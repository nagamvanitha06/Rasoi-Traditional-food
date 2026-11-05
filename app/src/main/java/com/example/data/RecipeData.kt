package com.example.data

import com.example.model.AppLanguage
import com.example.model.IngredientCategory
import com.example.model.IngredientSubstitution
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.model.RecipeIngredient
import com.example.model.RecipeStep
import com.example.model.SpiceLevel
import com.example.model.localized

object RecipeData {

    private fun ing(en: String, hi: String, te: String, qty: Double, unitEn: String, unitHi: String, unitTe: String, cat: IngredientCategory, sub: String? = null) =
        RecipeIngredient(
            name = localized(en, hi, te),
            baseQuantity = qty,
            unit = localized(unitEn, unitHi, unitTe),
            category = cat,
            substituteNote = sub?.let { localized(it, it, it) }
        )

    private fun step(num: Int, tEn: String, tHi: String, tTe: String, iEn: String, iHi: String, iTe: String, sec: Int? = null, tip: String? = null) =
        RecipeStep(
            stepNumber = num,
            title = localized(tEn, tHi, tTe),
            instruction = localized(iEn, iHi, iTe),
            timerSeconds = sec,
            chefTip = tip?.let { localized(it, it, it) }
        )

    val traditionalRecipes: List<Recipe> = listOf(
        // 1. Hyderabadi Dum Biryani
        Recipe(
            id = "rec_hyderabadi_biryani",
            title = localized("Hyderabadi Dum Biryani", "शाही हैदराबादी दम बिरयानी", "హైదరాబాదీ దమ్ బిర్యానీ"),
            description = localized(
                "Centuries-old Nizami heritage rice cooked with fragrant basmati, marinated pieces in spiced yogurt, fried onions, mint, and saffron milk.",
                "निज़ामी परंपरा की खुशबूदार दम बिरयानी, केसर, तली हुई प्याज (बरिस्ता), पुदीना और मसालों में पकी हुई।",
                "సువాసనగల బాస్మతి బియ్యం, వేయించిన ఉల్లిపాయలు, పుదీనా, కుంకుమపువ్వు పాలతో దమ్ చేసిన నిజామీ సంప్రదాయ బిర్యానీ."
            ),
            region = localized("Hyderabad, Telangana", "हैदराबाद, तेलंगाना", "హైదరాబాద్, తెలంగాణ"),
            category = RecipeCategory.RICE_BIRYANI,
            isVegetarian = false,
            spiceLevel = SpiceLevel.SPICY,
            prepTimeMinutes = 35,
            cookTimeMinutes = 45,
            baseServings = 4,
            caloriesPerServing = 580,
            imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800",
            tags = listOf("Biryani", "Hyderabadi", "Dum Cooking", "Saffron", "Festive"),
            ingredients = listOf(
                ing("Aged Basmati Rice", "बासमती चावल", "బాస్మతి బియ్యం", 500.0, "g", "ग्राम", "గ్రా", IngredientCategory.GRAINS),
                ing("Chicken or Paneer", "चिकन या पनीर", "చికెన్ లేదా పనీర్", 600.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Thick Curd / Dahi", "गाढ़ा दही", "చిక్కటి పెరుగు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.DAIRY, "Cashew cream + lemon"),
                ing("Fried Onions (Birista)", "तली प्याज", "వేయించిన ఉల్లిపాయలు", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.PRODUCE),
                ing("Pure Desi Ghee", "देसी घी", "స్వచ్ఛమైన నెయ్యి", 4.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY),
                ing("Saffron Milk", "केसर दूध", "కుంకుమపువ్వు పాలు", 0.25, "cup", "कप", "కప్పు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Marination", "मैरीनेशन", "మ్యారినేషన్", "Mix chicken/paneer with curd, ginger-garlic, mint, spices. Rest 30 mins.", "दही और मसालों के साथ 30 मिनट मैरीनेट करें।", "పెరుగు, అల్లం వెల్లుల్లి, పుదీనా, మసాలాలతో 30 నిమిషాలు నానబెట్టండి.", 1800),
                step(2, "Parboil Rice", "चावल उबालना", "బియ్యం ఉడికించడం", "Boil basmati rice with whole spices until 70% done, then drain.", "खड़े मसालों के साथ चावल 70% उबालें।", "మసాలా దినుసులతో బియ్యాన్ని 70% ఉడికించి వార్చుకోండి.", 480),
                step(3, "Layer and Dum", "दम लगाना", "దమ్ చేయడం", "Layer meat, rice, ghee, fried onions, saffron milk. Seal with dough and dum cook 25 mins.", "चावल और घी की परत लगाकर 25 मिनट धीमी आंच पर दम दें।", "అన్నం, నెయ్యి, ఉల్లిపాయలు, కుంకుమపువ్వు వేసి ఆవిరి పోకుండా 25 నిమిషాలు దమ్ చేయండి.", 1500)
            )
        ),

        // 2. Andhra Pesarattu
        Recipe(
            id = "rec_andhra_pesarattu",
            title = localized("Andhra Pesarattu & Allam Chutney", "आंध्र पेसरट्टू और अदरक चटनी", "ఆంధ్ర పెసరట్టు & అల్లం పచ్చడి"),
            description = localized(
                "Crispy whole green gram crepe served with tangy ginger-jaggery chutney and chopped onions.",
                "साबुत हरी मूंग दाल से बना कुरकुरा और पौष्टिक पेसरट्टू।",
                "పొట్టు పెసలతో చేసిన కరకరలాడే సాంప్రదాయ పెసరట్టు, అల్లం పచ్చడితో."
            ),
            region = localized("Andhra Pradesh", "आंध्र प्रदेश", "ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 290,
            imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800",
            tags = listOf("Pesarattu", "Healthy", "High Protein", "Breakfast", "Andhra"),
            ingredients = listOf(
                ing("Whole Green Gram (Pesalu)", "साबुत मूंग", "పొట్టు పెసలు", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Fresh Ginger root", "ताज़ा अदरक", "తాజా అల్లం", 2.0, "inches", "इंच", "అంగుళాలు", IngredientCategory.PRODUCE),
                ing("Green Chilies & Cumin", "हरी मिर्च और जीरा", "పచ్చిమిర్చి & జీలకర్ర", 1.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్", IngredientCategory.SPICES),
                ing("Chopped Onions", "बारीक कटी प्याज", "సన్నగా తరిగిన ఉల్లిపాయలు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.PRODUCE)
            ),
            instructions = listOf(
                step(1, "Grind Batter", "घोल तैयार करना", "పిండి రుబ్బడం", "Grind soaked green gram with ginger, green chilies, and cumin smoothly.", "भीगी मूंग, अदरक और मिर्च को बारीक पीस लें।", "నానబెట్టిన పెసలను అల్లం, పచ్చిమిర్చి, జీలకర్రతో మెత్తగా రుబ్బండి.", 300),
                step(2, "Roast on Tawa", "पेसरट्टू सेंकना", "పెనంపై కాల్చడం", "Pour batter on hot tawa, top with onions, drizzle ghee, and roast until golden.", "गरम तवे पर फैलाएं, प्याज डालें और घी लगाकर कुरकुरा सेंकें।", "పెనంపై పిండి వేసి ఉల్లిపాయలు చల్లి నెయ్యితో బంగారు రంగు వచ్చేవరకు కాల్చండి.", 180)
            )
        ),

        // 3. Gutti Vankaya Kura
        Recipe(
            id = "rec_gutti_vankaya",
            title = localized("Gutti Vankaya Kura (Stuffed Brinjal)", "गुत्ती वंकाया (आंध्र भरवां बैंगन)", "గుత్తి వంకాయ కూర (స్టఫ్డ్ బ్రింజాల్)"),
            description = localized(
                "Royal Andhra spicy baby eggplants stuffed with roasted peanuts, sesame, and coconut masala gravy.",
                "छोटे बैंगनों में भुनी मूंगफली और तिल का मसाला भरकर गाढ़ी ग्रेवी में पकाया गया प्रसिद्ध व्यंजन।",
                "వేరుశెనగ, నువ్వులు, కొబ్బరి, చింతపండు మసాలాతో గుత్తి వంకాయలను కూర్చి వండిన ఆంధ్ర స్పెషల్ కూర."
            ),
            region = localized("Rayalaseema, Andhra Pradesh", "आंध्र प्रदेश", "రాయలసీమ, ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 25,
            cookTimeMinutes = 30,
            baseServings = 4,
            caloriesPerServing = 340,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Andhra", "Brinjal", "Spicy", "Rayalaseema"),
            ingredients = listOf(
                ing("Tender Brinjals (Vankaya)", "छोटे बैंगन", "లేత గుత్తి వంకాయలు", 500.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Roasted Peanuts", "भुनी मूंगफली", "వేరుశెనగ గుళ్లు", 0.5, "cup", "कप", "కప్పు", IngredientCategory.PANTRY),
                ing("White Sesame Seeds", "सफेद तिल", "తెల్ల నువ్వులు", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES),
                ing("Tamarind Pulp", "इमली का पल्प", "చింతపండు రసం", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PANTRY, "Amchur powder")
            ),
            instructions = listOf(
                step(1, "Roast Masala", "मसाला भूनना", "మసాలా వేయించడం", "Dry roast peanuts, sesame, and coconut. Grind into stuffing paste.", "मूंगफली, तिल और नारियल को भूनकर पेस्ट बनाएं।", "వేరుశెనగ, నువ్వులు, కొబ్బరి వేయించి మసాలా పేస్ట్ చేయండి.", 300),
                step(2, "Stuff & Simmer", "भरना और पकाना", "వంకాయలను మగ్గించడం", "Stuff brinjals with masala and simmer in oil on low heat until tender.", "बैंगन में मसाला भरकर धीमी आंच पर तेल छूटने तक पकाएं।", "వంకాయల్లో మసాలా కూర్చి నూనెలో చిన్న మంటపై నూనె తేలేవరకు మగ్గించండి.", 1200)
            )
        ),

        // 4. Punjabi Dal Makhani
        Recipe(
            id = "rec_dal_makhani",
            title = localized("Slow-Cooked Punjabi Dal Makhani", "शाही दाल मखनी", "పంజాబీ దాల్ మఖని"),
            description = localized(
                "Whole black urad lentils and kidney beans simmered overnight with tomatoes, white butter, and cream.",
                "काली साबुत उड़द और राजमा को मक्खन और क्रीम के साथ धीमी आंच पर घंटों पकाकर बनाया गया क्लासिक।",
                "నల్ల మినపపప్పు మరియు రాజ్మాను వెన్న, క్రీమ్, టమోటాలతో గంటల తరబడి సన్నని మంటపై ఉడికించిన ప్రసిద్ధ పంజాబీ వంటకం."
            ),
            region = localized("Punjab", "पंजाब", "పంజాబ్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 60,
            baseServings = 4,
            caloriesPerServing = 420,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Dal Makhani", "Creamy", "Punjabi"),
            ingredients = listOf(
                ing("Whole Black Urad Dal", "साबुत उड़द दाल", "నల్ల మినప గుళ్లు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("Rajma (Kidney Beans)", "राजमा", "రాజ్మా గింజలు", 0.25, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("White Butter (Makhan)", "सफेद मक्खन", "వెన్నపూస", 4.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY),
                ing("Fresh Cream", "ताज़ा मलाई", "తాజా మీగడ", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Pressure Cook", "उबालना", "ఉడికించడం", "Pressure cook soaked lentils and rajma until melt-in-mouth tender.", "दाल और राजमा को 6-8 सीटी आने तक खूब अच्छी तरह उबालें।", "కుక్కర్‌లో పప్పును మెత్తగా ఉడికించండి.", 900),
                step(2, "Slow Simmer", "धीमी आंच पर पकाना", "మరిగించడం", "Simmer with tomato puree, white butter, and cream for 45 minutes.", "टमाटर और मक्खन के साथ 45 मिनट तक धीमी आंच पर पकाएं।", "టమోటా గ్రేవీ, వెన్న, మీగడ వేసి 45 నిమిషాలు చిన్న మంటపై మరిగించండి.", 2700)
            )
        ),

        // 5. Kovil Pulihora
        Recipe(
            id = "rec_temple_pulihora",
            title = localized("Kovil Pulihora (Temple Tamarind Rice)", "कोविल पुलिहोरा (इमली चावल)", "గుడి పులిహోర (ఆంధ్ర సాంప్రదాయం)"),
            description = localized(
                "Temple style tamarind rice infused with crunchy peanuts, mustard, curry leaves, green chilies, and hing.",
                "दक्षिण भारतीय मंदिरों का प्रसिद्ध प्रसादम इमली चावल, मूंगफली, कढ़ी पत्ता और हींग के तड़के के साथ।",
                "ఆంధ్ర దేవాలయాల ప్రసాదం రుచిని తలపించే చింతపండు పులిహోర, వేరుశెనగలు, ఆవాలు, పచ్చిమిర్చి, ఇంగువ మరియు కరివేపాకు పోపుతో."
            ),
            region = localized("Tirupati, Andhra Pradesh", "तिरुपति, आंध्र प्रदेश", "తిరుపతి, ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 15,
            cookTimeMinutes = 25,
            baseServings = 4,
            caloriesPerServing = 380,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Pulihora", "Temple Style", "Prasadam"),
            ingredients = listOf(
                ing("Cooked Sona Masoori Rice", "पके चावल", "ఉడికించిన అన్నం", 3.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Thick Tamarind Pulp", "इमली पल्प", "చింతపండు గుజ్జు", 0.5, "cup", "कप", "కప్పు", IngredientCategory.PANTRY, "Amchur powder + lemon"),
                ing("Raw Peanuts", "मूंगफली", "వేరుశెనగ గుళ్లు", 0.33, "cup", "कप", "కప్పు", IngredientCategory.PANTRY),
                ing("Curry leaves and Hing", "कढ़ी पत्ता और हींग", "కరివేపాకు & ఇంగువ", 1.0, "bunch", "गुच्छा", "రెమ్మలు", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Simmer Tamarind Paste", "इमली पकाना", "గుజ్జు ఉడికించడం", "Simmer tamarind with turmeric, green chilies, and salt until thick and oil separates.", "इमली को तेल छूटने तक गाढ़ा पकाएं।", "చింతపండు రసాన్ని పసుపు, పచ్చిమిర్చితో నూనె తేలేవరకు ఉడికించండి.", 600),
                step(2, "Crunchy Tempering & Mix", "तड़का और मिलाना", "పోపు కలపడం", "Roast peanuts, mustard seeds, chilies, curry leaves, and mix into rice.", "मूंगफली, राई और कढ़ी पत्ता भूनकर चावल में मिलाएं।", "వేరుశెనగ, ఆవాలు, కరివేపాకు పోపు వేసి అన్నంలో మెల్లగా కలపండి.", 300)
            )
        ),

        // 6. Royal Gulab Jamun
        Recipe(
            id = "rec_royal_gulab_jamun",
            title = localized("Royal Saffron Gulab Jamun", "शाही केसरिया गुलाब जामुन", "రాయల్ కేసరి గులాబ్ జామున్"),
            description = localized(
                "Melt-in-mouth traditional khoya dumplings fried in pure ghee, soaked in rose cardamom saffron syrup.",
                "ताज़ा मावा से बने, देसी घी में तले और इलायची-केसरिया चाशनी में डूबे पारंपरिक रसीले जामुन।",
                "తాజా కోవా మరియు పనీర్‌తో చేసి, నేతిలో వేయించి యాలకులు, కుంకుమపువ్వు పాకంలో నానబెట్టిన గులాబ్ జామున్లు."
            ),
            region = localized("Heritage India", "उत्तर भारत", "భారతీయ సాంప్రదాయం"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 20,
            cookTimeMinutes = 25,
            baseServings = 6,
            caloriesPerServing = 320,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Gulab Jamun", "Dessert", "Sweet"),
            ingredients = listOf(
                ing("Soft Khoya / Mawa", "ताज़ा मावा", "తాజా కోవా", 200.0, "g", "ग्राम", "గ్రా", IngredientCategory.DAIRY),
                ing("Grated Paneer", "कद्दूकस पनीर", "తురిమిన పనీర్", 50.0, "g", "ग्राम", "గ్రా", IngredientCategory.DAIRY),
                ing("Sugar for syrup", "चीनी", "చక్కెర", 1.75, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY),
                ing("Cardamom & Saffron", "इलायची और केसर", "యాలకులు & కుంకుమపువ్వు", 1.0, "tsp", "चम्मच", "టీస్పూన్", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Prepare Syrup", "चाशनी बनाना", "పాకం సిద్ధం చేయడం", "Boil sugar and water with cardamom and saffron until sticky.", "चीनी और पानी को इलायची व केसर के साथ उबालें।", "చక్కెర, యాలకులు, కుంకుమపువ్వుతో తీగపాకం సిద్ధం చేయండి.", 480),
                step(2, "Shape & Slow Fry", "तलना", "నేతిలో వేయించడం", "Knead smooth dough, shape balls, fry on low heat in ghee until golden brown, then soak in syrup.", "गोलियां बनाकर धीमी आंच पर घी में तलें और चाशनी में 2 घंटे भिगोएं।", "ఉండలు చుట్టి చిన్న మంటపై నేతిలో వేయించి గోరువెచ్చని పాకంలో నానబెట్టండి.", 600)
            )
        ),

        // 7. Masala Dosa
        Recipe(
            id = "rec_masala_dosa",
            title = localized("Traditional Masala Dosa & Sambar", "मसाला डोसा और सांभर", "మసాలా దోశ & సాంబార్"),
            description = localized("Crisp golden rice-lentil crepe wrapped around savory spiced turmeric potato filling, served with coconut chutney.", "आलू के चटपटे मसाले से भरा कुरकुरा डोसा, सांभर और नारियल चटनी।", "బంగారు రంగులో కరకరలాడే దోశ, కమ్మటి బంగాళాదుంప మసాలా మరియు సాంబార్."),
            region = localized("Karnataka & South India", "दक्षिण भारत", "కర్ణాటక & దక్షిణ భారతం"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 310,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Dosa", "Breakfast", "Crispy"),
            ingredients = listOf(
                ing("Fermented Dosa Batter", "डोसा बैटर", "దోశ పిండి", 3.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Boiled Mashed Potatoes", "उबले आलू", "ఉడికించిన బంగాళాదుంపలు", 4.0, "pieces", "नग", "ముక్కలు", IngredientCategory.PRODUCE),
                ing("Mustard, Curry Leaves, Chilies", "राई, कढ़ी पत्ता", "ఆవాలు & కరివేపాకు", 1.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Potato Masala", "आलू मसाला", "ఆలూ మసాలా", "Sauté onions, mustard, green chilies, turmeric, and mix boiled potatoes.", "प्याज, राई और हल्दी के साथ आलू भूनें।", "ఉల్లిపాయలు, పచ్చిమిర్చి, పసుపుతో బంగాళాదుంప మసాలా చేయండి.", 300),
                step(2, "Make Crisp Dosa", "डोसा बनाना", "దోశ వేయడం", "Spread batter on hot griddle, roast with butter, fill potato masala, fold.", "तवे पर घोल फैलाकर मक्खन लगाएं, मसाला भरकर मोड़ें।", "పెనంపై పిండి వేసి వెన్నతో కాల్చి ఆలూ మసాలా పెట్టి మడవండి.", 180)
            )
        ),

        // 8. Butter Chicken
        Recipe(
            id = "rec_butter_chicken",
            title = localized("Murgh Makhani (Butter Chicken)", "मुर्ग मखनी (बटर चिकन)", "బట్టర్ చికెన్ (ముర్గ్ మఖని)"),
            description = localized("Tender marinated tandoori chicken cooked in velvety spiced tomato, cashew nut, and butter gravy.", "मखमली टमाटर, काजू और मक्खन की ग्रेवी में पका हुआ तंदूरी चिकन।", "టమోటా, జీడిపప్పు, వెన్న గ్రేవీలో మగ్గిన సాంప్రదాయ బట్టర్ చికెన్."),
            region = localized("Old Delhi / Punjab", "दिल्ली / पंजाब", "ఢిల్లీ / పంజాబ్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = false,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 30,
            cookTimeMinutes = 35,
            baseServings = 4,
            caloriesPerServing = 520,
            imageUrl = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
            tags = listOf("Butter Chicken", "Makhani", "Rich"),
            ingredients = listOf(
                ing("Chicken pieces", "चिकन", "చికెన్ ముక్కలు", 600.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Tomato Puree & Cashews", "टमाटर व काजू पेस्ट", "టమోటా & జీడిపప్పు పేస్ట్", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.PRODUCE),
                ing("Butter & Cream", "मक्खन व क्रीम", "వెన్న & మీగడ", 4.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Sauté Gravy", "ग्रेवी पकाना", "గ్రేవీ తయారు చేయడం", "Cook tomato-cashew puree in butter until smooth and fragrant.", "मक्खन में टमाटर और काजू पेस्ट अच्छी तरह भूनें।", "వెన్నలో టమోటా, జీడిపప్పు పేస్ట్ వేయించండి.", 600),
                step(2, "Simmer Chicken", "चिकन पकाना", "చికెన్ ఉడికించడం", "Add marinated chicken and simmer with kasuri methi and cream.", "चिकन, कसूरी मेथी और क्रीम डालकर 20 मिनट पकाएं।", "చికెన్, కసూరి మేథి, మీగడ వేసి 20 నిమిషాలు ఉడికించండి.", 1200)
            )
        ),

        // 9. Paneer Butter Masala
        Recipe(
            id = "rec_paneer_butter_masala",
            title = localized("Paneer Butter Masala", "पनीर बटर मसाला", "పనీర్ బట్టర్ మసాలా"),
            description = localized("Cubes of fresh paneer gently simmered in creamy satin-smooth tomato gravy with aromatic spices.", "मुलायम ताज़ा पनीर मक्खनी ग्रेवी और कस्तूरी मेथी के साथ।", "వెన్నపూస, క్రీమ్, టమోటా మసాలా గ్రేవీలో మెత్తటి తాజా పనీర్ కూర."),
            region = localized("North India", "उत्तर भारत", "ఉత్తర భారతం"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 380,
            imageUrl = "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800",
            tags = listOf("Paneer", "Vegetarian", "Creamy"),
            ingredients = listOf(
                ing("Fresh Paneer cubes", "पनीर", "పనీర్ ముక్కలు", 300.0, "g", "ग्राम", "గ్రా", IngredientCategory.DAIRY, "Extra firm tofu"),
                ing("Tomato Cashew Puree", "टमाटर काजू प्यूरी", "టమోటా జీడిపప్పు గుజ్జు", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.PRODUCE),
                ing("Butter & Kasuri Methi", "मक्खन व मेथी", "వెన్న & కసూరి మేథి", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Cook Makhani Gravy", "मखनी ग्रेवी", "మఖని గ్రేవీ", "Simmer puree with butter, Kashmiri chili, and garam masala.", "मक्खन में प्यूरी और मसाले डालकर गाढ़ा पकाएं।", "వెన్నలో టమోటా గ్రేవీ, కారం, మసాలా వేసి ఉడికించండి.", 600),
                step(2, "Add Paneer", "पनीर मिलाना", "పనీర్ కలపడం", "Drop paneer cubes, stir in cream and crushed fenugreek leaves.", "पनीर के टुकड़े, मलाई और मेथी मिलाकर 5 मिनट धीमी आंच पर रखें।", "పనీర్ ముక్కలు, మీగడ, కసూరి మేథి వేసి 5 నిమిషాలు ఉంచండి.", 300)
            )
        ),

        // 10. Rayalaseema Ragi Sangati
        Recipe(
            id = "rec_ragi_sangati",
            title = localized("Rayalaseema Ragi Sangati & Pulusu", "रागी संगटी और पुलुसु", "రాయలసీమ రాగి సంగటి & పులుసు"),
            description = localized("Nutritious finger millet balls served with fiery traditional country gravy (Natu Kodi / Pulusu).", "पौष्टिक रागी के मुड्डे और तीखा पारंपरिक खट्टा-तीखा सालन।", "రాయలసీమ సాంప్రదాయ ఆరోగ్యకరమైన రాగి ముద్ద మరియు నాటు కోడి / వంకాయ పులుసు."),
            region = localized("Rayalaseema, Andhra", "रायलासीमा, आंध्र", "రాయలసీమ, ఆంధ్ర"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 15,
            cookTimeMinutes = 30,
            baseServings = 4,
            caloriesPerServing = 310,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Ragi", "Rayalaseema", "Nutritious"),
            ingredients = listOf(
                ing("Ragi Flour (Finger Millet)", "रागी का आटा", "రాగి పిండి", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Cooked Rice", "पके चावल", "మెత్తటి అన్నం", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("Desi Ghee", "देसी घी", "నెయ్యి", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Cook Ragi with Rice", "रागी पकाना", "రాగి పిండి కలపడం", "Boil water with soft rice. Add ragi flour on top without stirring, let steam 5 mins, then mix vigorously with wooden stick.", "उबलते चावल में रागी आटा डालकर लकड़ी की मथानी से अच्छी तरह मिलाएं।", "మరిగే నీటిలో రాగి పిండి వేసి ఆవిరిపై ఉంచి తెడ్డుతో ఉండలు లేకుండా కలపండి.", 600),
                step(2, "Shape Sangati Balls", "गोलियां बनाना", "ముద్దలు చేయడం", "Apply ghee to hands or wet plate and shape into smooth round balls.", "हाथ में घी लगाकर गोल चिकने मुड्डे बनाएं।", "చేతికి నెయ్యి రాసుకుని వేడివేడిగా గుండ్రటి రాగి ముద్దలు చేయండి.", 180)
            )
        ),

        // 11. Kerala Avial
        Recipe(
            id = "rec_kerala_avial",
            title = localized("Kerala Avial (Coconut Veg Medley)", "केरल अवियल (नारियल सब्जी)", "కేరళ అవియల్ (కొబ్బరి కూరగాయలు)"),
            description = localized("Traditional Onam feast harmony of raw plantain, drumstick, yam, and carrot tossed in coconut, curd, and coconut oil.", "केरल की प्रसिद्ध मिश्रित सब्जी, पिसे नारियल, दही और ताज़ा नारियल तेल के साथ।", "అరటికాయ, మునగకాయ, క్యారెట్ కూరగాయలను కొబ్బరి, పెరుగు, కొబ్బరి నూనెతో వండిన కేరళ సాంప్రదాయ కూర."),
            region = localized("Kerala", "केरल", "కేరళ"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 20,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 220,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Avial", "Kerala", "Coconut"),
            ingredients = listOf(
                ing("Mixed Vegetables (Drumstick, Plantain, Carrot)", "मिश्रित सब्जियां", "మునగకాయ, అరటికాయ, క్యారెట్", 400.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Grated Fresh Coconut & Green Chilies", "कद्दूकस नारियल", "తురిమిన కొబ్బరి & పచ్చిమిర్చి", 1.0, "cup", "कप", "కప్పు", IngredientCategory.PANTRY),
                ing("Whisked Curd & Coconut Oil", "दही और नारियल तेल", "పెరుగు & కొబ్బరి నూనె", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Steam Veggies", "सब्जी उबालना", "కూరగాయలు ఉడికించడం", "Boil vegetables with turmeric and water until tender-crisp.", "हल्दी और नमक के साथ सब्जियों को नरम होने तक उबालें।", "కూరగాయలను పసుపు, ఉప్పుతో మెత్తగా ఉడికించండి.", 600),
                step(2, "Mix Coconut Paste & Curd", "नारियल मिलाना", "కొబ్బరి పేస్ట్ కలపడం", "Fold coarse coconut paste and curd. Turn off heat and drizzle raw coconut oil with curry leaves.", "नारियल पेस्ट, दही और कच्चा नारियल तेल डालकर मिलाएं।", "కొబ్బరి పేస్ట్, పెరుగు కలిపి దించేముందు పచ్చి కొబ్బరి నూనె, కరివేపాకు వేయండి.", 300)
            )
        ),

        // 12. Chettinad Pepper Masala
        Recipe(
            id = "rec_chettinad_pepper",
            title = localized("Chettinad Pepper Masala", "चेट्टीनाड पेपर मसाला", "చెట్టినాడు పెప్పర్ మసాలా"),
            description = localized("Fiery Tamil Nadu culinary gem roasted with black peppercorns, shallots, fennel, and star anise.", "काली मिर्च, सौंफ और खड़े मसालों में भुना हुआ तीखा चेट्टीनाड मसाला।", "నల్ల మిరియాలు, సోంపు, సాంబార్ ఉల్లిపాయలతో ఘాటుగా వేయించిన చెట్టినాడు మసాలా."),
            region = localized("Chettinad, Tamil Nadu", "तमिलनाडु", "చెట్టినాడు, తమిళనాడు"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = false,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            baseServings = 4,
            caloriesPerServing = 410,
            imageUrl = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
            tags = listOf("Chettinad", "Peppery", "Spicy"),
            ingredients = listOf(
                ing("Chicken or Mushrooms", "चिकन या मशरूम", "చికెన్ లేదా పుట్టగొడుగులు", 500.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Black Peppercorns", "काली मिर्च", "నల్ల మిరియాలు", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES),
                ing("Shallots (Sambar Onions)", "छोटे प्याज", "సాంబార్ ఉల్లిపాయలు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.PRODUCE)
            ),
            instructions = listOf(
                step(1, "Roast Pepper Masala", "मसाला भूनना", "మిరియాల మసాలా", "Roast black pepper, fennel, coriander seeds, and cinnamon; grind fresh.", "काली मिर्च, सौंफ और धनिया भूनकर दरदरा पीसें।", "మిరియాలు, సోంపు, ధనియాలు వేయించి తాజా మసాలా పొడి చేయండి.", 300),
                step(2, "Cook with Shallots", "पकाना", "ఉల్లిపాయలతో వేయించడం", "Sauté shallots in sesame oil, add pieces and freshly ground masala until deeply browned.", "छोटे प्याज भूनकर चिकन और पिसा मसाला डालकर भूनें।", "నూనెలో ఉల్లిపాయలు, చికెన్, మిరియాల మసాలా వేసి నూనె తేలేవరకు వేయించండి.", 1200)
            )
        ),

        // 13. Rajasthani Dal Baati Churma
        Recipe(
            id = "rec_dal_baati",
            title = localized("Rajasthani Dal Baati Churma", "दाल बाटी चूरमा", "రాజస్థానీ దాల్ బాటీ చుర్మా"),
            description = localized("Crisp baked wheat dumplings dipped in desi ghee, served with spicy Panchmel dal and sweet churma.", "घी में डूबी कुरकुरी बाटी, तीखी पंचमेल दाल और मीठा चूरमा।", "స్వచ్ఛమైన నేతిలో ముంచిన గోధుమ బాటీ, పంచమేల్ దాల్ మరియు తియ్యటి చుర్మా."),
            region = localized("Rajasthan", "राजस्थान", "రాజస్థాన్"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 25,
            cookTimeMinutes = 40,
            baseServings = 4,
            caloriesPerServing = 590,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Rajasthani", "Dal Baati", "Heritage"),
            ingredients = listOf(
                ing("Whole Wheat Flour (Atta)", "गेहूं का आटा", "గోధుమ పిండి", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Panchmel Dals (5 Lentils)", "पंचमेल दाल", "ఐదు రకాల పప్పులు", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Desi Ghee", "देसी घी", "స్వచ్ఛమైన నెయ్యి", 0.5, "cup", "कप", "కప్పు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Bake Baati", "बाटी सेकना", "బాటీ కాల్చడం", "Knead firm dough, shape balls, bake over embers or oven until crackly.", "आटा गूंथकर गोलियां बनाएं और सुनहरा कुरकुरा सेकें।", "గట్టి పిండిని ఉండలుగా చేసి బంగారు రంగు వచ్చేవరకు కాల్చండి.", 1500),
                step(2, "Dip in Ghee", "घी में डुबोना", "నేతిలో ముంచడం", "Crush hot baatis lightly and dip generously in warm melted ghee.", "गरम बाटी को तोड़कर शुद्ध देसी घी में अच्छी तरह डुबोएं।", "బాటీలను కొద్దిగా విరిచి వేడి నేతిలో ముంచి దాల్ తో వడ్డించండి.", 180)
            )
        ),

        // 14. Gujarati Khaman Dhokla
        Recipe(
            id = "rec_gujarati_dhokla",
            title = localized("Gujarati Khaman Dhokla", "गुजराती खमन ढोकला", "గుజరాతీ ఖమన్ ధోక్లా"),
            description = localized("Steamed spongy gram flour cakes tempered with mustard seeds, sesame, green chilies, and lemon sugar drizzle.", "बेसन से बना जालीदार और स्पंजी ढोकला, राई और मिर्च के तड़के के साथ।", "శనగపిండితో చేసిన మెత్తటి గుజరాతీ ధోక్లా, ఆవాలు, పచ్చిమిర్చి, నిమ్మరసం పోపుతో."),
            region = localized("Gujarat", "गुजरात", "గుజరాత్"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 180,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Dhokla", "Snack", "Steamed"),
            ingredients = listOf(
                ing("Gram Flour (Besan)", "बेसन", "శనగపిండి", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Turmeric, Lemon Juice, Sugar", "हल्दी, नींबू, चीनी", "పసుపు, నిమ్మరసం, చక్కెర", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PANTRY),
                ing("Mustard seeds & Green chilies", "राई और हरी मिर्च", "ఆవాలు & పచ్చిమిర్చి", 1.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Steam Batter", "भाप में पकाना", "ఆవిరిపై ఉడికించడం", "Whisk besan batter with lemon and fruit salt, steam in greased tray for 18 mins.", "घोल को थाली में डालकर 18 मिनट भाप में पकाएं।", "శనగపిండి మిశ్రమాన్ని పళ్ళెంలో పోసి ఆవిరిపై 18 నిమిషాలు ఉడికించండి.", 1080),
                step(2, "Tempering Tadka", "तड़का लगाना", "కమ్మటి పోపు", "Pour hot mustard-chili sugar water over sliced steamed dhokla.", "राई, मिर्च और मीठे पानी का तड़का ऊपर डालें।", "ఆవాలు, పచ్చిమిర్చి వేయించి నిమ్మ-చక్కెర నీళ్ల పోపును ధోక్లాపై పోయండి.", 300)
            )
        ),

        // 15. Kashmiri Rogan Josh
        Recipe(
            id = "rec_kashmiri_rogan_josh",
            title = localized("Kashmiri Rogan Josh", "कश्मीरी रोगन जोश", "కాశ్మీరీ రోగన్ జోష్"),
            description = localized("Aromatic Kashmiri curry slow-cooked with fennel powder, dry ginger (sonth), Kashmiri red chilies, and ratan jot.", "सौंफ, सोंठ और कश्मीरी लाल मिर्च में धीमी आंच पर पका शाही रोगन जोश।", "సోంపు, శొంఠి, కాశ్మీరీ ఎర్ర కారంతో సన్నని మంటపై వండిన సువాసనగల కాశ్మీరీ కూర."),
            region = localized("Kashmir Valley", "कश्मीर घाटी", "కాశ్మీర్ లోయ"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = false,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 50,
            baseServings = 4,
            caloriesPerServing = 480,
            imageUrl = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
            tags = listOf("Kashmiri", "Rogan Josh", "Festive"),
            ingredients = listOf(
                ing("Mutton or Soya/Mushroom", "मटन या मशरूम", "మటన్ లేదా పుట్టగొడుగులు", 600.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Fennel & Ginger Powder (Sonth)", "सौंफ और सोंठ पाउडर", "సోంపు & శొంఠి పొడి", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES),
                ing("Kashmiri Red Chili & Curd", "कश्मीरी मिर्च व दही", "కాశ్మీరీ కారం & పెరుగు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Brown & Simmer", "भूनना व पकाना", "మసాలాలో ఉడికించడం", "Brown in mustard oil with cloves, add whisked curd, sonth, and simmer on low heat until fork tender.", "दही, सौंफ और सोंठ के साथ धीमी आंच पर 45 मिनट पकाएं।", "పెరుగు, శొంఠి, సోంపు పొడి వేసి చిన్న మంటపై 45 నిమిషాలు ఉడికించండి.", 2700)
            )
        ),

        // 16. Bengali Shorshe Maach / Paneer
        Recipe(
            id = "rec_bengali_shorshe",
            title = localized("Bengali Shorshe Curry", "बंगाली सरसों करी (Shorshe)", "బెంగాలీ ఆవాల కూర (షోర్షే)"),
            description = localized("Pungent traditional mustard paste and green chili curry finished with pure virgin mustard oil.", "पीली व काली सरसों और हरी मिर्च की तीखी बंगाली सरसों करी।", "పసుపు-నలుపు ఆవాల పేస్ట్ మరియు పచ్చిమిర్చితో ఘాటుగా వండిన బెంగాలీ సంప్రదాయ వంటకం."),
            region = localized("Bengal", "पश्चिम बंगाल", "బెంగాల్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.SPICY,
            prepTimeMinutes = 15,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 310,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Bengali", "Mustard", "Shorshe"),
            ingredients = listOf(
                ing("Fresh Paneer or Fish fillets", "पनीर या मछली", "పనీర్ లేదా చేప ముక్కలు", 400.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Yellow & Black Mustard Paste", "सरसों का पेस्ट", "ఆవాల పేస్ట్", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES),
                ing("Raw Cold-Pressed Mustard Oil", "कच्चा सरसों का तेल", "స్వచ్ఛమైన ఆవనూనె", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Cook in Mustard Gravy", "सरसों में पकाना", "ఆవాల గ్రేవీలో ఉడికించడం", "Simmer mustard paste with nigella seeds (kalonji) and slit green chilies. Add cubes and rest 5 mins.", "कलोंजी और मिर्च के साथ सरसों पेस्ट पकाएं और पनीर डालें।", "కలోంజి, పచ్చిమిర్చి, ఆవాల పేస్ట్‌లో ముక్కలను వేసి 10 నిమిషాలు ఉడికించండి.", 600)
            )
        ),

        // 17. Andhra Gongura Pachadi
        Recipe(
            id = "rec_andhra_gongura",
            title = localized("Andhra Gongura Pachadi & Rice", "आंध्र गोंगुरा चटनी और चावल", "ఆంధ్ర గోంగూర పచ్చడి & వేడి అన్నం"),
            description = localized("The undisputed king of Andhra pickles! Tangy sorrel leaves cooked with garlic, red chilies, and sesame oil.", "खट्टे गोंगुरा पत्तों, लहसुन और लाल मिर्च की प्रसिद्ध आंध्र चटनी।", "ఆంధ్రాకే తలమానికమైన గోంగూర పచ్చడి, వెల్లుల్లి, ఎండుమిర్చి, నువ్వుల నూనెతో రుబ్బిన అద్భుతమైన రుచి."),
            region = localized("Guntur, Andhra Pradesh", "गुंटूर, आंध्र प्रदेश", "గుంటూరు, ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 15,
            cookTimeMinutes = 15,
            baseServings = 6,
            caloriesPerServing = 190,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Gongura", "Andhra Pickle", "Spicy"),
            ingredients = listOf(
                ing("Fresh Red Stem Gongura Leaves", "लाल गोंगुरा पत्ते", "ఎర్ర గోంగూర ఆకులు", 4.0, "bunches", "गुच्छे", "కట్టలు", IngredientCategory.PRODUCE),
                ing("Dry Red Chilies & Garlic cloves", "सूखी मिर्च और लहसुन", "ఎండుమిర్చి & వెల్లుల్లి రెబ్బలు", 15.0, "cloves", "कलियां", "రెబ్బలు", IngredientCategory.PRODUCE),
                ing("Sesame Oil (Nuvvula Nune)", "तिल का तेल", "నువ్వుల నూనె", 4.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Wilt Leaves & Grind", "पत्ते भूनना व पीसना", "గోంగూర మగ్గించడం", "Cook gongura leaves until soft. Fry chilies, coriander seeds, garlic; grind together.", "पत्तों को मंदी आंच पर पकाएं, मिर्च और लहसुन के साथ पीसें।", "గోంగూరను మగ్గించి, ఎండుమిర్చి, వెల్లుల్లితో కలిపి రోటి పచ్చడిలా రుబ్బండి.", 480)
            )
        ),

        // 18. Hyderabadi Haleem
        Recipe(
            id = "rec_hyderabadi_haleem",
            title = localized("Hyderabadi Shahi Haleem", "हैदराबादी शाही हलीम", "హైదరాబాదీ షాహీ హలీమ్"),
            description = localized("Slow-cooked pounded meat, broken wheat, lentils, and ghee beaten with wooden pestles into a rich silky stew.", "गेहूं, दालों और घी को मथकर बनाया गया शाही हैदराबादी हलीम।", "గోధుమ రవ్వ, పప్పులు, నెయ్యి మరియు మసాలాలతో గంటల తరబడి మెదిపిన రాయల్ నిజామీ హలీమ్."),
            region = localized("Hyderabad, Telangana", "हैदराबाद", "హైదరాబాద్"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = false,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 40,
            cookTimeMinutes = 90,
            baseServings = 6,
            caloriesPerServing = 540,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Haleem", "Hyderabadi", "Slow Cooked"),
            ingredients = listOf(
                ing("Broken Wheat (Dalia)", "दलिया", "గోధుమ రవ్వ", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Mixed Dals (Chana, Urad, Moong)", "मिश्रित दालें", "శనగ, మినప, పెసర పప్పులు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("Desi Ghee & Fried Cashews", "घी और काजू", "నెయ్యి & వేయించిన జీడిపప్పు", 0.5, "cup", "कप", "కప్పు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Slow Cook & Mash", "घोंटना", "మెదపడం", "Cook wheat, lentils, and meat together until soft; pound vigorously with wooden masher until elastic and velvety.", "घोंटने वाले घोटे से खूब अच्छी तरह मथें जब तक कि मखमली न हो जाए।", "గోధుమ రవ్వ, పప్పులను మెత్తగా ఉడికించి తెడ్డుతో జిగురు వచ్చేంతవరకు మెదపండి.", 3600)
            )
        ),

        // 19. Medu Vada
        Recipe(
            id = "rec_medu_vada",
            title = localized("Crispy Medu Vada & Chutney", "कुरकुरा मेदू वड़ा", "కరకరలాడే మేదు వడ & చట్నీ"),
            description = localized("Golden crispy urad dal fritters fluffy on the inside with black pepper, curry leaves, and ginger.", "अंदर से नरम और बाहर से बेहद कुरकुरा उड़द दाल वड़ा।", "మినప్పప్పుతో చేసిన కరకరలాడే గారెలు (వడలు), కొబ్బరి చట్నీ మరియు సాంబార్ తో."),
            region = localized("South India", "दक्षिण भारत", "దక్షిణ భారతం"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 20,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 260,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Vada", "Crispy", "Breakfast"),
            ingredients = listOf(
                ing("Soaked Urad Dal", "उड़द दाल", "మినపప్పు", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Crushed Black Pepper & Ginger", "काली मिर्च व अदरक", "మిరియాలు & అల్లం", 1.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్", IngredientCategory.SPICES),
                ing("Oil for frying", "तलने का तेल", "నూనె", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Fluffy Batter & Shape", "वड़ा बनाना", "వడలు వేయడం", "Grind urad dal with minimal water until light and fluffy. Shape with hole in center, fry golden.", "हल्का घोल पीसकर बीच में छेद बनाकर गरम तेल में कुरकुरा तलें।", "నీళ్లు తక్కువగా పోసి పిండిని రుబ్బి, మధ్యలో రంధ్రం పెట్టి నూనెలో వేయించండి.", 480)
            )
        ),

        // 20. Idli Sambar
        Recipe(
            id = "rec_idli_sambar",
            title = localized("Soft Steamed Idli & Sambar", "नरम इडली और सांभर", "మల్లెపూవు లాంటి ఇడ్లీ & సాంబార్"),
            description = localized("Pillowy soft fermented rice cakes served with aromatic piping hot lentil-vegetable stew.", "रुई जैसी नरम इडली, गरमागरम सांभर और नारियल चटनी के साथ।", "మల్లెపూవు లాంటి మెత్తటి ఇడ్లీలు, వేడివేడి సాంబార్ మరియు కొబ్బరి చట్నీ."),
            region = localized("Tamil Nadu & Andhra", "दक्षिण भारत", "తమిళనాడు & ఆంధ్ర"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 210,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Idli", "Healthy", "Steamed"),
            ingredients = listOf(
                ing("Fermented Idli Batter", "इडली बैटर", "ఇడ్లీ పిండి", 3.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Toor Dal for Sambar", "तुअर दाल", "కందిపప్పు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS)
            ),
            instructions = listOf(
                step(1, "Steam Idlis", "भाप में पकाना", "ఇడ్లీలు ఉడికించడం", "Pour batter into greased idli plates and steam for 10-12 minutes.", "इडली स्टैंड में 10-12 मिनट भाप में पकाएं।", "ఇడ్లీ రేకుల్లో పిండి వేసి ఆవిరిపై 10-12 నిమిషాలు ఉడికించండి.", 720)
            )
        ),

        // 21. Chole Bhature
        Recipe(
            id = "rec_chole_bhature",
            title = localized("Amritsari Chole Bhature", "अमृतसरी छोले भटूरे", "పంజాబీ చోలే భటూరే"),
            description = localized("Spicy dark chickpeas cooked with tea decoction and dried pomegranate, paired with puffy fried bhature.", "मसालेदार पिंडी छोले और फूले हुए गरमागरम भटूरे।", "దానిమ్మ గింజల పొడితో వండిన ఘాటైన చోలే మసాలా మరియు వేడివేడి ఉబ్బిన భటూరే."),
            region = localized("Amritsar, Punjab", "पंजाब", "పంజాబ్"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.SPICY,
            prepTimeMinutes = 20,
            cookTimeMinutes = 35,
            baseServings = 4,
            caloriesPerServing = 560,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Chole", "Bhature", "Punjabi"),
            ingredients = listOf(
                ing("Kabuli Chana (Chickpeas)", "काबुली चना", "తెల్ల శనగలు", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Anardana & Chole Masala", "अनारदाना व मसाला", "దానిమ్మ గింజల పొడి & మసాలా", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Simmer Chole", "छोले पकाना", "చోలే ఉడికించడం", "Boil chickpeas with black tea bag, simmer with onion-tomato anardana masala.", "चायपत्ती के पानी में उबले छोले को अनारदाना मसाले में पकाएं।", "శనగలను ఉడికించి దానిమ్మ గింజల మసాలాతో చిక్కగా ఉడికించండి.", 1500)
            )
        ),

        // 22. Palak Paneer
        Recipe(
            id = "rec_palak_paneer",
            title = localized("Desi Palak Paneer", "पालक पनीर", "పాలక్ పనీర్"),
            description = localized("Nutritious pureed vibrant green spinach garlic gravy with tender pan-seared paneer cubes.", "लहसुनी पालक की हरी ग्रेवी और ताज़ा पनीर।", "పాలకూర, వెల్లుల్లి గ్రేవీలో మెత్తటి పనీర్ ముక్కల సాంప్రదాయ కూర."),
            region = localized("North India", "उत्तर भारत", "ఉత్తర భారతం"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 290,
            imageUrl = "https://images.unsplash.com/photo-1631452180519-c014fe946bc7?w=800",
            tags = listOf("Palak", "Paneer", "Greens"),
            ingredients = listOf(
                ing("Fresh Spinach (Palak)", "ताज़ा पालक", "తాజా పాలకూర", 3.0, "bunches", "गुच्छे", "కట్టలు", IngredientCategory.PRODUCE),
                ing("Paneer cubes", "पनीर", "పనీర్", 250.0, "g", "ग्राम", "గ్రా", IngredientCategory.DAIRY),
                ing("Garlic & Green Chilies", "लहसुन और हरी मिर्च", "వెల్లుల్లి & పచ్చిమిర్చి", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.PRODUCE)
            ),
            instructions = listOf(
                step(1, "Blanch & Puree", "पालक पीसना", "పాలకూర ఉడికించడం", "Blanch spinach in boiling water for 2 mins, ice shock, and blend.", "पालक को 2 मिनट उबालकर ठंडे पानी में डालें और पीसें।", "పాలకూరను 2 నిమిషాలు మరిగించి చల్లటి నీళ్లలో వేసి ప్యూరీ చేయండి.", 300)
            )
        ),

        // 23. Malabar Parotta with Kurma
        Recipe(
            id = "rec_malabar_parotta",
            title = localized("Malabar Layered Parotta & Kurma", "मालाबार लच्छा परोटा और कुरमा", "మలబార్ పరోటా & కుర్మా"),
            description = localized("Flaky, layered golden Kerala flatbread paired with rich spiced coconut vegetable kurma.", "परतों वाला खस्ता मालाबार परोटा और नारियल वेज कुरमा।", "పొరలు పొరలుగా ఉండే కరకరలాడే మలబార్ పరోటా మరియు కొబ్బరి కూరగాయల కుర్మా."),
            region = localized("Malabar, Kerala", "केरल", "మలబార్, కేరళ"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 30,
            cookTimeMinutes = 25,
            baseServings = 4,
            caloriesPerServing = 420,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Parotta", "Kerala", "Layered"),
            ingredients = listOf(
                ing("All-purpose Flour & Ghee", "मैदा व घी", "మైదా పిండి & నెయ్యి", 2.5, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Coconut Milk for Kurma", "नारियल दूध", "కొబ్బరి పాలు", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Pleat & Roast", "परोटा सेंकना", "పరోటా కాల్చడం", "Stretch thin, pleat into coils, roll, and roast on tawa with ghee.", "लोई को खींचकर लच्छे बनाएं और घी लगाकर दोनों तरफ सेंकें।", "పిండిని సాగదీసి పొరలుగా చుట్టి నేతితో పెనంపై కాల్చండి.", 480)
            )
        ),

        // 24. Traditional Pepper Rasam
        Recipe(
            id = "rec_pepper_rasam",
            title = localized("Traditional Pepper Garlic Rasam", "काली मिर्च लहसुन रसम", "మిరియాల చారు (రసం)"),
            description = localized("Soothing and medicinal South Indian broth infused with crushed black pepper, cumin, garlic, and tamarind.", "काली मिर्च, जीरा, लहसुन और इमली का पाचक दक्षिण भारतीय रसम।", "మిరియాలు, జీలకర్ర, వెల్లుల్లి, చింతపండు రసంతో కాచిన నోరూరించే ఔషధ చారు."),
            region = localized("South India", "दक्षिण भारत", "దక్షిణ భారతం"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.SPICY,
            prepTimeMinutes = 10,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 95,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800",
            tags = listOf("Rasam", "Soup", "Pepper"),
            ingredients = listOf(
                ing("Tamarind Water & Tomatoes", "इमली पानी व टमाटर", "చింతపండు రసం & టమోటా", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY),
                ing("Crushed Black Pepper & Cumin", "काली मिर्च व जीरा", "మిరియాలు & జీలకర్ర", 1.5, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Froth Rasam", "रसम उबालना", "రసం మరిగించడం", "Boil tamarind-tomato water with pepper powder. Temper with mustard and curry leaves; turn off as soon as frothy.", "उबाल आते ही तड़का लगाएं और झाग बनते ही आंच बंद करें।", "చింతపండు నీళ్లలో మిరియాల పొడి వేసి మరిగించి, నురగ రాగానే పోపు పెట్టి దించండి.", 480)
            )
        ),

        // 25. Karnataka Bisi Bele Bath
        Recipe(
            id = "rec_bisi_bele_bath",
            title = localized("Karnataka Bisi Bele Bath", "कर्नाटक बिसी बेले भात", "కర్ణాటక బిసిబేళె బాత్"),
            description = localized("Comforting rice and pigeon pea lentil one-pot dish cooked with vegetables, freshly roasted spice powder, and ghee.", "दाल, चावल और सब्जियों से बना कर्नाटक का मसालेदार पारंपरिक व्यंजन।", "బియ్యం, కందిపప్పు, కూరగాయలు మరియు ప్రత్యేక బిసిబేళె బాత్ మసాలాతో నెయ్యిలో వండిన కమ్మటి అన్నం."),
            region = localized("Mysore, Karnataka", "कर्नाटक", "మైసూరు, కర్ణాటక"),
            category = RecipeCategory.RICE_BIRYANI,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 30,
            baseServings = 4,
            caloriesPerServing = 380,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Bisi Bele Bath", "Karnataka", "One Pot"),
            ingredients = listOf(
                ing("Rice and Toor Dal", "चावल और तुअर दाल", "బియ్యం & కందిపప్పు", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Bisi Bele Bath Spice Powder", "बिसी बेले भात मसाला", "బిసిబేళె బాత్ మసాలా పొడి", 3.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Cook Together", "पकाना", "ఉడికించడం", "Pressure cook rice, dal, and veggies. Stir in spice paste, tamarind, and top with ghee roasted cashews.", "दाल, चावल और सब्जी पकाकर मसाला, इमली और घी का तड़का लगाएं।", "పప్పు, అన్నం, కూరగాయలు ఉడికించి మసాలా, చింతపండు, నెయ్యిలో వేయించిన జీడిపప్పు కలపండి.", 1200)
            )
        ),

        // 26. Royal Kaju Katli
        Recipe(
            id = "rec_kaju_katli",
            title = localized("Royal Silver Kaju Katli", "शाही काजू कतली", "రాయల్ కాజూ కత్లీ (జీడిపప్పు స్వీట్)"),
            description = localized("Melt-in-mouth diamond fudge crafted with finely powdered cashew nuts and sugar syrup.", "काजू और चीनी से बनी मखमली पारंपरिक काजू कतली।", "సన్నటి జీడిపప్పు పొడి, చక్కెర పాకంతో చేసిన నోరూరించే సాంప్రదాయ డైమండ్ స్వీట్."),
            region = localized("Heritage India", "उत्तर भारत", "భారతీయ సాంప్రదాయం"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 15,
            baseServings = 8,
            caloriesPerServing = 240,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Kaju Katli", "Sweet", "Festive"),
            ingredients = listOf(
                ing("Cashew Nut Powder (Kaju)", "काजू पाउडर", "జీడిపప్పు పొడి", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY),
                ing("Sugar and Cardamom", "चीनी व इलायची", "చక్కెర & యాలకులు", 1.0, "cup", "कप", "కప్పు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Stir Paste & Roll", "पकाना व बेलना", "పాకంలో ఉడికించి పరచడం", "Cook cashew powder in 1-string sugar syrup until dough forms. Knead warm and roll thin; cut diamonds.", "एक तार की चाशनी में काजू पाउडर डालकर पकाएं, बेलकर डायमंड आकार में काटें।", "చక్కెర తీగపాకంలో జీడిపప్పు పొడి కలిపి దగ్గరపడ్డాక చపాతీలా ఒత్తి డైమండ్ ముక్కలుగా కోయండి.", 600)
            )
        ),

        // 27. Mysore Pak
        Recipe(
            id = "rec_mysore_pak",
            title = localized("Traditional Mysore Pak", "पारंपरिक मैसूर पाक", "మైసూరు పాక్ (సాంప్రదాయం)"),
            description = localized("Royal Mysore palace delicacy made with roasted gram flour, sugar syrup, and generous cascading hot ghee.", "बेसन, चीनी और शुद्ध देसी घी से बनी मैसूर की शाही मिठाई।", "శనగపిండి, చక్కెర పాకం మరియు వేడివేడి నెయ్యితో వండిన ప్రసిద్ధ మైసూరు రాజాస్థాన పిండివంటకం."),
            region = localized("Mysore, Karnataka", "मैसूर, कर्नाटक", "మైసూరు, కర్ణాటక"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 10,
            cookTimeMinutes = 20,
            baseServings = 8,
            caloriesPerServing = 380,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Mysore Pak", "Ghee", "Sweet"),
            ingredients = listOf(
                ing("Besan (Gram Flour)", "बेसन", "శనగపిండి", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("Pure Desi Ghee", "देसी घी", "స్వచ్ఛమైన నెయ్యి", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.DAIRY),
                ing("Sugar", "चीनी", "చక్కెర", 1.5, "cups", "कप", "కప్పులు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Cook with Hot Ghee", "घी डालकर पकाना", "వేడి నెయ్యి పోస్తూ వండడం", "Gradually pour sizzling hot ghee into stirring besan sugar mixture until porous and honeycombed.", "बेसन और चाशनी में खौलता हुआ घी डालते हुए जालीदार बनने तक पकाएं।", "చక్కెర పాకంలో శనగపిండి కలిపి, వేడి నెయ్యిని కొద్దికొద్దిగా పోస్తూ పొంగు వచ్చేంతవరకు కలపండి.", 900)
            )
        ),

        // 28. Double Ka Meetha
        Recipe(
            id = "rec_double_ka_meetha",
            title = localized("Hyderabadi Double Ka Meetha", "डबल का मीठा (शाही टुकड़ा)", "హైదరాబాదీ డబుల్ కా మీఠా"),
            description = localized("Crisp fried milk bread steeped in condensed cardamom saffron milk (rabri) topped with slivered nuts.", "देसी घी में तली ब्रेड को केसरिया रबड़ी और मेवों के साथ पकाया गया शाही मीठा।", "నేతిలో వేయించిన బ్రెడ్ ముక్కలను కుంకుమపువ్వు చిక్కటి పాలకోవా (రబ్రీ) లో నానబెట్టిన నిజామీ స్వీట్."),
            region = localized("Hyderabad, Telangana", "हैदराबाद", "హైదరాబాద్"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 15,
            cookTimeMinutes = 25,
            baseServings = 6,
            caloriesPerServing = 390,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Dessert", "Hyderabadi", "Shahi"),
            ingredients = listOf(
                ing("Bread Slices fried in ghee", "ब्रेड स्लाइस", "నేతిలో వేయించిన బ్రెడ్ ముక్కలు", 6.0, "slices", "स्लाइस", "ముక్కలు", IngredientCategory.GRAINS),
                ing("Thickened Saffron Milk (Rabri)", "केसर रबड़ी", "కుంకుమపువ్వు రబ్రీ పాలు", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Soak in Rabri", "रबड़ी में पकाना", "పాలల్లో నానబెట్టడం", "Fry bread crisp in ghee, soak in warm cardamom sugar syrup, and pour rich rabri on top.", "ब्रेड को घी में तलें और गाढ़ी केसरिया रबड़ी डालकर सजाएं।", "బ్రెడ్ ముక్కలను నేతిలో వేయించి, చక్కెర పాకంలో ముంచి పైన చిక్కటి రబ్రీ పోయండి.", 600)
            )
        ),

        // 29. Gajar Ka Halwa
        Recipe(
            id = "rec_gajar_halwa",
            title = localized("Traditional Gajar Ka Halwa", "गाजर का हलवा", "క్యారెట్ హల్వా (సాంప్రదాయం)"),
            description = localized("Winter red carrots slow-simmered in full-fat milk, khoya, cardamom, and roasted cashews.", "सर्दियों की लाल गाजर, दूध, मावा और देसी घी से बना रसीला हलवा।", "ఎర్ర క్యారెట్లను పాలు, కోవా, యాలకులు, నెయ్యిలో వేయించిన జీడిపప్పులతో వండిన రుచికరమైన హల్వా."),
            region = localized("Punjab & North India", "उत्तर भारत", "పంజాబ్ & ఉత్తర భారతం"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 20,
            cookTimeMinutes = 40,
            baseServings = 6,
            caloriesPerServing = 340,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Halwa", "Carrot", "Winter"),
            ingredients = listOf(
                ing("Grated Red Carrots", "कद्दूकस लाल गाजर", "తురిమిన ఎర్ర క్యారెట్లు", 1.0, "kg", "किलो", "కిలో", IngredientCategory.PRODUCE),
                ing("Full Cream Milk & Khoya", "दूध व खोया", "చిక్కటి పాలు & కోవా", 1.0, "liter", "लीटर", "లీటర్", IngredientCategory.DAIRY)
            ),
            instructions = listOf(
                step(1, "Simmer & Roast", "दूध में पकाना", "పాలలో ఉడికించడం", "Simmer grated carrots in milk until liquid evaporates. Roast in ghee with khoya and cardamom.", "गाजर को दूध सूखने तक पकाएं, फिर घी व मावे के साथ भूनें।", "క్యారెట్ తురుమును పాలలో ఉడికించి నెయ్యి, కోవా, యాలకులతో వేయించండి.", 2400)
            )
        ),

        // 30. Poori Masala
        Recipe(
            id = "rec_poori_masala",
            title = localized("Puffed Poori & Aloo Saagu", "फूली पूरी और आलू भाजी", "పొంగిన పూరీ & ఆలూ సాగు"),
            description = localized("Golden deep-fried puffy whole wheat pooris served with mild spiced chickpea-potato curry.", "गरमागरम फूली हुई पूरी और खुशबूदार आलू की भाजी।", "బంగారు రంగులో గుండ్రంగా పొంగిన వేడివేడి పూరీలు మరియు బంగాళాదుంప సాగు."),
            region = localized("Heritage India", "संपूर्ण भारत", "భారతీయ సాంప్రదాయం"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 20,
            baseServings = 4,
            caloriesPerServing = 420,
            imageUrl = "https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=800",
            tags = listOf("Poori", "Breakfast", "Aloo"),
            ingredients = listOf(
                ing("Whole Wheat Flour (Atta)", "गेहूं का आटा", "గోధుమ పిండి", 2.0, "cups", "कप", "కప్పులు", IngredientCategory.GRAINS),
                ing("Potatoes, Onions, Green chilies", "आलू व प्याज", "బంగాళాదుంపలు & ఉల్లిపాయలు", 3.0, "pieces", "नग", "ముక్కలు", IngredientCategory.PRODUCE)
            ),
            instructions = listOf(
                step(1, "Fry Pooris", "पूरी तलना", "పూరీలు వేయించడం", "Roll dough rounds, drop in hot oil, press gently to puff up golden, and serve with potato saagu.", "गरम तेल में डालकर दबाएं और फूली हुई पूरी निकालें।", "వేడి నూనెలో పూరీలను వేసి గరిటెతో మెల్లగా అదిమి పొంగించి తీయండి.", 480)
            )
        ),

        // 31. Saffron Semiya Payasam
        Recipe(
            id = "rec_semiya_payasam",
            title = localized("Saffron Semiya Payasam (Kheer)", "केसरिया सेवई खीर", "కుంకుమపువ్వు సేమియా పాయసం"),
            description = localized("Ghee-roasted vermicelli cooked in sweetened creamy milk infused with saffron, green cardamom, and golden raisins.", "घी में भुनी सेवई, गाढ़े दूध, केसर और मेवों से बनी पारंपरिक खीर।", "నేతిలో వేయించిన సేమియా, చిక్కటి పాలు, యాలకులు, కుంకుమపువ్వు మరియు జీడిపప్పులతో చేసిన పాయసం."),
            region = localized("South & North India", "संपूर्ण भारत", "భారతీయ సాంప్రదాయం"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 10,
            cookTimeMinutes = 18,
            baseServings = 4,
            caloriesPerServing = 280,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800",
            tags = listOf("Payasam", "Kheer", "Festive"),
            ingredients = listOf(
                ing("Roasted Vermicelli (Semiya)", "रोस्टेड सेवई", "సేమియా", 1.0, "cup", "कप", "కప్పు", IngredientCategory.GRAINS),
                ing("Full Cream Milk", "दूध", "చిక్కటి పాలు", 4.0, "cups", "कप", "కప్పులు", IngredientCategory.DAIRY),
                ing("Sugar & Saffron strands", "चीनी व केसर", "చక్కెర & కుంకుమపువ్వు", 0.75, "cup", "कप", "కప్పు", IngredientCategory.PANTRY)
            ),
            instructions = listOf(
                step(1, "Simmer Payasam", "खीर पकाना", "పాయసం చేయడం", "Roast vermicelli in ghee, boil in milk until tender, stir in sugar, cardamom, saffron, and fried nuts.", "सेवई को घी में भूनकर दूध में पकाएं और चीनी व केसर मिलाएं।", "సేమియాను నేతిలో వేయించి పాలలో ఉడికించి చక్కెర, యాలకులు, కుంకుమపువ్వు కలపండి.", 720)
            )
        ),

        // 32. Gongura Chicken / Mutton Pulusu
        Recipe(
            id = "rec_gongura_mutton",
            title = localized("Andhra Gongura Mutton / Chicken", "आंध्र गोंगुरा मटन / चिकन", "ఆంధ్ర గోంగూర మాంసం (మటన్ / చికెన్)"),
            description = localized("Celebrated Andhra wedding delicacy: tender meat braised with sour red gongura leaves, garlic, and fiery red chili gravy.", "खट्टे गोंगुरा पत्तों और मसालों में धीमी आंच पर पका प्रसिद्ध आंध्र मटन।", "పుల్లటి ఎర్ర గోంగూర, వెల్లుల్లి, గుంటూరు కారంతో వండిన ప్రసిద్ధ సాంప్రదాయ ఆంధ్ర వంటకం."),
            region = localized("Andhra Pradesh", "आंध्र प्रदेश", "ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = false,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 25,
            cookTimeMinutes = 45,
            baseServings = 4,
            caloriesPerServing = 510,
            imageUrl = "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=800",
            tags = listOf("Gongura", "Andhra", "Fiery"),
            ingredients = listOf(
                ing("Tender Meat (Chicken/Mutton)", "मटन या चिकन", "మటన్ లేదా చికెన్", 600.0, "g", "ग्राम", "గ్రా", IngredientCategory.PRODUCE),
                ing("Fresh Gongura Leaves", "गोंगुरा पत्ते", "ఎర్ర గోంగూర ఆకులు", 3.0, "bunches", "गुच्छे", "కట్టలు", IngredientCategory.PRODUCE),
                ing("Guntur Red Chili & Garlic", "गुंटूर मिर्च व लहसुन", "గుంటూరు కారం & వెల్లుల్లి", 2.0, "tbsp", "चम्मच", "టేబుల్ స్పూన్లు", IngredientCategory.SPICES)
            ),
            instructions = listOf(
                step(1, "Cook Meat & Gongura", "पकाना", "ఉడికించడం", "Cook meat with spices until tender. Mash cooked gongura leaves into a paste and simmer together until rich and tangy.", "गोश्त को पकाकर भुने हुए गोंगुरा पेस्ट के साथ तेल छूटने तक पकाएं।", "మాంసాన్ని మసాలాతో ఉడికించి, మగ్గించిన గోంగూర ముద్దను కలిపి నూనె తేలేవరకు ఉడికించండి.", 2400)
            )
        )
    )
}
