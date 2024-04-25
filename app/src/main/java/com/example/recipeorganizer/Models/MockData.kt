package com.example.recipeorganizer.Models

class MockData {
    val categories: ArrayList<Category> = arrayListOf(
        Category(1,"Breakfast"),
        Category(2,"Lunch"),
        Category(3,"Dinner"),
        Category(4,"Supper"),
        Category(5,"Easter"),
        Category(6,"Christmas"),
        Category(7,"Thanksgiving"),
        Category(8,"Grandma's"))
    val ingredients: ArrayList<Ingredient> = arrayListOf(
        Ingredient(1,"Pancetta", "100g"),
        Ingredient(2,"Pecorino cheese", "50g"),
        Ingredient(3,"Parmesan", "50g"),
        Ingredient(4,"Large eggs", "x2"),
        Ingredient(5,"Spaghetti", "350g"),
        Ingredient(6,"Garlic cloves", "x2"),
        Ingredient(7,"Butter", "50g"),
        Ingredient(8,"Sea salt", null),
        Ingredient(9,"Black pepper", null))
    val recipes: ArrayList<Recipe> = arrayListOf(
        Recipe(
            1,
            "Spaghetti carbonara",
            40,
            "STEP 1 Put a large saucepan of water on to boil. " +
                    "STEP 2 Finely chop the 100g pancetta, having first removed any rind. Finely grate 50g pecorino cheese and 50g parmesan and mix them together. " +
                    "STEP 3 Beat the 3 large eggs in a medium bowl and season with a little freshly grated black pepper. Set everything aside. " +
                    "STEP 4 Add 1 tsp salt to the boiling water, add 350g spaghetti and when the water comes back to the boil, cook at a constant simmer, covered, for 10 minutes or until al dente (just cooked). " +
                    "STEP 5 Squash 2 peeled plump garlic cloves with the blade of a knife, just to bruise it. " +
                    "STEP 6 While the spaghetti is cooking, fry the pancetta with the garlic. Drop 50g unsalted butter into a large frying pan or wok and, as soon as the butter has melted, tip in the pancetta and garlic. " +
                    "STEP 7 Leave to cook on a medium heat for about 5 minutes, stirring often, until the pancetta is golden and crisp. The garlic has now imparted its flavour, so take it out with a slotted spoon and discard. " +
                    "STEP 8 Keep the heat under the pancetta on low. When the pasta is ready, lift it from the water with a pasta fork or tongs and put it in the frying pan with the pancetta. Don’t worry if a little water drops in the pan as well (you want this to happen) and don’t throw the pasta water away yet. " +
                    "STEP 9 Mix most of the cheese in with the eggs, keeping a small handful back for sprinkling over later. " +
                    "STEP 10 Take the pan of spaghetti and pancetta off the heat. Now quickly pour in the eggs and cheese. Using the tongs or a long fork, lift up the spaghetti so it mixes easily with the egg mixture, which thickens but doesn’t scramble, and everything is coated. " +
                    "STEP 11 Add extra pasta cooking water to keep it saucy (several tablespoons should do it). You don’t want it wet, just moist. Season with a little salt, if needed. " +
                    "STEP 12 Use a long-pronged fork to twist the pasta on to the serving plate or bowl. Serve immediately with a little sprinkling of the remaining cheese and a grating of black pepper. If the dish does get a little dry before serving, splash in some more hot pasta water and the glossy sauciness will be revived.",
            "spaghetti_carbonara"
        ),
        Recipe(
            2,
            "Shrimp soup",
            25,
            "STEP 1 In a large saucepan over medium heat, combine chicken broth, beef broth and shrimp consomme. Bring to a simmer, then strain. \n" +
                    "STEP 2 Return broth to pot and add shrimp and dill. Heat through and adjust seasonings. Garnish with chopped green onion.",
            "shrimp_soup"
        ),
        Recipe(
            3,
            "Chicken sandwich",
            20,
            "STEP 1 In a small bowl combine shredded chicken, kimchi, and mayonnaise.\n" +
                    "STEP 2 Preheat a small skillet over medium heat. Spread ½ tablespoon butter on one side of a slice of bread. Place 1 slice bread butter-side-down in skillet; place 1 slice provolone and 1 slice Havarti cheese onto bread. Top with chicken mixture, then with remaining slice provolone and remaining Havarti. Butter second bread slice with remaining butter on one side and place butter-side-up on top of sandwich.\n" +
                    "STEP 3 Cook sandwich in skillet until bread is lightly browned and flip over, about 2 ½ minutes per side.",
            "chicken_sandwich"
            ),
        Recipe(
            4,
            "Rice with eggs and zucchini",
            30,
            "In a wok or large fry pan, heat vegetable oil. Saute garlic for 30 seconds.\n" +
                    "Add carrots and zucchini. Fry for 2 to 3 minutes.\n" +
                    "Push the vegetables to the side of the wok or large fry pan. Lightly beat the eggs and pour into the wok or fry pan. Allow eggs to set.\n" +
                    "When eggs are almost set, add rice over the eggs and vegetables.\n" +
                    "Add ground pepper and soy sauce. Stir to get everything well mixed. This will take 3 to 4 minutes.\n" +
                    "Half way through stir frying, add salt to taste. Continue to stir fry until rice is dry and fluffy.\n" +
                    "Stir in sliced green onions. Turn off stove.\n" +
                    "Dish into bowls and serve immediately.",
            "rice_with_eggs"
        ),
        Recipe(
            5,
            "Duck with thyme",
            90,
            "1. Score the skin of the duck 4 or 5 times, being careful not to cut through into the meat. Put the duck in a roasting tin in the sink, pour over a kettleful of boiling water then drain and allow to dry for 1-2 hours, to prepare the fatty skin ready for roasting.\n" +
                    "2. Preheat the oven to 150˚C, gas mark 2. Rub the duck inside and out with a generous amount of salt and the thyme leaves. Pour over the honey and stock and roast for 2 hours 15 minutes. Every 45 minutes, baste the duck with the juices and fats from the tray. If the juices turn too thick, add a little more stock or water.\n" +
                    "3. When the fat seems excessive, carefully pour some into a jug (save it to cook roast potatoes). After 2 hours 15 minutes, check the duck is thoroughly cooked; it should be tender to the touch and the juices should run clear. If it’s not ready, baste again and cook for another 10 minutes or so, until it is tender. Transfer to a serving plate and allow to rest for 25 minutes.\n" +
                    "4. Meanwhile, strain off the fat from the roasting tin and make a gravy by reducing the cooking juices over a high heat. Garnish the duck with extra thyme sprigs, sprinkle with sea salt and serve with the gravy. ",
            "duck_with_thyme"
        )
    )
}