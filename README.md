# 🍲 Rasoi - Traditional Heritage Recipe Application

An authentic traditional Indian recipe mobile application built with the **Flutter SDK** and **Dart**, featuring real data persistence, trilingual support, ingredient substitutions, interactive cooking timers, a validated recipe creator, and a smart meal planner.

---

## 👥 4.1 Team Members & Ownership Breakdown

In accordance with Section 4.1 of the Group Project Specification:

| Team Member | Owned Major Component / Screen | Description & Contribution |
| :--- | :--- | :--- |
| **Member 1 (Lead Developer)** | **Main List Screen (`RecipeListScreen`) & Provider State Store (`RecipeProvider`)** | Implemented the shared `RecipeProvider` store, dynamic theme, and the 4 screen states (Loading indicator, Error message with retry button, Empty state, and Data list). |
| **Member 2** | **Validated Form Screen (`AddRecipeScreen`) & Local Data Persistence (`RecipeService`)** | Developed the `GlobalKey<FormState>` input validation for every single field with custom error messages, and persistent storage saving to `SharedPreferences` to survive app restarts. |
| **Member 3** | **Detail Screen (`RecipeDetailScreen`), Substitutions Screen & API Integration** | Built the extended detail view with dynamic servings scaler, ingredient checklist, step timers, TheMealDB REST API integration, and the Ingredient Substitutions directory. |

---

## 📋 4.2 Mandatory Requirements Compliance Matrix (GP 1 – GP 11)

| Requirement ID | Requirement Name | How It Is Implemented in Code | File Location |
| :--- | :--- | :--- | :--- |
| **GP 1** | **Four Screens Minimum** | 1. `RecipeListScreen` (List)<br>2. `RecipeDetailScreen` (Detail)<br>3. `AddRecipeScreen` (Validated Form)<br>4. `SubstitutionsScreen` (Further screen of choice)<br>5. `MealPlannerTab` & `GroceryTab` | `flutter_app/lib/screens/` |
| **GP 2** | **Real Data** | Real data fetched from TheMealDB public REST API over the internet (`https://www.themealdb.com/api/json/v1/1/`) and user-created recipes saved locally to `SharedPreferences` so they survive app restarts. | `flutter_app/lib/services/recipe_service.dart` |
| **GP 3** | **Four Screen States** | The main list screen displays: 1. `CircularProgressIndicator` (Loading), 2. Error message with a **Retry** button (`ElevatedButton`), 3. Empty message when no items exist, and 4. The full data list. | `flutter_app/lib/screens/recipe_list_screen.dart` |
| **GP 4** | **Validated Form** | The `AddRecipeScreen` validates every field before saving (title, description, region, prep time > 0, cook time > 0, calories > 0, ingredients, steps) and displays clear inline error messages. | `flutter_app/lib/screens/add_recipe_screen.dart` |
| **GP 5** | **Detail View** | Tapping any recipe row opens `RecipeDetailScreen`, displaying detailed cultural background, dynamic servings adjuster (`- / +`), ingredient checklist, and step-by-step instructions. | `flutter_app/lib/screens/recipe_detail_screen.dart` |
| **GP 6** | **Shared State** | Application state is managed via `MultiProvider` with `RecipeProvider` (`ChangeNotifier`) and read across all screens simultaneously. | `flutter_app/lib/providers/recipe_provider.dart` |
| **GP 7** | **Theme** | Consistent Material 3 theme generated from one seed colour (`#BC3814` — Traditional Indian Terracotta/Saffron), with custom adaptive icon and branding. | `flutter_app/lib/main.dart` |
| **GP 8** | **Robustness** | Safe error handling, all views wrapped in `SingleChildScrollView` / `ListView` preventing pixel overflows when rotated sideways, and graceful offline network fallbacks. | Across all screens |
| **GP 9** | **Repository** | Shared GitHub repository tracking regular commits, detailed README, and complete source code. | Root repository |
| **GP 10** | **Release Build** | Production release APK configured with Android `<uses-permission android:name="android.permission.INTERNET" />`. | `app/src/main/AndroidManifest.xml` |
| **GP 11** | **Automated Test** | Automated widget test verifying app startup, navigation tabs, and screen transitions that passes with `flutter test`. | `flutter_app/test/widget_test.dart` |

---

## 🚀 How to Run the Flutter Project

```bash
cd flutter_app
flutter pub get
flutter test
flutter run
```
