import 'package:flutter/material.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:provider/provider.dart';
import 'package:rasoi_traditional_recipes/main.dart';
import 'package:rasoi_traditional_recipes/providers/recipe_provider.dart';

// Requirement GP 11: At least one widget test that runs and passes with the flutter test command.
void main() {
  testWidgets('Rasoi app loads and verifies bottom navigation tabs and screens', (WidgetTester tester) async {
    // Build our app and trigger a frame
    await tester.pumpWidget(
      MultiProvider(
        providers: [
          ChangeNotifierProvider(create: (_) => RecipeProvider()),
        ],
        child: const RasoiFlutterApp(),
      ),
    );

    // Initial pump
    await tester.pumpAndSettle();

    // Verify main screen loads
    expect(find.text('Traditional Recipes'), findsOneWidget);

    // Verify navigation destination labels exist
    expect(find.text('Recipes'), findsOneWidget);
    expect(find.text('Substitutes'), findsOneWidget);
    expect(find.text('Meal Plan'), findsOneWidget);
    expect(find.text('Grocery'), findsOneWidget);

    // Tap on the "Substitutes" navigation tab (GP 1)
    await tester.tap(find.text('Substitutes'));
    await tester.pumpAndSettle();

    // Verify the Substitutions screen is displayed
    expect(find.text('Ingredient Substitutions'), findsOneWidget);
  });
}
