import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../models/recipe.dart';
import '../providers/recipe_provider.dart';

// Requirement GP 1 (Form Screen) & GP 4 (Validated form checking every field with clear error message)
class AddRecipeScreen extends StatefulWidget {
  const AddRecipeScreen({super.key});

  @override
  State<AddRecipeScreen> createState() => _AddRecipeScreenState();
}

class _AddRecipeScreenState extends State<AddRecipeScreen> {
  final _formKey = GlobalKey<FormState>();

  final _titleController = TextEditingController();
  final _descriptionController = TextEditingController();
  final _regionController = TextEditingController();
  final _prepTimeController = TextEditingController(text: '15');
  final _cookTimeController = TextEditingController(text: '25');
  final _caloriesController = TextEditingController(text: '350');
  final _ingredientsController = TextEditingController();
  final _instructionsController = TextEditingController();

  String _selectedCategory = 'Curries & Dals';
  bool _isVegetarian = true;

  @override
  void dispose() {
    _titleController.dispose();
    _descriptionController.dispose();
    _regionController.dispose();
    _prepTimeController.dispose();
    _cookTimeController.dispose();
    _caloriesController.dispose();
    _ingredientsController.dispose();
    _instructionsController.dispose();
    super.dispose();
  }

  void _saveRecipe() {
    // Validate every field before saving (Requirement GP 4)
    if (!_formKey.currentState!.validate()) {
      ScaffoldMessenger.of(context).showSnackBar(
        const SnackBar(
          content: Text('Please correct the highlighted errors in the form.'),
          backgroundColor: Colors.red,
        ),
      );
      return;
    }

    // Parse ingredients from lines
    final ingLines = _ingredientsController.text
        .split('\n')
        .where((l) => l.trim().isNotEmpty)
        .map((l) => RecipeIngredient(name: l.trim(), quantity: 1, unit: ''))
        .toList();

    // Parse instructions from lines
    final stepLines = _instructionsController.text
        .split('\n')
        .where((l) => l.trim().isNotEmpty)
        .toList();

    final List<RecipeStep> steps = [];
    for (int i = 0; i < stepLines.length; i++) {
      steps.add(
        RecipeStep(
          stepNumber: i + 1,
          title: 'Step ${i + 1}',
          instruction: stepLines[i].trim(),
          timerSeconds: 300,
        ),
      );
    }

    final newRecipe = Recipe(
      id: DateTime.now().millisecondsSinceEpoch.toString(),
      title: _titleController.text.trim(),
      description: _descriptionController.text.trim(),
      region: _regionController.text.trim(),
      categoryName: _selectedCategory,
      isVegetarian: _isVegetarian,
      prepMinutes: int.parse(_prepTimeController.text.trim()),
      cookMinutes: int.parse(_cookTimeController.text.trim()),
      calories: int.parse(_caloriesController.text.trim()),
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: ingLines,
      instructions: steps,
    );

    // Save to shared Provider store & device storage (Requirement GP 2 & GP 6)
    context.read<RecipeProvider>().addRecipe(newRecipe);

    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(
        content: Text('Recipe "${newRecipe.title}" added successfully!'),
        backgroundColor: Colors.green,
      ),
    );

    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Add New Recipe'),
        backgroundColor: const Color(0xFFBC3814),
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(20.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text(
                'Create Traditional Dish',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold, color: Color(0xFFBC3814)),
              ),
              const SizedBox(height: 6),
              const Text(
                'Fill in the details below. All fields are required and validated.',
                style: TextStyle(color: Colors.black54),
              ),
              const SizedBox(height: 20),

              // Title Field (GP 4)
              TextFormField(
                controller: _titleController,
                decoration: const InputDecoration(
                  labelText: 'Recipe Title *',
                  hintText: 'e.g. Gongura Mutton / Paneer Butter Masala',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.restaurant),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'Recipe title is required and cannot be empty.';
                  }
                  if (value.trim().length < 3) {
                    return 'Title must be at least 3 characters long.';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),

              // Description Field (GP 4)
              TextFormField(
                controller: _descriptionController,
                maxLines: 2,
                decoration: const InputDecoration(
                  labelText: 'Description & Heritage Story *',
                  hintText: 'Brief description and traditional background of the dish.',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.description),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'Description is required.';
                  }
                  if (value.trim().length < 10) {
                    return 'Please enter a description of at least 10 characters.';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),

              // Region Field (GP 4)
              TextFormField(
                controller: _regionController,
                decoration: const InputDecoration(
                  labelText: 'Region / Origin *',
                  hintText: 'e.g. Andhra Pradesh, Punjab, Rayalaseema',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.location_on),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'Region is required to preserve culinary origin.';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),

              // Category & Diet Row
              Row(
                children: [
                  Expanded(
                    child: DropdownButtonFormField<String>(
                      value: _selectedCategory,
                      decoration: const InputDecoration(
                        labelText: 'Category *',
                        border: OutlineInputBorder(),
                      ),
                      items: const [
                        DropdownMenuItem(value: 'Curries & Dals', child: Text('Curries & Dals')),
                        DropdownMenuItem(value: 'Rice & Biryani', child: Text('Rice & Biryani')),
                        DropdownMenuItem(value: 'Tiffins & Breakfast', child: Text('Tiffins & Breakfast')),
                        DropdownMenuItem(value: 'Sweets & Snacks', child: Text('Sweets & Snacks')),
                        DropdownMenuItem(value: 'Festival Specials', child: Text('Festival Specials')),
                      ],
                      onChanged: (val) {
                        if (val != null) setState(() => _selectedCategory = val);
                      },
                    ),
                  ),
                  const SizedBox(width: 12),
                  FilterChip(
                    label: Text(_isVegetarian ? 'Pure Veg' : 'Non-Veg'),
                    selected: _isVegetarian,
                    selectedColor: Colors.green.shade100,
                    onSelected: (val) => setState(() => _isVegetarian = val),
                  ),
                ],
              ),
              const SizedBox(height: 16),

              // Numerical inputs: Prep time, Cook time, Calories (GP 4)
              Row(
                children: [
                  Expanded(
                    child: TextFormField(
                      controller: _prepTimeController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: 'Prep (mins) *',
                        border: OutlineInputBorder(),
                      ),
                      validator: (value) {
                        if (value == null || value.trim().isEmpty) {
                          return 'Enter prep time.';
                        }
                        final parsed = int.tryParse(value.trim());
                        if (parsed == null || parsed <= 0) {
                          return 'Must be > 0';
                        }
                        return null;
                      },
                    ),
                  ),
                  const SizedBox(width: 10),
                  Expanded(
                    child: TextFormField(
                      controller: _cookTimeController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: 'Cook (mins) *',
                        border: OutlineInputBorder(),
                      ),
                      validator: (value) {
                        if (value == null || value.trim().isEmpty) {
                          return 'Enter cook time.';
                        }
                        final parsed = int.tryParse(value.trim());
                        if (parsed == null || parsed <= 0) {
                          return 'Must be > 0';
                        }
                        return null;
                      },
                    ),
                  ),
                  const SizedBox(width: 10),
                  Expanded(
                    child: TextFormField(
                      controller: _caloriesController,
                      keyboardType: TextInputType.number,
                      decoration: const InputDecoration(
                        labelText: 'Calories *',
                        border: OutlineInputBorder(),
                      ),
                      validator: (value) {
                        if (value == null || value.trim().isEmpty) {
                          return 'Enter calories.';
                        }
                        final parsed = int.tryParse(value.trim());
                        if (parsed == null || parsed <= 0) {
                          return 'Must be > 0';
                        }
                        return null;
                      },
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16),

              // Ingredients Field (GP 4)
              TextFormField(
                controller: _ingredientsController,
                maxLines: 3,
                decoration: const InputDecoration(
                  labelText: 'Ingredients (one per line) *',
                  hintText: '2 cups Basmati Rice\n4 tbsp Desi Ghee\n1 tsp Cumin Seeds',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.shopping_bag),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'At least one ingredient is required.';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 16),

              // Instructions Field (GP 4)
              TextFormField(
                controller: _instructionsController,
                maxLines: 4,
                decoration: const InputDecoration(
                  labelText: 'Step-by-Step Instructions (one per line) *',
                  hintText: 'Soak rice for 30 minutes.\nHeat ghee and sauté whole spices.\nDum cook for 20 minutes.',
                  border: OutlineInputBorder(),
                  prefixIcon: Icon(Icons.format_list_numbered),
                ),
                validator: (value) {
                  if (value == null || value.trim().isEmpty) {
                    return 'At least one instruction step is required.';
                  }
                  return null;
                },
              ),
              const SizedBox(height: 24),

              // Save Recipe Action Button
              SizedBox(
                width: double.infinity,
                height: 52,
                child: ElevatedButton.icon(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: const Color(0xFFBC3814),
                    foregroundColor: Colors.white,
                    shape: RoundedCornerShape(12),
                  ),
                  icon: const Icon(Icons.check_circle),
                  label: const Text(
                    'Save & Publish Recipe',
                    style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                  ),
                  onPressed: _saveRecipe,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
