import 'package:flutter/foundation.dart';
import '../models/language.dart';
import '../models/recipe.dart';
import '../services/recipe_service.dart';

// Requirement GP 6: Application data held in at least one Provider store and read by more than one screen.
class RecipeProvider extends ChangeNotifier {
  final RecipeService _service = RecipeService();

  List<Recipe> _recipes = [];
  bool _isLoading = false;
  String? _errorMessage;
  AppLanguage _language = AppLanguage.english;

  final Set<String> _favoriteIds = {'rec_hyderabadi_biryani', 'rec_andhra_pesarattu'};
  final List<String> _shoppingList = [
    'Basmati Rice (500g)',
    'Desi Ghee (250ml)',
    'Cardamom & Saffron',
    'Tamarind Paste',
    'Whole Green Gram (Pesalu)',
  ];

  // Getters
  List<Recipe> get recipes => _recipes;
  bool get isLoading => _isLoading;
  String? get errorMessage => _errorMessage;
  AppLanguage get language => _language;
  Set<String> get favoriteIds => _favoriteIds;
  List<String> get shoppingList => _shoppingList;

  List<Recipe> get favoriteRecipes =>
      _recipes.where((r) => _favoriteIds.contains(r.id)).toList();

  RecipeProvider() {
    fetchRecipes();
  }

  void setLanguage(AppLanguage lang) {
    _language = lang;
    notifyListeners();
  }

  // Requirement GP 2 & GP 3: Fetches real data and manages 4 screen states (Loading, Error, Empty, Data)
  Future<void> fetchRecipes() async {
    _isLoading = true;
    _errorMessage = null;
    notifyListeners();

    try {
      // 1. Fetch persistent user recipes from device storage (GP 2)
      final userRecipes = await _service.loadUserRecipes();

      // 2. Fetch live data from public API over internet (GP 2)
      List<Recipe> apiRecipes = [];
      try {
        apiRecipes = await _service.fetchRecipesFromApi();
      } catch (_) {
        // If network is offline, continue with local user recipes or default items
      }

      final combined = [...userRecipes, ...apiRecipes];

      // Fallback seed recipes (All 32 authentic traditional dishes)
      if (combined.isEmpty) {
        combined.addAll(_thirtyTwoTraditionalRecipes);
      }

      _recipes = combined;
      _isLoading = false;
      _errorMessage = null;
      notifyListeners();
    } catch (e) {
      _isLoading = false;
      _errorMessage = 'Could not load recipes. Please check your internet connection and retry.';
      notifyListeners();
    }
  }

  // Force trigger an error state for testing retry flow (GP 3)
  void simulateNetworkError() {
    _isLoading = false;
    _errorMessage = 'Network connection failed. Tap Retry to reconnect.';
    notifyListeners();
  }

  // Requirement GP 4 & GP 2: Add user validated recipe and persist to device
  Future<void> addRecipe(Recipe newRecipe) async {
    await _service.saveUserRecipe(newRecipe);
    _recipes.insert(0, newRecipe);
    notifyListeners();
  }

  void toggleFavorite(String id) {
    if (_favoriteIds.contains(id)) {
      _favoriteIds.remove(id);
    } else {
      _favoriteIds.add(id);
    }
    notifyListeners();
  }

  void addToShopping(String item) {
    _shoppingList.add(item);
    notifyListeners();
  }

  void removeFromShopping(int index) {
    if (index >= 0 && index < _shoppingList.length) {
      _shoppingList.removeAt(index);
      notifyListeners();
    }
  }

  // 32 Authentic Traditional Heritage Dishes
  static final List<Recipe> _thirtyTwoTraditionalRecipes = [
    const Recipe(
      id: 'rec_hyderabadi_biryani',
      title: 'Hyderabadi Dum Biryani (హైదరాబాదీ బిర్యానీ)',
      description: 'Centuries-old Nizami heritage rice cooked with saffron milk, caramel onions, and aromatic whole spices.',
      region: 'Hyderabad, Telangana',
      categoryName: 'Rice & Biryani',
      isVegetarian: false,
      prepMinutes: 35,
      cookMinutes: 45,
      calories: 580,
      imageUrl: 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800',
      ingredients: [
        RecipeIngredient(name: 'Aged Basmati Rice', quantity: 500, unit: 'g'),
        RecipeIngredient(name: 'Pure Desi Ghee', quantity: 4, unit: 'tbsp'),
        RecipeIngredient(name: 'Saffron Strands', quantity: 1, unit: 'pinch'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Marination', instruction: 'Marinate pieces with spiced curd, mint, and garam masala.', timerSeconds: 1800),
        RecipeStep(stepNumber: 2, title: 'Dum Cooking', instruction: 'Layer parboiled rice, seal with dough, and cook on low heat for 25 mins.', timerSeconds: 1500),
      ],
    ),
    const Recipe(
      id: 'rec_andhra_pesarattu',
      title: 'Andhra Pesarattu & Allam Chutney (పెసరట్టు)',
      description: 'Crispy whole green gram crepe served with spicy-sweet ginger jaggery pachadi.',
      region: 'Andhra Pradesh',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 15,
      calories: 290,
      imageUrl: 'https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800',
      ingredients: [
        RecipeIngredient(name: 'Whole Green Gram (Pesalu)', quantity: 2, unit: 'cups'),
        RecipeIngredient(name: 'Fresh Ginger Root', quantity: 2, unit: 'inches'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Batter Preparation', instruction: 'Grind soaked green gram with ginger and green chilies into a smooth batter.', timerSeconds: 300),
        RecipeStep(stepNumber: 2, title: 'Roasting', instruction: 'Pour on tawa, top with onions, roast with ghee until golden crisp.', timerSeconds: 180),
      ],
    ),
    const Recipe(
      id: 'rec_gutti_vankaya',
      title: 'Gutti Vankaya Kura (గుత్తి వంకాయ కూర)',
      description: 'Royal Andhra baby brinjals stuffed with roasted peanut, sesame, and dry coconut paste.',
      region: 'Rayalaseema, Andhra',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 25,
      cookMinutes: 30,
      calories: 340,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [
        RecipeIngredient(name: 'Tender Brinjals (Vankaya)', quantity: 500, unit: 'g'),
        RecipeIngredient(name: 'Roasted Peanuts & Sesame', quantity: 0.5, unit: 'cup'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Stuff Masala', instruction: 'Slit brinjals into four quarters and stuff generously with roasted masala paste.'),
        RecipeStep(stepNumber: 2, title: 'Simmer', instruction: 'Simmer in oil on low heat until tender and oil separates.', timerSeconds: 1200),
      ],
    ),
    const Recipe(
      id: 'rec_dal_makhani',
      title: 'Slow-Cooked Punjabi Dal Makhani (दाल मखनी)',
      description: 'Whole black urad and rajma simmered with white butter, cream, and tomatoes.',
      region: 'Punjab',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 60,
      calories: 420,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [
        RecipeIngredient(name: 'Black Urad Dal', quantity: 1, unit: 'cup'),
        RecipeIngredient(name: 'White Butter & Cream', quantity: 4, unit: 'tbsp'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Pressure Cook', instruction: 'Cook soaked lentils until tender.', timerSeconds: 900),
        RecipeStep(stepNumber: 2, title: 'Slow Simmer', instruction: 'Simmer with tomato puree and butter for 45 minutes.', timerSeconds: 2700),
      ],
    ),
    const Recipe(
      id: 'rec_temple_pulihora',
      title: 'Kovil Pulihora / Tamarind Rice (గుడి పులిహోర)',
      description: 'Temple prasadam tamarind rice infused with crunchy peanuts, mustard, curry leaves, and hing.',
      region: 'Tirupati, Andhra',
      categoryName: 'Festival Specials',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 25,
      calories: 380,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [
        RecipeIngredient(name: 'Cooked Rice', quantity: 3, unit: 'cups'),
        RecipeIngredient(name: 'Tamarind Pulp', quantity: 0.5, unit: 'cup'),
        RecipeIngredient(name: 'Raw Peanuts', quantity: 0.33, unit: 'cup'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Cook Pulp', instruction: 'Simmer tamarind with turmeric and chilies until thick.', timerSeconds: 600),
        RecipeStep(stepNumber: 2, title: 'Tempering', instruction: 'Roast peanuts and mustard; fold gently into rice.', timerSeconds: 300),
      ],
    ),
    const Recipe(
      id: 'rec_royal_gulab_jamun',
      title: 'Royal Saffron Gulab Jamun (గులాబ్ జామున్)',
      description: 'Melt-in-mouth mawa and paneer dumplings fried in pure ghee, soaked in rose saffron syrup.',
      region: 'Heritage India',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 25,
      calories: 320,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [
        RecipeIngredient(name: 'Soft Khoya / Mawa', quantity: 200, unit: 'g'),
        RecipeIngredient(name: 'Sugar & Saffron', quantity: 1.75, unit: 'cups'),
      ],
      instructions: [
        RecipeStep(stepNumber: 1, title: 'Syrup', instruction: 'Boil sugar and water with cardamom until sticky.', timerSeconds: 480),
        RecipeStep(stepNumber: 2, title: 'Fry and Soak', instruction: 'Slow fry in ghee until deep golden and soak in warm syrup.', timerSeconds: 600),
      ],
    ),
    const Recipe(
      id: 'rec_masala_dosa',
      title: 'Traditional Masala Dosa (మసాలా దోశ)',
      description: 'Crisp golden crepe wrapped around savory spiced potato filling, served with coconut chutney.',
      region: 'Karnataka & South India',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 15,
      calories: 310,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'Dosa Batter', quantity: 3, unit: 'cups'), RecipeIngredient(name: 'Boiled Potatoes', quantity: 4, unit: 'pcs')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Roast Dosa', instruction: 'Pour batter, roast with butter, add potato filling, fold.', timerSeconds: 180)],
    ),
    const Recipe(
      id: 'rec_butter_chicken',
      title: 'Murgh Makhani / Butter Chicken (బట్టర్ చికెన్)',
      description: 'Tender chicken cooked in rich spiced tomato, cashew nut, and cream gravy.',
      region: 'Delhi / Punjab',
      categoryName: 'Curries & Dals',
      isVegetarian: false,
      prepMinutes: 30,
      cookMinutes: 35,
      calories: 520,
      imageUrl: 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800',
      ingredients: [RecipeIngredient(name: 'Chicken pieces', quantity: 600, unit: 'g'), RecipeIngredient(name: 'Butter & Cream', quantity: 4, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Simmer Gravy', instruction: 'Simmer chicken in buttery tomato cashew gravy.', timerSeconds: 1200)],
    ),
    const Recipe(
      id: 'rec_paneer_butter_masala',
      title: 'Paneer Butter Masala (పనీర్ బట్టర్ మసాలా)',
      description: 'Cubes of fresh paneer in velvety tomato-butter gravy with kasuri methi.',
      region: 'North India',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 20,
      calories: 380,
      imageUrl: 'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800',
      ingredients: [RecipeIngredient(name: 'Fresh Paneer', quantity: 300, unit: 'g'), RecipeIngredient(name: 'Makhani Puree', quantity: 1.5, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Simmer', instruction: 'Cook paneer in tomato cream sauce with fenugreek.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_ragi_sangati',
      title: 'Rayalaseema Ragi Sangati (రాగి సంగటి)',
      description: 'Nutritious finger millet balls served with fiery traditional country gravy.',
      region: 'Rayalaseema, Andhra',
      categoryName: 'Festival Specials',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 30,
      calories: 310,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Ragi Flour', quantity: 1.5, unit: 'cups'), RecipeIngredient(name: 'Soft Rice', quantity: 1, unit: 'cup')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Steam & Mix', instruction: 'Cook ragi flour with rice and shape into smooth balls.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_kerala_avial',
      title: 'Kerala Avial (కేరళ అవియల్)',
      description: 'Traditional mixed vegetables cooked in coconut, cumin, yogurt, and cold-pressed coconut oil.',
      region: 'Kerala',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 20,
      calories: 220,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Mixed Veggies', quantity: 400, unit: 'g'), RecipeIngredient(name: 'Grated Coconut', quantity: 1, unit: 'cup')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Cook & Toss', instruction: 'Steam veggies and fold coconut paste with yogurt and coconut oil.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_chettinad_pepper',
      title: 'Chettinad Pepper Masala (చెట్టినాడు మసాలా)',
      description: 'Fiery Tamil Nadu delicacy roasted with freshly crushed black peppercorns and shallots.',
      region: 'Chettinad, Tamil Nadu',
      categoryName: 'Curries & Dals',
      isVegetarian: false,
      prepMinutes: 20,
      cookMinutes: 30,
      calories: 410,
      imageUrl: 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800',
      ingredients: [RecipeIngredient(name: 'Chicken/Mushroom', quantity: 500, unit: 'g'), RecipeIngredient(name: 'Black Peppercorns', quantity: 2, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Roast & Sauté', instruction: 'Roast pepper masala and sauté with small shallots.', timerSeconds: 1200)],
    ),
    const Recipe(
      id: 'rec_dal_baati',
      title: 'Rajasthani Dal Baati Churma (దాల్ బాటీ చుర్మా)',
      description: 'Crisp baked wheat balls soaked in desi ghee with spicy panchmel dal.',
      region: 'Rajasthan',
      categoryName: 'Festival Specials',
      isVegetarian: true,
      prepMinutes: 25,
      cookMinutes: 40,
      calories: 590,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Wheat Flour', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Panchmel Dal', quantity: 1.5, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Bake Baati', instruction: 'Bake baatis until crackly and dip in melted ghee.', timerSeconds: 1500)],
    ),
    const Recipe(
      id: 'rec_gujarati_dhokla',
      title: 'Gujarati Khaman Dhokla (ఖమన్ ధోక్లా)',
      description: 'Steamed spongy gram flour cakes tempered with mustard seeds and green chilies.',
      region: 'Gujarat',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 20,
      calories: 180,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Gram Flour (Besan)', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Mustard & Chilies', quantity: 1, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Steam', instruction: 'Steam batter 18 mins and pour mustard-sugar water seasoning.', timerSeconds: 1080)],
    ),
    const Recipe(
      id: 'rec_kashmiri_rogan_josh',
      title: 'Kashmiri Rogan Josh (రోగన్ జోష్)',
      description: 'Slow-cooked curry infused with fennel powder, dry ginger (sonth), and Kashmiri chilies.',
      region: 'Kashmir Valley',
      categoryName: 'Curries & Dals',
      isVegetarian: false,
      prepMinutes: 20,
      cookMinutes: 50,
      calories: 480,
      imageUrl: 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800',
      ingredients: [RecipeIngredient(name: 'Meat/Mushroom', quantity: 600, unit: 'g'), RecipeIngredient(name: 'Fennel & Ginger Powder', quantity: 2, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Slow Braise', instruction: 'Braise with curd, sonth, and whole spices until tender.', timerSeconds: 2700)],
    ),
    const Recipe(
      id: 'rec_bengali_shorshe',
      title: 'Bengali Shorshe Curry (బెంగాలీ ఆవాల కూర)',
      description: 'Pungent traditional mustard paste curry finished with pure virgin mustard oil.',
      region: 'Bengal',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 20,
      calories: 310,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Paneer or Fish', quantity: 400, unit: 'g'), RecipeIngredient(name: 'Mustard Paste', quantity: 3, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Simmer', instruction: 'Simmer with green chilies and raw mustard oil.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_andhra_gongura',
      title: 'Andhra Gongura Pachadi & Rice (గోంగూర పచ్చడి)',
      description: 'The king of Andhra pickles: tangy sorrel leaves with garlic, red chilies, and sesame oil.',
      region: 'Guntur, Andhra',
      categoryName: 'Festival Specials',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 15,
      calories: 190,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Gongura Leaves', quantity: 4, unit: 'bunches'), RecipeIngredient(name: 'Red Chilies & Garlic', quantity: 15, unit: 'pcs')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Sauté and Grind', instruction: 'Wilt gongura, fry chilies and garlic, grind into coarse pachadi.', timerSeconds: 480)],
    ),
    const Recipe(
      id: 'rec_hyderabadi_haleem',
      title: 'Hyderabadi Shahi Haleem (హైదరాబాదీ హలీమ్)',
      description: 'Slow-cooked stew of broken wheat, lentils, ghee, and meat pounded with wooden pestles.',
      region: 'Hyderabad',
      categoryName: 'Festival Specials',
      isVegetarian: false,
      prepMinutes: 40,
      cookMinutes: 90,
      calories: 540,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Broken Wheat', quantity: 1.5, unit: 'cups'), RecipeIngredient(name: 'Pure Desi Ghee', quantity: 0.5, unit: 'cup')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Pound & Simmer', instruction: 'Slow cook and pound until silky and elastic.', timerSeconds: 3600)],
    ),
    const Recipe(
      id: 'rec_medu_vada',
      title: 'Crispy Medu Vada (మేదు వడ / గారెలు)',
      description: 'Golden crispy urad dal fritters fluffy on the inside with black pepper, curry leaves, and ginger.',
      region: 'South India',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 20,
      calories: 260,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'Urad Dal', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Black Pepper & Ginger', quantity: 1, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Fry Vadas', instruction: 'Shape with hole in center, fry in hot oil until crisp.', timerSeconds: 480)],
    ),
    const Recipe(
      id: 'rec_idli_sambar',
      title: 'Steamed Idli & Sambar (మల్లెపూవు లాంటి ఇడ్లీ)',
      description: 'Pillowy soft fermented rice cakes served with piping hot lentil-vegetable stew.',
      region: 'Tamil Nadu & Andhra',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 15,
      calories: 210,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'Idli Batter', quantity: 3, unit: 'cups'), RecipeIngredient(name: 'Toor Dal for Sambar', quantity: 1, unit: 'cup')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Steam', instruction: 'Steam in idli molds for 10-12 mins.', timerSeconds: 720)],
    ),
    const Recipe(
      id: 'rec_chole_bhature',
      title: 'Amritsari Chole Bhature (చోలే భటూరే)',
      description: 'Spicy chickpeas cooked with dried pomegranate and tea infusion, paired with puffy bhature.',
      region: 'Punjab',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 35,
      calories: 560,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'Kabuli Chana', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Chole Masala', quantity: 2, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Cook Chole', instruction: 'Simmer chickpeas with anardana and chole masala.', timerSeconds: 1500)],
    ),
    const Recipe(
      id: 'rec_palak_paneer',
      title: 'Desi Palak Paneer (పాలక్ పనీర్)',
      description: 'Vibrant pureed spinach gravy with garlic and tender paneer cubes.',
      region: 'North India',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 20,
      calories: 290,
      imageUrl: 'https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800',
      ingredients: [RecipeIngredient(name: 'Fresh Spinach', quantity: 3, unit: 'bunches'), RecipeIngredient(name: 'Paneer cubes', quantity: 250, unit: 'g')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Blanch & Cook', instruction: 'Blanch spinach, puree, and simmer with paneer and garlic.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_malabar_parotta',
      title: 'Malabar Layered Parotta & Kurma (మలబార్ పరోటా)',
      description: 'Flaky layered Kerala flatbread paired with spiced coconut vegetable kurma.',
      region: 'Kerala',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 30,
      cookMinutes: 25,
      calories: 420,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'All-purpose flour', quantity: 2.5, unit: 'cups'), RecipeIngredient(name: 'Coconut milk', quantity: 1.5, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Layer & Cook', instruction: 'Pleat dough and roast with ghee until flaky.', timerSeconds: 480)],
    ),
    const Recipe(
      id: 'rec_pepper_rasam',
      title: 'Pepper Garlic Rasam (మిరియాల రసం)',
      description: 'Digestive and aromatic South Indian broth with black pepper, cumin, garlic, and tamarind.',
      region: 'South India',
      categoryName: 'Curries & Dals',
      isVegetarian: true,
      prepMinutes: 10,
      cookMinutes: 15,
      calories: 95,
      imageUrl: 'https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800',
      ingredients: [RecipeIngredient(name: 'Tamarind Water', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Crushed Black Pepper', quantity: 1.5, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Froth', instruction: 'Boil tamarind with pepper powder; remove as soon as frothy.', timerSeconds: 480)],
    ),
    const Recipe(
      id: 'rec_bisi_bele_bath',
      title: 'Karnataka Bisi Bele Bath (కర్ణాటక బిసిబేళె బాత్)',
      description: 'Rice, lentils, and garden vegetables cooked in freshly ground spice powder and ghee.',
      region: 'Karnataka',
      categoryName: 'Rice & Biryani',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 30,
      calories: 380,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Rice & Toor Dal', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Bisi Bele Bath Masala', quantity: 3, unit: 'tbsp')],
      instructions: [RecipeStep(stepNumber: 1, title: 'One Pot Cook', instruction: 'Cook dal-rice, add spice paste and ghee-roasted cashews.', timerSeconds: 1200)],
    ),
    const Recipe(
      id: 'rec_kaju_katli',
      title: 'Silver Kaju Katli (కాజూ కత్లీ)',
      description: 'Melt-in-mouth cashew fudge diamonds flavored with cardamom and sugar syrup.',
      region: 'Heritage India',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 15,
      calories: 240,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Cashew Powder', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Sugar Syrup', quantity: 1, unit: 'cup')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Roll & Cut', instruction: 'Stir cashew powder in sugar syrup, roll thin, cut diamonds.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_mysore_pak',
      title: 'Traditional Mysore Pak (మైసూరు పాక్)',
      description: 'Royal palace fudge made with roasted gram flour, sugar syrup, and sizzling hot ghee.',
      region: 'Karnataka',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 10,
      cookMinutes: 20,
      calories: 380,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Besan (Gram Flour)', quantity: 1, unit: 'cup'), RecipeIngredient(name: 'Pure Desi Ghee', quantity: 1.5, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Add Ghee', instruction: 'Pour hot ghee in stream while stirring besan syrup until porous.', timerSeconds: 900)],
    ),
    const Recipe(
      id: 'rec_double_ka_meetha',
      title: 'Double Ka Meetha (డబుల్ కా మీఠా)',
      description: 'Ghee-fried bread soaked in cardamom sugar syrup and rich saffron rabri.',
      region: 'Hyderabad',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 25,
      calories: 390,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Bread Slices', quantity: 6, unit: 'pcs'), RecipeIngredient(name: 'Saffron Milk (Rabri)', quantity: 2, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Soak in Rabri', instruction: 'Fry bread in ghee and pour cardamom rabri.', timerSeconds: 600)],
    ),
    const Recipe(
      id: 'rec_gajar_halwa',
      title: 'Traditional Gajar Ka Halwa (క్యారెట్ హల్వా)',
      description: 'Red winter carrots slow-simmered in milk, khoya, cardamom, and roasted cashews.',
      region: 'Punjab & North India',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 40,
      calories: 340,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Grated Carrots', quantity: 1, unit: 'kg'), RecipeIngredient(name: 'Full Cream Milk', quantity: 1, unit: 'liter')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Roast with Khoya', instruction: 'Simmer carrots in milk until thick, roast with ghee and khoya.', timerSeconds: 2400)],
    ),
    const Recipe(
      id: 'rec_poori_masala',
      title: 'Puffed Poori & Aloo Saagu (పూరీ & ఆలూ సాగు)',
      description: 'Golden puffy whole wheat pooris served with mild potato-onion curry.',
      region: 'Heritage India',
      categoryName: 'Tiffins & Breakfast',
      isVegetarian: true,
      prepMinutes: 20,
      cookMinutes: 20,
      calories: 420,
      imageUrl: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800',
      ingredients: [RecipeIngredient(name: 'Wheat Flour', quantity: 2, unit: 'cups'), RecipeIngredient(name: 'Potatoes & Onions', quantity: 3, unit: 'pcs')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Puff in Oil', instruction: 'Deep fry pooris until puffed golden.', timerSeconds: 480)],
    ),
    const Recipe(
      id: 'rec_semiya_payasam',
      title: 'Saffron Semiya Payasam / Kheer (సేమియా పాయసం)',
      description: 'Ghee-roasted vermicelli in sweet creamy milk with saffron, cardamom, and fried nuts.',
      region: 'South & North India',
      categoryName: 'Sweets & Snacks',
      isVegetarian: true,
      prepMinutes: 10,
      cookMinutes: 18,
      calories: 280,
      imageUrl: 'https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800',
      ingredients: [RecipeIngredient(name: 'Roasted Vermicelli', quantity: 1, unit: 'cup'), RecipeIngredient(name: 'Milk & Saffron', quantity: 4, unit: 'cups')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Simmer', instruction: 'Cook vermicelli in milk, add sugar and saffron.', timerSeconds: 720)],
    ),
    const Recipe(
      id: 'rec_gongura_mutton',
      title: 'Andhra Gongura Mutton / Chicken (గోంగూర మాంసం)',
      description: 'Celebrated Andhra wedding delicacy: meat braised with sour red gongura leaves and garlic.',
      region: 'Andhra Pradesh',
      categoryName: 'Curries & Dals',
      isVegetarian: false,
      prepMinutes: 25,
      cookMinutes: 45,
      calories: 510,
      imageUrl: 'https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800',
      ingredients: [RecipeIngredient(name: 'Meat pieces', quantity: 600, unit: 'g'), RecipeIngredient(name: 'Gongura leaves', quantity: 3, unit: 'bunches')],
      instructions: [RecipeStep(stepNumber: 1, title: 'Simmer', instruction: 'Cook meat with spices and fold wilted gongura paste.', timerSeconds: 2400)],
    ),
  ];
}
