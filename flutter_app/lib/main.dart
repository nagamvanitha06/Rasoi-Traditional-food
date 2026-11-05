import 'package:flutter/material.dart';
import 'models/language.dart';
import 'models/recipe.dart';

void main() {
  runApp(const RasoiApp());
}

class RasoiApp extends StatefulWidget {
  const RasoiApp({super.key});

  @override
  State<RasoiApp> createState() => _RasoiAppState();
}

class _RasoiAppState extends State<RasoiApp> {
  AppLanguage _currentLanguage = AppLanguage.english;

  void _setLanguage(AppLanguage lang) {
    setState(() {
      _currentLanguage = lang;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Rasoi Traditional Recipes',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorScheme: ColorScheme.fromSeed(
          seedColor: const Color(0xFFBC3814),
          primary: const Color(0xFFBC3814),
          secondary: const Color(0xFFEAA11A),
          surface: Colors.white,
        ),
      ),
      home: MainShellScreen(
        language: _currentLanguage,
        onLanguageChanged: _setLanguage,
      ),
    );
  }
}

class MainShellScreen extends StatefulWidget {
  final AppLanguage language;
  final ValueChanged<AppLanguage> onLanguageChanged;

  const MainShellScreen({
    super.key,
    required this.language,
    required this.onLanguageChanged,
  });

  @override
  State<MainShellScreen> createState() => _MainShellScreenState();
}

class _MainShellScreenState extends State<MainShellScreen> {
  int _currentIndex = 0;
  final Set<String> _favorites = {'biryani', 'pesarattu'};
  final List<String> _shoppingList = [
    'Basmati Rice (500g)',
    'Whole Green Gram (2 cups)',
    'Desi Ghee (250ml)',
    'Cardamom & Saffron',
    'Tamarind Paste'
  ];

  final List<Recipe> _recipes = [
    Recipe(
      id: 'biryani',
      title: LocalizedText(
        en: 'Hyderabadi Dum Biryani',
        hi: 'शाही हैदराबादी दम बिरयानी',
        te: 'హైదరాబాదీ దమ్ బిర్యానీ',
      ),
      description: LocalizedText(
        en: 'Centuries-old Nizami heritage rice cooked with saffron milk and aromatic spices.',
        hi: 'केसर, पुदीना और मसालों में पकी हुई शाही निज़ामी दम बिरयानी।',
        te: 'సువాసనగల బాస్మతి బియ్యం, వేయించిన ఉల్లిపాయలు, కుంకుమపువ్వు పాలతో దమ్ చేసిన నిజామీ బిర్యానీ.',
      ),
      region: LocalizedText(en: 'Hyderabad, Telangana', hi: 'हैदराबाद, तेलंगाना', te: 'హైదరాబాద్, తెలంగాణ'),
      category: RecipeCategory.biryani,
      isVegetarian: false,
      prepMinutes: 30,
      cookMinutes: 45,
      calories: 580,
      imageUrl: 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&auto=format&fit=crop&q=80',
      ingredients: [
        RecipeIngredient(
          name: LocalizedText(en: 'Aged Basmati Rice', hi: 'बासमती चावल', te: 'బాస్మతి బియ్యం'),
          baseQuantity: 500,
          unit: LocalizedText(en: 'g', hi: 'ग्राम', te: 'గ్రా'),
          category: 'Grains',
        ),
        RecipeIngredient(
          name: LocalizedText(en: 'Thick Curd / Dahi', hi: 'गाढ़ा दही', te: 'చిక్కటి పెరుగు'),
          baseQuantity: 1,
          unit: LocalizedText(en: 'cup', hi: 'कप', te: 'కప్పు'),
          category: 'Dairy',
          substituteNote: LocalizedText(en: 'Cashew cream + lemon', hi: 'काजू पेस्ट + नींबू', te: 'జీడిపప్పు పేస్ట్ + నిమ్మరసం'),
        ),
        RecipeIngredient(
          name: LocalizedText(en: 'Pure Desi Ghee', hi: 'देसी घी', te: 'స్వచ్ఛమైన నెయ్యి'),
          baseQuantity: 4,
          unit: LocalizedText(en: 'tbsp', hi: 'चम्मच', te: 'టేబుల్ స్పూన్లు'),
          category: 'Dairy',
        ),
      ],
      instructions: [
        RecipeStep(
          stepNumber: 1,
          title: LocalizedText(en: 'Marination', hi: 'मैरीनेशन', te: 'మ్యారినేషన్'),
          instruction: LocalizedText(
            en: 'Mix ingredients with curd, spices, and mint. Rest 30 minutes.',
            hi: 'दही, मसालों और पुदीने के साथ 30 मिनट तक मैरीनेट करें।',
            te: 'పెరుగు, మసాలాలు, పుదీనాతో 30 నిమిషాలు నానబెట్టండి.',
          ),
          timerSeconds: 1800,
        ),
        RecipeStep(
          stepNumber : 2,
          title: LocalizedText(en: 'Dum Cooking', hi: 'दम पर पकाना', te: 'దమ్ చేయడం'),
          instruction: LocalizedText(
            en: 'Layer rice and cook sealed on low flame for 25 minutes.',
            hi: 'चावल की परत बिछाकर धीमी आंच पर 25 मिनट दम दें।',
            te: 'అన్నం పొరలు వేసి చిన్న మంటపై 25 నిమిషాలు దమ్ చేయండి.',
          ),
          timerSeconds: 1500,
        ),
      ],
    ),
    Recipe(
      id: 'pesarattu',
      title: LocalizedText(
        en: 'Andhra Pesarattu & Allam Chutney',
        hi: 'आंध्र पेसरट्टू और अदरक चटनी',
        te: 'ఆంధ్ర పెసరట్టు & అల్లం పచ్చడి',
      ),
      description: LocalizedText(
        en: 'Crispy whole green gram crepe served with tangy ginger-jaggery chutney.',
        hi: 'साबुत हरी मूंग दाल से बना कुरकुरा और पौष्टिक पेसरट्टू।',
        te: 'పొట్టు పెసలతో చేసిన కరకరలాడే సాంప్రదాయ పెసరట్టు, అల్లం పచ్చడితో.',
      ),
      region: LocalizedText(en: 'Andhra Pradesh', hi: 'आंध्र प्रदेश', te: 'ఆంధ్ర ప్రదేశ్'),
      category: RecipeCategory.breakfast,
      isVegetarian: true,
      prepMinutes: 15,
      cookMinutes: 15,
      calories: 290,
      imageUrl: 'https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800&auto=format&fit=crop&q=80',
      ingredients: [
        RecipeIngredient(
          name: LocalizedText(en: 'Whole Green Gram (Pesalu)', hi: 'साबुत मूंग', te: 'పొట్టు పెసలు'),
          baseQuantity: 2,
          unit: LocalizedText(en: 'cups', hi: 'कप', te: 'కప్పులు'),
          category: 'Grains',
        ),
        RecipeIngredient(
          name: LocalizedText(en: 'Fresh Ginger root', hi: 'अदरक', te: 'అల్లం'),
          baseQuantity: 2,
          unit: LocalizedText(en: 'inches', hi: 'इंच', te: 'అంగుళాలు'),
          category: 'Produce',
        ),
      ],
      instructions: [
        RecipeStep(
          stepNumber: 1,
          title: LocalizedText(en: 'Grind Batter', hi: 'घोल पीसना', te: 'పిండి రుబ్బడం'),
          instruction: LocalizedText(
            en: 'Grind soaked green gram with ginger and green chilies into a smooth batter.',
            hi: 'भीगी मूंग, अदरक और हरी मिर्च को पीसकर घोल तैयार करें।',
            te: 'నానబెట్టిన పెసలను అల్లం, పచ్చిమిర్చితో రుబ్బండి.',
          ),
        ),
        RecipeStep(
          stepNumber: 2,
          title: LocalizedText(en: 'Roast on Tawa', hi: 'तवे पर सेंकना', te: 'పెనంపై కాల్చడం'),
          instruction: LocalizedText(
            en: 'Pour batter, sprinkle onions and cumin, roast until golden crisp with ghee.',
            hi: 'घोल फैलाकर प्याज और जीरा डालें, घी लगाकर कुरकुरा सेंकें।',
            te: 'పెనంపై పిండి వేసి ఉల్లిపాయలు, జీలకర్ర, నెయ్యితో కరకరలాడేలా కాల్చండి.',
          ),
          timerSeconds: 180,
        ),
      ],
    ),
  ];

  void _toggleFavorite(String id) {
    setState(() {
      if (_favorites.contains(id)) {
        _favorites.remove(id);
      } else {
        _favorites.add(id);
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    final lang = widget.language;

    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFFBC3814),
        foregroundColor: Colors.white,
        title: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Rasoi • रसोई • రసోయ్',
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
            ),
            Text(
              lang == AppLanguage.telugu
                  ? 'సాంప్రదాయ వంటకాలు'
                  : lang == AppLanguage.hindi
                      ? 'पारंपरिक भारतीय व्यंजन'
                      : 'Authentic Traditional Recipes',
              style: const TextStyle(fontSize: 11, color: Colors.white70),
            ),
          ],
        ),
        actions: [
          PopupMenuButton<AppLanguage>(
            icon: Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              decoration: BoxDecoration(
                color: Colors.white24,
                borderRadius: BorderRadius.circular(16),
              ),
              child: Text(
                lang.nativeName,
                style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 12),
              ),
            ),
            onSelected: widget.onLanguageChanged,
            itemBuilder: (context) => AppLanguage.values.map((l) {
              return PopupMenuItem(
                value: l,
                child: Text('${l.nativeName} (${l.displayName})'),
              );
            }).toList(),
          ),
        ],
      ),
      body: _buildCurrentTab(),
      bottomNavigationBar: NavigationBar(
        selectedIndex: _currentIndex,
        onDestinationSelected: (idx) => setState(() => _currentIndex = idx),
        destinations: [
          NavigationDestination(
            icon: const Icon(Icons.home),
            label: lang == AppLanguage.telugu ? 'హోమ్' : lang == AppLanguage.hindi ? 'होम' : 'Home',
          ),
          NavigationDestination(
            icon: const Icon(Icons.swap_horiz),
            label: lang == AppLanguage.telugu ? 'ప్రత్యామ్నాయాలు' : lang == AppLanguage.hindi ? 'विकल्प' : 'Substitutes',
          ),
          NavigationDestination(
            icon: const Icon(Icons.calendar_month),
            label: lang == AppLanguage.telugu ? 'మీల్ ప్లాన్' : lang == AppLanguage.hindi ? 'मील प्लान' : 'Planner',
          ),
          NavigationDestination(
            icon: Badge(
              label: Text('${_shoppingList.length}'),
              child: const Icon(Icons.shopping_cart),
            ),
            label: lang == AppLanguage.telugu ? 'షాపింగ్' : lang == AppLanguage.hindi ? 'खरीदारी' : 'Grocery',
          ),
          NavigationDestination(
            icon: const Icon(Icons.favorite),
            label: lang == AppLanguage.telugu ? 'ఇష్టమైనవి' : lang == AppLanguage.hindi ? 'पसंदीदा' : 'Favorites',
          ),
        ],
      ),
    );
  }

  Widget _buildCurrentTab() {
    switch (_currentIndex) {
      case 0:
        return _buildHomeTab();
      case 1:
        return _buildSubstitutionsTab();
      case 2:
        return _buildPlannerTab();
      case 3:
        return _buildGroceryTab();
      case 4:
        return _buildFavoritesTab();
      default:
        return _buildHomeTab();
    }
  }

  Widget _buildHomeTab() {
    final lang = widget.language;
    return ListView(
      padding: const EdgeInsets.all(16),
      children: [
        // Hero Card
        Container(
          padding: const EdgeInsets.all(20),
          decoration: BoxDecoration(
            gradient: const LinearGradient(
              colors: [Color(0xFFBC3814), Color(0xFFEAA11A)],
            ),
            borderRadius: BorderRadius.circular(20),
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              const Text(
                'TRADITIONAL FLAVORS',
                style: TextStyle(color: Colors.white70, fontSize: 11, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8),
              Text(
                lang == AppLanguage.telugu
                    ? 'మన ప్రాచీన తెలుగు & భారతీయ వంటల వైభవం'
                    : lang == AppLanguage.hindi
                        ? 'भारत के असली पारंपरिक शाही स्वाद'
                        : 'Heritage Recipes with Voice Cooking & Substitutes',
                style: const TextStyle(color: Colors.white, fontSize: 20, fontWeight: FontWeight.bold),
              ),
            ],
          ),
        ),
        const SizedBox(height: 20),
        Text(
          lang == AppLanguage.telugu ? 'ప్రసిద్ధ వంటకాలు' : lang == AppLanguage.hindi ? 'प्रसिद्ध व्यंजन' : 'Featured Heritage Recipes',
          style: const TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
        ),
        const SizedBox(height: 12),
        ..._recipes.map((r) => _buildRecipeCard(r)),
      ],
    );
  }

  Widget _buildRecipeCard(Recipe recipe) {
    final lang = widget.language;
    final isFav = _favorites.contains(recipe.id);

    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      shape: RoundedRectangleBorder(
  borderRadius: BorderRadius.circular(16),
),
      clipBehavior: Clip.antiAlias,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Image.network(recipe.imageUrl, height: 160, width: double.infinity, fit: BoxFit.cover),
          Padding(
            padding: const EdgeInsets.all(14),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      recipe.title.get(lang),
                      style: const TextStyle(fontSize: 17, fontWeight: FontWeight.bold),
                    ),
                    IconButton(
                      icon: Icon(isFav ? Icons.favorite : Icons.favorite_border, color: Colors.red),
                      onPressed: () => _toggleFavorite(recipe.id),
                    ),
                  ],
                ),
                Text(
                  recipe.description.get(lang),
                  style: const TextStyle(fontSize: 13, color: Colors.black54),
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Chip(
                      label: Text('${recipe.totalMinutes} min'),
                      avatar: const Icon(Icons.timer, size: 16),
                    ),
                    const SizedBox(width: 8),
                    Chip(label: Text('${recipe.calories} kcal')),
                  ],
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildSubstitutionsTab() {
    final subs = [
      {'orig': 'Tamarind (చింతపండు / इमली)', 'sub': 'Amchur (Dry Mango) or Lemon Juice', 'ratio': '1 tbsp tamarind = 1 tsp amchur'},
      {'orig': 'Desi Ghee (నెయ్యి / घी)', 'sub': 'Cold-Pressed Mustard Oil or Coconut Oil', 'ratio': '1:1 ratio'},
      {'orig': 'Fresh Paneer (పనీర్ / पनीर)', 'sub': 'Extra Firm Pressed Tofu', 'ratio': '1:1 ratio'},
      {'orig': 'Curry Leaves (కరివేపాకు / कढ़ी पत्ता)', 'sub': 'Bay Leaf + Lemon Zest', 'ratio': '6 leaves = 1 bay leaf'},
      {'orig': 'Jaggery (బెల్లం / गुड़)', 'sub': 'Palm Sugar or Brown Sugar', 'ratio': '1:1 ratio'},
    ];

    return ListView.builder(
      padding: const EdgeInsets.all(16),
      itemCount: subs.length,
      itemBuilder: (context, i) {
        final s = subs[i];
        return Card(
          margin: const EdgeInsets.only(bottom: 12),
          child: ListTile(
            leading: const CircleAvatar(
              backgroundColor: Color(0xFFBC3814),
              child: Icon(Icons.swap_horiz, color: Colors.white),
            ),
            title: Text(s['orig']!, style: const TextStyle(fontWeight: FontWeight.bold)),
            subtitle: Text('Substitute: ${s['sub']}\nRatio: ${s['ratio']}'),
          ),
        );
      },
    );
  }

  Widget _buildPlannerTab() {
    return ListView(
      padding: const EdgeInsets.all(16),
      children: const [
        ListTile(
          leading: Icon(Icons.wb_sunny, color: Colors.orange),
          title: Text('Monday Breakfast: Andhra Pesarattu'),
          subtitle: Text('08:30 AM • 290 kcal'),
        ),
        Divider(),
        ListTile(
          leading: Icon(Icons.lunch_dining, color: Colors.red),
          title: Text('Monday Lunch: Hyderabadi Dum Biryani'),
          subtitle: Text('01:00 PM • 580 kcal'),
        ),
        Divider(),
        ListTile(
          leading: Icon(Icons.nightlight, color: Colors.indigo),
          title: Text('Monday Dinner: Dal Makhani & Roti'),
          subtitle: Text('08:30 PM • 420 kcal'),
        ),
      ],
    );
  }

  Widget _buildGroceryTab() {
    return ListView.builder(
      padding: const EdgeInsets.all(16),
      itemCount: _shoppingList.length,
      itemBuilder: (context, idx) {
        return CheckboxListTile(
          value: false,
          onChanged: (val) {},
          title: Text(_shoppingList[idx]),
          secondary: const Icon(Icons.shopping_bag_outlined),
        );
      },
    );
  }

  Widget _buildFavoritesTab() {
    final favList = _recipes.where((r) => _favorites.contains(r.id)).toList();
    return ListView(
      padding: const EdgeInsets.all(16),
      children: favList.map((r) => _buildRecipeCard(r)).toList(),
    );
  }
}
