import 'dart:convert';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';
import '../models/recipe.dart';

class RecipeService {
  static const String _userRecipesKey = 'rasoi_user_recipes_v1';
  static const String _apiEndpoint = 'https://www.themealdb.com/api/json/v1/1/search.php?s=curry';

  // Fetch real data from public REST API over the internet (Requirement GP 2)
  Future<List<Recipe>> fetchRecipesFromApi() async {
    try {
      final response = await http.get(Uri.parse(_apiEndpoint)).timeout(
        const Duration(seconds: 8),
      );

      if (response.statusCode == 200) {
        final data = json.decode(response.body);
        final meals = data['meals'] as List<dynamic>?;
        if (meals != null && meals.isNotEmpty) {
          return meals.map((m) => _parseMealToRecipe(m)).toList();
        }
      }
      return [];
    } catch (e) {
      // Re-throw so caller can display the Error State with Retry (Requirement GP 3)
      throw Exception('Failed to load recipes from internet: $e');
    }
  }

  Recipe _parseMealToRecipe(dynamic m) {
    List<RecipeIngredient> ingredients = [];
    for (int i = 1; i <= 6; i++) {
      final ing = m['strIngredient$i'];
      final measure = m['strMeasure$i'];
      if (ing != null && ing.toString().trim().isNotEmpty) {
        ingredients.add(
          RecipeIngredient(
            name: ing.toString().trim(),
            quantity: 1,
            unit: measure?.toString().trim() ?? '',
          ),
        );
      }
    }

    return Recipe(
      id: m['idMeal']?.toString() ?? DateTime.now().millisecondsSinceEpoch.toString(),
      title: m['strMeal']?.toString() ?? 'Traditional Dish',
      description: 'Authentic ${m['strArea'] ?? 'Traditional'} specialty featuring aromatic spices.',
      region: m['strArea']?.toString() ?? 'Traditional India',
      categoryName: m['strCategory']?.toString() ?? 'Curries & Dals',
      isVegetarian: !(m['strMeal']?.toString().toLowerCase().contains('chicken') ?? false),
      prepMinutes: 20,
      cookMinutes: 30,
      calories: 450,
      imageUrl: m['strMealThumb']?.toString() ?? 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: ingredients.isNotEmpty
          ? ingredients
          : [
              const RecipeIngredient(name: 'Whole Spices', quantity: 2, unit: 'tbsp'),
              const RecipeIngredient(name: 'Aromatic Gravy Base', quantity: 1, unit: 'cup'),
            ],
      instructions: [
        RecipeStep(
          stepNumber: 1,
          title: 'Preparation & Sautéing',
          instruction: m['strInstructions']?.toString().substring(0, (m['strInstructions']?.toString().length ?? 50).clamp(0, 200)) ?? 'Prepare ingredients and sauté spices.',
          timerSeconds: 300,
        ),
        const RecipeStep(
          stepNumber: 2,
          title: 'Simmer to Perfection',
          instruction: 'Simmer on low flame until oil releases and aroma develops.',
          timerSeconds: 900,
        ),
      ],
    );
  }

  // Load user-created recipes saved locally (Survives app restarts - Requirement GP 2)
  Future<List<Recipe>> loadUserRecipes() async {
    try {
      final prefs = await SharedPreferences.getInstance();
      final raw = prefs.getString(_userRecipesKey);
      if (raw != null && raw.isNotEmpty) {
        final List<dynamic> list = json.decode(raw);
        return list.map((item) => Recipe.fromJson(item as Map<String, dynamic>)).toList();
      }
    } catch (_) {}
    return [];
  }

  // Save a new user recipe persistently (Requirement GP 2 & GP 4)
  Future<void> saveUserRecipe(Recipe recipe) async {
    final prefs = await SharedPreferences.getInstance();
    final current = await loadUserRecipes();
    final updated = [recipe, ...current];
    await prefs.setString(_userRecipesKey, json.encode(updated.map((r) => r.toJson()).toList()));
  }
}
