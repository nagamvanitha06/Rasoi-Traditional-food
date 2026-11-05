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
  final LocalizedText name;
  final double baseQuantity;
  final LocalizedText unit;
  final String category;
  final LocalizedText? substituteNote;

  const RecipeIngredient({
    required this.name,
    required this.baseQuantity,
    required this.unit,
    required this.category,
    this.substituteNote,
  });

  String formattedQuantity(double scale, AppLanguage lang) {
    double qty = baseQuantity * scale;
    String display = (qty == qty.toInt()) ? qty.toInt().toString() : qty.toStringAsFixed(1);
    String u = unit.get(lang);
    return u.isEmpty ? display : '$display $u';
  }
}

class RecipeStep {
  final int stepNumber;
  final LocalizedText title;
  final LocalizedText instruction;
  final int? timerSeconds;
  final LocalizedText? chefTip;

  const RecipeStep({
    required this.stepNumber,
    required this.title,
    required this.instruction,
    this.timerSeconds,
    this.chefTip,
  });
}

class Recipe {
  final String id;
  final LocalizedText title;
  final LocalizedText description;
  final LocalizedText region;
  final RecipeCategory category;
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
    required this.category,
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
}
