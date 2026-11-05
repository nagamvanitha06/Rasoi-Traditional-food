package com.example

import com.example.data.RecipeData
import com.example.data.SubstitutionsData
import com.example.model.AppLanguage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }

  @Test
  fun `traditional recipes dataset has valid translations in all 3 languages`() {
    val recipes = RecipeData.traditionalRecipes
    assertTrue("Traditional recipes list should not be empty", recipes.isNotEmpty())

    recipes.forEach { recipe ->
      assertNotNull("Recipe ID should exist", recipe.id)
      assertTrue("Title in English should be non-empty", recipe.title.get(AppLanguage.ENGLISH).isNotBlank())
      assertTrue("Title in Hindi should be non-empty", recipe.title.get(AppLanguage.HINDI).isNotBlank())
      assertTrue("Title in Telugu should be non-empty", recipe.title.get(AppLanguage.TELUGU).isNotBlank())
      assertTrue("Recipe must have ingredients", recipe.ingredients.isNotEmpty())
      assertTrue("Recipe must have steps", recipe.instructions.isNotEmpty())
    }
  }

  @Test
  fun `ingredient scaling formats correctly for fractional and whole values`() {
    val recipe = RecipeData.traditionalRecipes.first { it.id == "rec_hyderabadi_biryani" }
    val rice = recipe.ingredients.first { it.category == com.example.model.IngredientCategory.GRAINS }

    // Base quantity is 500g for 4 servings
    // Scale 1.0 (4 servings)
    assertEquals("500 g", rice.formattedQuantity(1.0, AppLanguage.ENGLISH))
    // Scale 0.5 (2 servings) -> 250g
    assertEquals("250 g", rice.formattedQuantity(0.5, AppLanguage.ENGLISH))
    // Scale 2.0 (8 servings) -> 1000g
    assertEquals("1000 g", rice.formattedQuantity(2.0, AppLanguage.ENGLISH))
  }

  @Test
  fun `traditional substitutions database has valid options`() {
    val subs = SubstitutionsData.masterSubstitutions
    assertTrue("Substitutions should not be empty", subs.isNotEmpty())
    val tamarindSub = subs.first { it.original.en.contains("Tamarind") }
    assertTrue(tamarindSub.substitute.get(AppLanguage.ENGLISH).isNotBlank())
    assertTrue(tamarindSub.substitute.get(AppLanguage.HINDI).isNotBlank())
    assertTrue(tamarindSub.substitute.get(AppLanguage.TELUGU).isNotBlank())
  }
}
