import 'language.dart';

enum RecipeCategory {
  all(LocalizedText(en: 'All Traditional', hi: 'सभी पारंपरिक', te: 'అన్ని సాంప్రదాయ')),
  breakfast(LocalizedText(en: 'Tiffins & Breakfast', hi: 'नाश्ता और टिफिन', te: 'టిఫిన్స్ & అల్పాహారం')),
  biryani(LocalizedText(en: 'Rice & Biryani', hi: 'चावल और बिरयानी', te: 'అన్నం & బిర్యానీ')),
  curries(LocalizedText(en: 'Curries & Dals', hi: 'करी और दालें', te: 'కూరలు & పప్పులు')),
  sweets(LocalizedText(en: 'Sweets & Snacks', hi: 'मिठाई और स्नैक्स', te: 'స్వీట్లు & స్నాక్స్')),
  festival(LocalizedText(en: 'Festival Specials', hi: 'त्यौहार विशेष', te: 'పండుగ వంటకాలు'));

  final LocalizedText label;
  const RecipeCategory(this.label);
}

class RecipeIngredient {
  final String name;
  final double quantity;
  final String unit;
  final String? substitute;

  const RecipeIngredient({
    required this.name,
    required this.quantity,
    required this.unit,
    this.substitute,
  });

  Map<String, dynamic> toJson() => {
    'name': name,
    'quantity': quantity,
    'unit': unit,
    'substitute': substitute,
  };

  factory RecipeIngredient.fromJson(Map<String, dynamic> json) => RecipeIngredient(
    name: json['name'] as String? ?? '',
    quantity: (json['quantity'] as num?)?.toDouble() ?? 1.0,
    unit: json['unit'] as String? ?? '',
    substitute: json['substitute'] as String?,
  );

  String formatted(double scale) {
    double q = quantity * scale;
    String display = (q == q.toInt()) ? q.toInt().toString() : q.toStringAsFixed(1);
    return unit.isEmpty ? display : '$display $unit';
  }
}

class RecipeStep {
  final int stepNumber;
  final String title;
  final String instruction;
  final int? timerSeconds;

  const RecipeStep({
    required this.stepNumber,
    required this.title,
    required this.instruction,
    this.timerSeconds,
  });

  Map<String, dynamic> toJson() => {
    'stepNumber': stepNumber,
    'title': title,
    'instruction': instruction,
    'timerSeconds': timerSeconds,
  };

  factory RecipeStep.fromJson(Map<String, dynamic> json) => RecipeStep(
    stepNumber: json['stepNumber'] as int? ?? 1,
    title: json['title'] as String? ?? '',
    instruction: json['instruction'] as String? ?? '',
    timerSeconds: json['timerSeconds'] as int?,
  );
}

class Recipe {
  final String id;
  final String title;
  final String description;
  final String region;
  final String categoryName;
  final bool isVegetarian;
  final int prepMinutes;
  final int cookMinutes;
  final int baseServings;
  final int calories;
  final String imageUrl;
  final List<RecipeIngredient> ingredients;
  final List<RecipeStep> instructions;

  const Recipe({
    required this.id,
    required this.title,
    required this.description,
    required this.region,
    required this.categoryName,
    required this.isVegetarian,
    required this.prepMinutes,
    required this.cookMinutes,
    this.baseServings = 4,
    required this.calories,
    required this.imageUrl,
    required this.ingredients,
    required this.instructions,
  });

  int get totalMinutes => prepMinutes + cookMinutes;

  Map<String, dynamic> toJson() => {
    'id': id,
    'title': title,
    'description': description,
    'region': region,
    'categoryName': categoryName,
    'isVegetarian': isVegetarian,
    'prepMinutes': prepMinutes,
    'cookMinutes': cookMinutes,
    'baseServings': baseServings,
    'calories': calories,
    'imageUrl': imageUrl,
    'ingredients': ingredients.map((i) => i.toJson()).toList(),
    'instructions': instructions.map((s) => s.toJson()).toList(),
  };

  factory Recipe.fromJson(Map<String, dynamic> json) => Recipe(
    id: json['id'] as String? ?? '',
    title: json['title'] as String? ?? '',
    description: json['description'] as String? ?? '',
    region: json['region'] as String? ?? 'Traditional India',
    categoryName: json['categoryName'] as String? ?? 'Curries & Dals',
    isVegetarian: json['isVegetarian'] as bool? ?? true,
    prepMinutes: json['prepMinutes'] as int? ?? 15,
    cookMinutes: json['cookMinutes'] as int? ?? 25,
    baseServings: json['baseServings'] as int? ?? 4,
    calories: json['calories'] as int? ?? 300,
    imageUrl: json['imageUrl'] as String? ?? 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
    ingredients: (json['ingredients'] as List<dynamic>?)
            ?.map((i) => RecipeIngredient.fromJson(i as Map<String, dynamic>))
            .toList() ??
        [],
    instructions: (json['instructions'] as List<dynamic>?)
            ?.map((s) => RecipeStep.fromJson(s as Map<String, dynamic>))
            .toList() ??
        [],
  );
}
