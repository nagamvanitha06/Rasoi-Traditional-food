import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/recipe.dart';
import '../providers/recipe_provider.dart';

// Requirement GP 1 (Detail Screen) & GP 5 (Detail view showing more information than list row)
class RecipeDetailScreen extends StatefulWidget {
  final Recipe recipe;

  const RecipeDetailScreen({super.key, required this.recipe});

  @override
  State<RecipeDetailScreen> createState() => _RecipeDetailScreenState();
}

class _RecipeDetailScreenState extends State<RecipeDetailScreen> {
  int _servings = 4;
  final Set<int> _checkedIngredients = {};

  @override
  void initState() {
    super.initState();
    _servings = widget.recipe.baseServings;
  }

  @override
  Widget build(BuildContext context) {
    final provider = context.watch<RecipeProvider>();
    final isFav = provider.favoriteIds.contains(widget.recipe.id);
    final scale = _servings / widget.recipe.baseServings.toDouble();

    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // Collapsible Hero AppBar
          SliverAppBar(
            expandedHeight: 250,
            pinned: true,
            backgroundColor: const Color(0xFFBC3814),
            foregroundColor: Colors.white,
            actions: [
              IconButton(
                icon: Icon(
                  isFav ? Icons.favorite : Icons.favorite_border,
                  color: isFav ? Colors.red : Colors.white,
                ),
                onPressed: () => provider.toggleFavorite(widget.recipe.id),
              ),
            ],
            flexibleSpace: FlexibleSpaceBar(
              title: Text(
                widget.recipe.title,
                style: const TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.bold,
                  shadows: [Shadow(color: Colors.black, blurRadius: 4)],
                ),
              ),
              background: Stack(
                fit: StackFit.expand,
                children: [
                  Image.network(
                    widget.recipe.imageUrl,
                    fit: BoxFit.cover,
                    errorBuilder: (_, __, ___) => Container(color: Colors.brown),
                  ),
                  const DecoratedBox(
                    decoration: BoxDecoration(
                      gradient: LinearGradient(
                        begin: Alignment.topCenter,
                        end: Alignment.bottomCenter,
                        colors: [Colors.transparent, Colors.black87],
                      ),
                    ),
                  ),
                ],
              ),
            ),
          ),

          // Detail Content Body
          SliverToBoxAdapter(
            child: Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  // Region & Diet Badges
                  Row(
                    children: [
                      Chip(
                        backgroundColor: const Color(0xFFBC3814).withOpacity(0.1),
                        label: Text(
                          widget.recipe.region,
                          style: const TextStyle(fontWeight: FontWeight.bold, color: Color(0xFFBC3814)),
                        ),
                      ),
                      const SizedBox(width: 8),
                      Chip(
                        backgroundColor: widget.recipe.isVegetarian ? Colors.green.shade50 : Colors.red.shade50,
                        avatar: Icon(
                          Icons.circle,
                          size: 14,
                          color: widget.recipe.isVegetarian ? Colors.green : Colors.red,
                        ),
                        label: Text(
                          widget.recipe.isVegetarian ? 'Pure Vegetarian' : 'Non-Vegetarian',
                          style: TextStyle(
                            color: widget.recipe.isVegetarian ? Colors.green.shade900 : Colors.red.shade900,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 12),

                  // Description
                  Text(
                    widget.recipe.description,
                    style: const TextStyle(fontSize: 15, height: 1.4, color: Colors.black87),
                  ),
                  const SizedBox(height: 16),

                  // Metric Row: Prep, Cook, Total Time, Calories
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      _buildMetric('Prep Time', '${widget.recipe.prepMinutes}m', Icons.timer_outlined),
                      _buildMetric('Cook Time', '${widget.recipe.cookMinutes}m', Icons.soup_kitchen_outlined),
                      _buildMetric('Total Time', '${widget.recipe.totalMinutes}m', Icons.schedule),
                      _buildMetric('Calories', '${widget.recipe.calories}', Icons.local_fire_department_outlined),
                    ],
                  ),
                  const Divider(height: 36),

                  // Interactive Servings Scaler (GP 5)
                  Container(
                    padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
                    decoration: BoxDecoration(
                      color: Colors.amber.shade50,
                      borderRadius: BorderRadius.circular(12),
                      border: Border.all(color: Colors.amber.shade200),
                    ),
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        const Text(
                          'Adjust Servings:',
                          style: TextStyle(fontSize: 15, fontWeight: FontWeight.bold),
                        ),
                        Row(
                          children: [
                            IconButton(
                              icon: const Icon(Icons.remove_circle_outline),
                              onPressed: _servings > 1 ? () => setState(() => _servings--) : null,
                            ),
                            Text(
                              '$_servings Servings',
                              style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold, color: Color(0xFFBC3814)),
                            ),
                            IconButton(
                              icon: const Icon(Icons.add_circle_outline),
                              onPressed: _servings < 16 ? () => setState(() => _servings++) : null,
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 20),

                  // Ingredients Header & Grocery Action
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      const Text(
                        'Ingredients Required',
                        style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                      ),
                      TextButton.icon(
                        icon: const Icon(Icons.add_shopping_cart, size: 18),
                        label: const Text('Add to Grocery'),
                        onPressed: () {
                          for (final ing in widget.recipe.ingredients) {
                            provider.addToShopping('${ing.name} (${ing.formatted(scale)})');
                          }
                          ScaffoldMessenger.of(context).showSnackBar(
                            const SnackBar(
                              content: Text('All ingredients added to Grocery List!'),
                              backgroundColor: Colors.green,
                            ),
                          );
                        },
                      ),
                    ],
                  ),
                  const SizedBox(height: 8),

                  // Scaled Ingredients List
                  ...List.generate(widget.recipe.ingredients.length, (idx) {
                    final ing = widget.recipe.ingredients[idx];
                    final isChecked = _checkedIngredients.contains(idx);

                    return CheckboxListTile(
                      dense: true,
                      value: isChecked,
                      onChanged: (val) {
                        setState(() {
                          if (val == true) {
                            _checkedIngredients.add(idx);
                          } else {
                            _checkedIngredients.remove(idx);
                          }
                        });
                      },
                      title: Text(
                        ing.name,
                        style: TextStyle(
                          decoration: isChecked ? TextDecoration.lineThrough : null,
                          fontWeight: FontWeight.w500,
                        ),
                      ),
                      subtitle: ing.substitute != null
                          ? Text(
                              'Substitute: ${ing.substitute}',
                              style: TextStyle(color: Colors.amber.shade900, fontSize: 12),
                            )
                          : null,
                      secondary: Text(
                        ing.formatted(scale),
                        style: const TextStyle(fontWeight: FontWeight.bold, color: Color(0xFFBC3814)),
                      ),
                    );
                  }),

                  const Divider(height: 36),

                  // Steps Header
                  const Text(
                    'Step-by-Step Instructions',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 12),

                  // Steps
                  ...widget.recipe.instructions.map((step) {
                    return Card(
                      margin: const EdgeInsets.only(bottom: 12),
                      shape: RoundedCornerShape(12),
                      child: Padding(
                        padding: const EdgeInsets.all(14.0),
                        child: Column(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Row(
                                  children: [
                                    CircleAvatar(
                                      radius: 12,
                                      backgroundColor: const Color(0xFFBC3814),
                                      child: Text(
                                        '${step.stepNumber}',
                                        style: const TextStyle(color: Colors.white, fontSize: 12, fontWeight: FontWeight.bold),
                                      ),
                                    ),
                                    const SizedBox(width: 8),
                                    Text(
                                      step.title,
                                      style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                                    ),
                                  ],
                                ),
                                if (step.timerSeconds != null)
                                  Chip(
                                    avatar: const Icon(Icons.timer, size: 14),
                                    label: Text('${step.timerSeconds! ~/ 60}m'),
                                  ),
                              ],
                            ),
                            const SizedBox(height: 8),
                            Text(
                              step.instruction,
                              style: const TextStyle(fontSize: 14, height: 1.4),
                            ),
                          ],
                        ),
                      ),
                    );
                  }),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildMetric(String label, String value, IconData icon) {
    return Column(
      children: [
        Icon(icon, color: const Color(0xFFBC3814), size: 24),
        const SizedBox(height: 4),
        Text(value, style: const TextStyle(fontSize: 16, fontWeight: FontWeight.bold)),
        Text(label, style: const TextStyle(fontSize: 11, color: Colors.black54)),
      ],
    );
  }
}
