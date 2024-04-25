package com.example.recipeorganizer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeorganizer.Repositories.CategoryRepository
import com.example.recipeorganizer.Models.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoryRepository) : ViewModel() {
    val allCategories: LiveData<List<Category>> = repository.allCategories.asLiveData()

    fun insert(category: Category) = viewModelScope.launch {
        repository.insert(category)
    }
}