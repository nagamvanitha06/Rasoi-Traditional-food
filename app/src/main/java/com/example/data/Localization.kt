package com.example.data

import com.example.model.AppLanguage
import com.example.model.LocalizedText
import com.example.model.localized

object Strings {
    // Navigation
    val navHome = localized("Home", "होम", "హోమ్")
    val navExplore = localized("Explore", "खोजें", "అన్వేషించండి")
    val navPlanner = localized("Meal Plan", "मील प्लान", "మీల్ ప్లాన్")
    val navShopping = localized("Grocery", "खरीदारी", "షాపింగ్")
    val navFavorites = localized("Favorites", "पसंदीदा", "ఇష్టమైనవి")
    val navProfile = localized("Profile", "प्रोफ़ाइल", "ప్రొఫైల్")

    // Home Screen
    val appSubtitle = localized("Authentic Heritage Recipes", "पारंपरिक भारतीय व्यंजन", "ప్రాచీన భారతీయ సాంప్రదాయ వంటలు")
    val searchPlaceholder = localized("Search recipes, spices, ingredients...", "व्यंजन, मसाले, सामग्री खोजें...", "వంటకాలు, మసాలాలు, పదార్థాలు శోధించండి...")
    val categoriesTitle = localized("Traditional Categories", "पारंपरिक श्रेणियां", "సాంప్రదాయ వర్గాలు")
    val featuredSection = localized("Royal Heritage Specials", "शाही और प्रसिद्ध व्यंजन", "రాయల్ హెరిటేజ్ వంటకాలు")
    val teluguSpecials = localized("Andhra & Telangana Ruchulu", "आंध्र और तेलंगाना के स्वाद", "ఆంధ్ర & తెలంగాణ రుచులు")
    val quickHealthy = localized("Quick Heritage Breakfast", "पारंपरिक नाश्ता", "సాంప్రదాయ అల్పాహారాలు")
    val viewAll = localized("View All", "सभी देखें", "అన్నీ చూడండి")

    // Recipe Details
    val prepTime = localized("Prep Time", "तैयारी का समय", "సిద్ధం సమయం")
    val cookTime = localized("Cook Time", "पकाने का समय", "వండే సమయం")
    val totalTime = localized("Total Time", "कुल समय", "మొత్తం సమయం")
    val servings = localized("Servings", "सर्विंग्स", "సర్వింగ్స్")
    val calories = localized("Calories", "कैलोरी", "క్యాలరీలు")
    val ingredientsHeader = localized("Authentic Ingredients", "आवश्यक सामग्री", "కావలసిన పదార్థాలు")
    val instructionsHeader = localized("Step-by-Step Method", "बनाने की विधि", "తయారీ విధానం")
    val substitutionsHeader = localized("Ingredient Substitutions", "सामग्री के विकल्प", "పదార్థాల ప్రత్యామ్నాయాలు")
    val chefNotes = localized("Traditional Secret Tip", "रसोई का गुप्त नुस्खा", "సాంప్రదాయ చిట్కా")
    val cookModeButton = localized("Start Guided Cooking", "निर्देशित खाना पकाना शुरू करें", "వంట ప్రారంభించండి (గైడెడ్)")
    val addAllToShopping = localized("Add to Grocery List", "खरीदारी सूची में जोड़ें", "షాపింగ్ లిస్ట్‌కు చేర్చండి")
    val addedToShopping = localized("Added to Grocery List!", "खरीदारी सूची में जोड़ा गया!", "షాపింగ్ లిస్ట్‌కు చేర్చబడింది!")
    val scaleServings = localized("Adjust Servings", "सर्विंग्स बदलें", "సర్వింగ్స్ మార్చండి")

    // Cooking Mode & Voice
    val cookingModeTitle = localized("Hands-Free Cooking", "हैंड्स-फ्री कुकिंग", "హ్యాండ్స్-ఫ్రీ వంట విధానం")
    val step = localized("Step", "चरण", "దశ")
    val of = localized("of", "का", "లో")
    val startTimer = localized("Start Step Timer", "टाइमर शुरू करें", "టైమర్ ప్రారంభించండి")
    val timerRunning = localized("Timer Running", "टाइमर चल रहा है", "టైమర్ నడుస్తోంది")
    val timerDone = localized("Time's Up!", "समय समाप्त!", "సమయం ముగిసింది!")
    val readAloud = localized("Read Step Aloud", "बोलकर सुनाएं", "చదివి వినిపించు")
    val pauseReading = localized("Stop Reading", "रुकें", "ఆపండి")
    val nextStep = localized("Next Step", "अगला चरण", "తరువాతి దశ")
    val prevStep = localized("Previous Step", "पिछला चरण", "మునుపటి దశ")
    val finishCooking = localized("Done Cooking!", "खाना तैयार है!", "వంట పూర్తయింది!")
    val voiceHelper = localized("Voice Commands Active", "वॉयस कमांड सक्रिय", "వాయిస్ కమాండ్స్ సక్రియం")
    val voicePrompt = localized("Say 'Next', 'Back', 'Timer' or dish name", "बोलें: 'Next', 'Back', 'Timer'", "చెప్పండి: 'Next', 'Back', 'Timer'")

    // Voice Command Modal
    val voiceSearchTitle = localized("Rasoi Voice Assistant", "रसोई वॉयस सहायक", "రసోయ్ వాయిస్ అసిస్టెంట్")
    val voiceListening = localized("Listening for commands...", "सुन रहे हैं...", "వింటున్నాము...")
    val voicePermissionRequired = localized("Microphone permission is required for voice commands.", "वॉयस कमांड के लिए माइक्रोफ़ोन की अनुमति आवश्यक है।", "వాయిస్ కమాండ్ల కోసం మైక్రోఫోన్ అనుమతి అవసరం.")
    val grantPermission = localized("Grant Permission", "अनुमति दें", "అనుమతి ఇవ్వండి")
    val voiceCommandHints = localized("Try saying: 'Show Biryani', 'Pure Veg', 'Next step', 'Start timer'", "बोल कर देखें: 'बिरयानी दिखाओ', 'शाकाहारी', 'अगला चरण'", "చెప్పండి: 'బిర్యానీ చూపించు', 'శాకాహారం', 'తరువాతి స్టెప్'")

    // Substitutions Screen
    val substitutionsTitle = localized("Ingredient Substitutions", "सामग्री प्रतिस्थापन", "పదార్థాల ప్రత్యామ్నాయాలు")
    val substitutionsSubtitle = localized("Never let missing ingredients stop your cooking!", "किसी सामग्री की कमी से खाना न रुके!", "ఏదైనా వస్తువు లేకపోయినా రుచికరంగా వండండి!")
    val searchIngredient = localized("Search any ingredient...", "सामग्री खोजें...", "ఏదైనా పదార్థాన్ని శోధించండి...")
    val bestSubstitute = localized("Recommended Substitute", "सर्वोत्तम विकल्प", "సిఫార్సు చేసిన ప్రత్యామ్నాయం")
    val ratio = localized("Quantity Ratio", "मात्रा अनुपात", "పరిమాణ నిష్పత్తి")
    val culinaryNotes = localized("Flavor & Culinary Note", "स्वाद और सुझाव", "రుచి మరియు వంట చిట్కా")

    // Meal Planner
    val plannerTitle = localized("Weekly Meal Planner", "साप्ताहिक भोजन योजना", "వారపు భోజన ప్రణాళిక")
    val plannerSubtitle = localized("Plan traditional breakfast, lunch, and dinner effortlessly", "पारंपरिक नाश्ते और खाने की योजना बनाएं", "సాంప్రదాయ అల్పాహారం, భోజనం సులభంగా ప్లాన్ చేయండి")
    val addMeal = localized("Add Dish", "व्यंजन जोड़ें", "వంటకం చేర్చు")
    val emptySlot = localized("No recipe assigned yet", "अभी कोई व्यंजन नहीं चुना गया", "ఇంకా వంటకం ఎంచుకోలేదు")
    val exportGrocery = localized("Generate Weekly Shopping List", "सप्ताह की खरीदारी सूची बनाएं", "వారపు షాపింగ్ లిస్ట్ రూపొందించు")
    val clearDay = localized("Clear Day", "साफ़ करें", "క్లియర్ చేయండి")

    // Shopping List
    val shoppingTitle = localized("Smart Grocery List", "स्मार्ट किराने की सूची", "స్మార్ట్ కిరాణా జాబితా")
    val shoppingSubtitle = localized("Grouped by spices, produce, and traditional staples", "मसाले, सब्जियों और अनाज के अनुसार व्यवस्थित", "మసాలాలు, కూరగాయలు & పప్పుల వారీగా")
    val addItem = localized("Add Custom Item", "नया सामान जोड़ें", "కొత్త వస్తువు చేర్చండి")
    val clearChecked = localized("Clear Completed", "पूर्ण किए गए हटाएं", "పూర్తయినవి తొలగించు")
    val shareList = localized("Share Shopping List", "सूची साझा करें", "జాబితా షేర్ చేయండి")
    val emptyShopping = localized("Your grocery list is empty! Add ingredients from any recipe.", "आपकी खरीदारी सूची खाली है! किसी व्यंजन से सामग्री जोड़ें।", "మీ షాపింగ్ జాబితా ఖాళీగా ఉంది! వంటకాల నుండి పదార్థాలను జోడించండి.")
    val itemNamePlaceholder = localized("e.g. Cardamom pods, Ghee, Basmati rice", "उदा. इलायची, घी, बासमती चावल", "ఉదా: యాలకులు, నెయ్యి, బాస్మతి బియ్యం")
    val itemQuantityPlaceholder = localized("e.g. 250g or 1 cup", "उदा. 250 ग्राम या 1 कप", "ఉదా: 250 గ్రా లేదా 1 కప్పు")

    // Favorites
    val favoritesTitle = localized("Saved Traditional Recipes", "सहेजे गए पारंपरिक व्यंजन", "సేవ్ చేసిన సాంప్రదాయ వంటకాలు")
    val emptyFavorites = localized("No favorite recipes saved yet. Tap the heart on recipes you love!", "अभी कोई पसंदीदा व्यंजन नहीं है। दिल के आइकन पर टैप करें!", "ఇంకా ఇష్టమైన వంటకాలు ఏవీ లేవు. నచ్చిన వంటకాలపై గుండె గుర్తును నొక్కండి!")

    // Profile & Login Portal
    val profileTitle = localized("Kitchen Profile & Settings", "रसोई प्रोफ़ाइल और सेटिंग्स", "కిచెన్ ప్రొఫైల్ & సెట్టింగ్‌లు")
    val loginHeader = localized("Rasoi Member Portal", "रसोई सदस्य पोर्टल", "రసోయ్ సభ్యుల పోర్టల్")
    val signedInAs = localized("Signed in as", "के रूप में लॉग इन", "లాగిన్ అయ్యారు")
    val googleSignIn = localized("Sign in with Google", "Google से साइन इन करें", "Google తో సైన్ ఇన్ చేయండి")
    val switchAccount = localized("Switch Account / Guest", "अतिथि मोड / खाता बदलें", "ఖాతా మార్చండి / గెస్ట్")
    val selectLanguage = localized("Choose App Language", "ऐप की भाषा चुनें", "యాప్ భాషను ఎంచుకోండి")
    val dietaryPref = localized("Dietary Preference", "आहार प्राथमिकता", "ఆహార ప్రాధాన్యత")
    val spicePref = localized("Preferred Spice Tolerance", "तीखापन सहनशीलता", "కారం ప్రాధాన్యత")
    val appStats = localized("Your Culinary Stats", "आपके आंकड़े", "మీ గణాంకాలు")
    val savedCount = localized("Favorite Recipes", "सहेजे गए व्यंजन", "ఇష్టమైన వంటకాలు")
    val plannedMeals = localized("Planned Meals", "योजनाबद्ध भोजन", "ప్లాన్ చేసిన భోజనాలు")
    val groceryItems = localized("Grocery Items", "किराने का सामान", "షాపింగ్ వస్తువులు")

    // Filter Chips
    val filterAll = localized("All", "सभी", "అన్నీ")
    val filterVeg = localized("Pure Veg", "शाकाहारी", "శాకాహారం")
    val filterNonVeg = localized("Non-Veg", "माँसाहारी", "మాంసాహారం")
    val filterQuick = localized("Under 30 Min", "30 मिनट से कम", "30 నిమిషాల లోపు")
}
