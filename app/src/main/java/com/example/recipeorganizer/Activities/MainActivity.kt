package com.example.recipeorganizer.Activities

import android.R.id.input
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeorganizer.Adapters.CategoryListAdapter
import com.example.recipeorganizer.Adapters.ManageCategoryAdapter
import com.example.recipeorganizer.Adapters.RecipeListAdapter
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.R
import com.example.recipeorganizer.RecipeOrganizerApplication
import com.example.recipeorganizer.ViewModels.CategoryViewModel
import com.example.recipeorganizer.ViewModels.CategoryViewModelFactory
import com.example.recipeorganizer.ViewModels.RecipeViewModel
import com.example.recipeorganizer.ViewModels.RecipeViewModelFactory
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((application as RecipeOrganizerApplication).recipeRepository)
    }

    private val categoryViewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory((application as RecipeOrganizerApplication).categoryRepository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val recipeAdapter = RecipeListAdapter()
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        val categoryAdapter = CategoryListAdapter()
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.cl_overlay)
        val addButton = findViewById<FloatingActionButton>(R.id.floating_action_button_add)
        val backButton = findViewById<FloatingActionButton>(R.id.floating_action_button_back)
        val addRecipeButton = findViewById<Button>(R.id.elevatedButton_addRecipe)
        val manageCategoryButton = findViewById<Button>(R.id.elevatedButton_manageCategories)

        recyclerView.adapter  = recipeAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val dialogInside = LayoutInflater.from(this)
            .inflate(R.layout.manage_categories_layout, null, false)
        val manageCategoryRecyclerView: RecyclerView = dialogInside.findViewById(R.id.categoryRecyclerView)
        val manageCategoryAdapter: ManageCategoryAdapter = ManageCategoryAdapter(categoryViewModel, recipeViewModel)
        val categoryNameInput: TextInputEditText = dialogInside.findViewById(R.id.categoryNameInput)
        val addCategoryButton: Button = dialogInside.findViewById(R.id.addCategoryButton)

        manageCategoryRecyclerView.adapter = manageCategoryAdapter
        manageCategoryRecyclerView.layoutManager = LinearLayoutManager(this)

        builder
            .setView(dialogInside)
            .setPositiveButton(resources.getString(R.string.confirm_button)) { dialog, which ->
                dialog.dismiss()
            }
        val dialog: AlertDialog = builder.create()

        addCategoryButton.setOnClickListener {
            categoryViewModel.insert(Category(0,categoryNameInput.text.toString()))
        }

        recipeViewModel.allRecipes.observe(this, Observer { recipes ->
            // Update the cached copy of the words in the adapter.
            recipes?.let {
                recipeAdapter.submitList(it)
            }
        })
        categoryViewModel.allCategories.observe(this, Observer { categories ->
            // Update the cached copy of the words in the adapter.
            categories?.let {
                chipGroup.removeAllViews()
                categoryAdapter.submitList(it)
                DisplayCategories(categoryAdapter.currentList, chipGroup)

                manageCategoryAdapter.submitList(it)
            }
        })

        addButton.setOnClickListener {
            coordinatorLayout.visibility = View.VISIBLE
            coordinatorLayout.animate().alpha(1f)
            addButton.visibility = View.GONE
        }
        backButton.setOnClickListener {
            addButton.visibility = View.VISIBLE
            coordinatorLayout.animate().alpha(0f)
            coordinatorLayout.visibility = View.GONE
        }

        addRecipeButton.setOnClickListener {
            val openAddRecipe: Intent = Intent(this, AddRecipeActivity::class.java)
            startActivity(openAddRecipe)
            backButton.callOnClick()
        }

        manageCategoryButton.setOnClickListener {
            dialog.show()
        }
    }

    fun DisplayCategories(categories: List<Category>, chipGroup: ChipGroup) {
        for (category in categories) {
            val chip = getLayoutInflater().inflate(R.layout.selectable_chip_layout, chipGroup, false)
            val chipItem = chip.findViewById<Chip>(R.id.chip)
            chipItem.text = category.name
            chipGroup.addView(chip)
        }
    }

    fun DisplayRecipes(recipes: List<Recipe>, linearLayout: LinearLayout) {
        for (recipe in recipes) {
            val materialCard = getLayoutInflater().inflate(R.layout.item_layout, linearLayout, false)
            val recipeText = materialCard.findViewById<TextView>(R.id.recipe_title_text)
            val prepTimeText = materialCard.findViewById<TextView>(R.id.prep_time_text)
            val image = materialCard.findViewById<ImageView>(R.id.image_small)
            val imgUri: Uri = Uri.parse(applicationInfo.dataDir + "/files/foodImages/" + recipe.imagePath)

            val time = recipe.preparationTime
            val text: String

            if (time > 59) {
                val hours = time/60
                val minutes = time%60
                text = "$hours h $minutes min"
            }
            else {
                text = "$time minutes"
            }

            recipeText.text = recipe.name
            prepTimeText.text = text
            image.setImageURI(imgUri)

            materialCard.setOnClickListener() {
                val openItem: Intent = Intent(this, ItemActivity::class.java)
                openItem.putExtra("recipeItemKey", recipe.id)
                startActivity(openItem)
            }

            linearLayout.addView(materialCard)
        }
    }
}