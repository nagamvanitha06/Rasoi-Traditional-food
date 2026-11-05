import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/recipe.dart';
import '../providers/recipe_provider.dart';
import 'add_recipe_screen.dart';
import 'recipe_detail_screen.dart';

// Requirement GP 1 (List screen) & GP 3 (Four screen states: Loading, Error with Retry, Empty, Data)
class RecipeListScreen extends StatelessWidget {
  const RecipeListScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final provider = context.watch<RecipeProvider>();

    return Scaffold(
      appBar: AppBar(
        title: const Text('Traditional Recipes', style: TextStyle(fontWeight: FontWeight.bold)),
        backgroundColor: const Color(0xFFBC3814),
        foregroundColor: Colors.white,
        actions: [
          // Refresh / Test Retry button
          IconButton(
            tooltip: 'Refresh / Fetch API',
            icon: const Icon(Icons.refresh),
            onPressed: () => provider.fetchRecipes(),
          ),
          // Helper button to demonstrate Error State with Retry (GP 3)
          PopupMenuButton<String>(
            icon: const Icon(Icons.more_vert),
            onSelected: (val) {
              if (val == 'simulate_error') {
                provider.simulateNetworkError();
              }
            },
            itemBuilder: (ctx) => [
              const PopupMenuItem(
                value: 'simulate_error',
                child: Text('Simulate Network Error (Test GP 3)'),
              ),
            ],
          ),
        ],
      ),
      body: _buildBody(context, provider),
      // FAB to navigate to Validated Form Screen (GP 1 & GP 4)
      floatingActionButton: FloatingActionButton.extended(
        backgroundColor: const Color(0xFFBC3814),
        foregroundColor: Colors.white,
        icon: const Icon(Icons.add),
        label: const Text('Add Recipe', style: TextStyle(fontWeight: FontWeight.bold)),
        onPressed: () {
          Navigator.push(
            context,
            MaterialPageRoute(builder: (_) => const AddRecipeScreen()),
          );
        },
      ),
    );
  }

  Widget _buildBody(BuildContext context, RecipeProvider provider) {
    // State 1: Loading Indicator (Requirement GP 3)
    if (provider.isLoading) {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: const [
            CircularProgressIndicator(color: Color(0xFFBC3814)),
            SizedBox(height: 16),
            Text(
              'Fetching authentic recipes...',
              style: TextStyle(fontSize: 16, fontWeight: FontWeight.w500),
            ),
          ],
        ),
      );
    }

    // State 2: Error message with a Retry option (Requirement GP 3)
    if (provider.errorMessage != null) {
      return Center(
        child: Padding(
          padding: const EdgeInsets.all(24.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Icon(Icons.error_outline, size: 64, color: Colors.red),
              const SizedBox(height: 16),
              const Text(
                'Something went wrong',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8),
              Text(
                provider.errorMessage!,
                textAlign: TextAlign.center,
                style: const TextStyle(color: Colors.black54),
              ),
              const SizedBox(height: 24),
              ElevatedButton.icon(
                style: ElevatedButton.styleFrom(
                  backgroundColor: const Color(0xFFBC3814),
                  foregroundColor: Colors.white,
                  padding: const EdgeInsets.symmetric(horizontal: 24, vertical: 12),
                  shape: RoundedCornerShape(12),
                ),
                icon: const Icon(Icons.refresh),
                label: const Text('Retry', style: TextStyle(fontSize: 16)),
                onPressed: () => provider.fetchRecipes(),
              ),
            ],
          ),
        ),
      );
    }

    // State 3: Empty Message (Requirement GP 3)
    if (provider.recipes.isEmpty) {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Icon(Icons.restaurant_menu, size: 64, color: Colors.grey),
            const SizedBox(height: 16),
            const Text(
              'No recipes found',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            const Text(
              'Tap the "+ Add Recipe" button below to add your first dish.',
              style: TextStyle(color: Colors.black54),
            ),
            const SizedBox(height: 16),
            OutlinedButton(
              onPressed: () => provider.fetchRecipes(),
              child: const Text('Reload Default Recipes'),
            ),
          ],
        ),
      );
    }

    // State 4: Data List View (Requirement GP 3)
    return ListView.builder(
      padding: const EdgeInsets.only(left: 16, right: 16, top: 16, bottom: 80),
      itemCount: provider.recipes.length,
      itemBuilder: (context, index) {
        final recipe = provider.recipes[index];
        final isFav = provider.favoriteIds.contains(recipe.id);

        return Card(
          margin: const EdgeInsets.only(bottom: 16),
          shape: RoundedCornerShape(16),
          clipBehavior: Clip.antiAlias,
          elevation = 2,
          child: InkWell(
            // Requirement GP 5: Tapping an item opens a detail screen
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(
                  builder: (_) => RecipeDetailScreen(recipe: recipe),
                ),
              );
            },
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Stack(
                  children: [
                    Image.network(
                      recipe.imageUrl,
                      height: 170,
                      width: double.infinity,
                      fit: BoxFit.cover,
                      errorBuilder: (_, __, ___) => Container(
                        height: 170,
                        color: const Color(0xFFBC3814).withOpacity(0.15),
                        child: const Icon(Icons.restaurant, size: 48, color: Color(0xFFBC3814)),
                      ),
                    ),
                    Positioned(
                      top: 10,
                      left: 10,
                      child: Container(
                        padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                        decoration: BoxDecoration(
                          color: Colors.black.withOpacity(0.65),
                          borderRadius: BorderRadius.circular(10),
                        ),
                        child: Text(
                          recipe.region,
                          style: const TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.bold),
                        ),
                      ),
                    ),
                    Positioned(
                      top: 6,
                      right: 6,
                      child: CircleAvatar(
                        backgroundColor: Colors.white.withOpacity(0.85),
                        child: IconButton(
                          icon: Icon(
                            isFav ? Icons.favorite : Icons.favorite_border,
                            color: isFav ? Colors.red : Colors.grey,
                          ),
                          onPressed: () => provider.toggleFavorite(recipe.id),
                        ),
                      ),
                    ),
                  ],
                ),
                Padding(
                  padding: const EdgeInsets.all(14.0),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        recipe.title,
                        style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                      const SizedBox(height: 4),
                      Text(
                        recipe.description,
                        maxLines = 2,
                        overflow: TextOverflow.ellipsis,
                        style: const TextStyle(fontSize: 13, color: Colors.black54),
                      ),
                      const SizedBox(height: 10),
                      Row(
                        children: [
                          Icon(Icons.schedule, size: 16, color: Colors.grey.shade700),
                          const SizedBox(width: 4),
                          Text('${recipe.totalMinutes}m total', style: const TextStyle(fontSize: 12)),
                          const SizedBox(width: 14),
                          Icon(Icons.local_fire_department, size: 16, color: Colors.orange.shade800),
                          const SizedBox(width: 4),
                          Text('${recipe.calories} kcal', style: const TextStyle(fontSize: 12)),
                          const Spacer(),
                          const Text('View Recipe →', style: TextStyle(color: Color(0xFFBC3814), fontWeight: FontWeight.bold, fontSize: 12)),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        );
      },
    );
  }
}
