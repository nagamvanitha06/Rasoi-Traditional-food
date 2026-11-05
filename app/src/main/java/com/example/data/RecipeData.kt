package com.example.data

import com.example.model.AppLanguage
import com.example.model.DietType
import com.example.model.IngredientCategory
import com.example.model.IngredientSubstitution
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.model.RecipeIngredient
import com.example.model.RecipeStep
import com.example.model.SpiceLevel
import com.example.model.localized

object RecipeData {
    val traditionalRecipes: List<Recipe> = listOf(
        Recipe(
            id = "rec_hyderabadi_biryani",
            title = localized("Hyderabadi Dum Biryani", "शाही हैदराबादी दम बिरयानी", "హైదరాబాదీ దమ్ బిర్యానీ"),
            description = localized(
                "Centuries-old Nizami heritage rice cooked with fragrant long-grain basmati, marinated pieces in spiced yogurt, brown onions (birista), mint, and pure saffron milk sealed with wheat dough.",
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
            imageUrl = "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Biryani", "Hyderabadi", "Dum Cooking", "Saffron", "Festive"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Aged Basmati Rice", "बासमती चावल", "బాస్మతి బియ్యం"),
                    baseQuantity = 500.0,
                    unit = localized("g", "ग्राम", "గ్రా"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Chicken or Fresh Paneer cubes", "चिकन या ताज़ा पनीर", "చికెన్ లేదా పనీర్ ముక్కలు"),
                    baseQuantity = 600.0,
                    unit = localized("g", "ग्राम", "గ్రా"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Thick Curd / Dahi", "गाढ़ा दही", "చిక్కటి పెరుగు"),
                    baseQuantity = 1.0,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.DAIRY,
                    substituteNote = localized("Cashew paste + lemon", "काजू पेस्ट + नींबू", "జీడిపప్పు పేస్ట్ + నిమ్మరసం")
                ),
                RecipeIngredient(
                    name = localized("Fried Golden Onions (Birista)", "तली हुई प्याज (बरिस्ता)", "వేయించిన ఉల్లిపాయలు"),
                    baseQuantity = 1.5,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Fresh Mint & Coriander leaves", "पुदीना और हरा धनिया", "పుదీనా మరియు కొత్తిమీర"),
                    baseQuantity = 1.0,
                    unit = localized("bunch", "गुच्छा", "కట్ట"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Pure Desi Ghee", "देसी घी", "స్వచ్ఛమైన నెయ్యి"),
                    baseQuantity = 4.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Saffron strands soaked in warm milk", "केसर वाला दूध", "కుంకుమపువ్వు పాలు"),
                    baseQuantity = 0.25,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Shahi Biryani Garam Masala & Whole Spices", "खड़े गरम मसाले", "షాహీ బిర్యానీ మసాలా దినుసులు"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.SPICES
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Marination", "मैरीनेशन", "మ్యారినేషన్"),
                    instruction = localized(
                        "Mix chicken/paneer with thick curd, ginger-garlic paste, red chili powder, turmeric, biryani masala, half the fried onions, mint, coriander, and lemon juice. Marinate for at least 30 minutes.",
                        "चिकन या पनीर को गाढ़े दही, अदरक-लहसुन पेस्ट, लाल मिर्च, हल्दी, गरम मसाला, तली प्याज और पुदीने के साथ 30 मिनट तक मैरीनेट करें।",
                        "ముక్కలను పెరుగు, అల్లం వెల్లుల్లి పేస్ట్, కారం, పసుపు, బిర్యానీ మసాలా, వేయించిన ఉల్లిపాయలు, పుదీనా మరియు నిమ్మరసంతో 30 నిమిషాలు నానబెట్టండి."
                    ),
                    timerSeconds = 1800,
                    chefTip = localized("Add a spoon of raw mustard oil or ghee to seal the juices.", "मैरीनेशन में 1 चम्मच घी डालने से स्वाद कई गुना बढ़ जाता है।", "మ్యారినేషన్‌లో కొద్దిగా నెయ్యి కలిపితే ముక్క మెత్తగా ఉడుకుతుంది.")
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Parboil Basmati Rice", "चावल उबालना", "బియ్యం 70% ఉడికించడం"),
                    instruction = localized(
                        "Boil water with whole spices (cardamom, cloves, bay leaf, cinnamon, shahi jeera) and salt. Add soaked basmati rice and cook until exactly 70% done (firm bite). Drain completely.",
                        "पानी में साबुत खड़े मसाले और नमक डालकर उबालें। भीगे चावल डालें और ठीक 70% तक पकाकर पानी छान लें।",
                        "నీళ్లలో లవంగాలు, యాలకులు, దాల్చిన చెక్క, షాజీరా వేసి మరిగించి, బాస్మతి బియ్యాన్ని 70% వరకు ఉడికించి వార్చుకోండి."
                    ),
                    timerSeconds = 480,
                    chefTip = localized("Do not overboil rice; 7-8 minutes is optimal.", "चावल को 7-8 मिनट से ज्यादा न उबालें।", "బియ్యాన్ని 7-8 నిమిషాల కన్నా ఎక్కువ ఉడికించకూడదు.")
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Layering for Dum", "दम की लेयरिंग", "దమ్ లేయరింగ్"),
                    instruction = localized(
                        "In a heavy bottom handi, spread the marinated layer. Top with 70% parboiled rice. Garnish with remaining fried onions, chopped mint, coriander, generous desi ghee, and saffron milk.",
                        "भारी तले की हांडी में पहले मैरीनेटेड सामग्री रखें, ऊपर चावल की परत बिछाएं। बरिस्ता, पुदीना, धनिया, देसी घी और केसर का दूध डालें।",
                        "మందపాటి పాత్రలో కింద మ్యారినేట్ చేసిన మిశ్రమం, పైన ఉడికించిన అన్నం పొరలుగా పరచి, వేయించిన ఉల్లిపాయలు, కొత్తిమీర, నెయ్యి మరియు కుంకుమపువ్వు పాలు చల్లండి."
                    )
                ),
                RecipeStep(
                    stepNumber = 4,
                    title = localized("Seal and Dum Cook", "दम पर पकाना", "దమ్ పెట్టడం"),
                    instruction = localized(
                        "Seal the pot edges tightly with a lid and wheat dough (atta). Cook on high heat for 5 minutes, then place on a heavy tawa on low flame for 25 minutes. Rest 10 minutes before opening.",
                        "हांडी को ढक्कन और आटे से अच्छी तरह सील करें। 5 मिनट तेज़ आंच पर, फिर तवे पर धीमी आंच पर 25 मिनट पकाएं।",
                        "మూతకు గోధుమ పిండితో ఆవిరి పోకుండా సీల్ చేయండి. 5 నిమిషాలు పెద్ద మంటపై, తర్వాత పెనంపై చిన్న మంటపై 25 నిమిషాలు దమ్ చేయండి."
                    ),
                    timerSeconds = 1500,
                    chefTip = localized("Listen for gentle sizzle inside the handi, indicating steam circulation.", "भाप की खुशबू आने पर ही ढक्कन खोलें।", "ఆవిరి సువాసన వచ్చేంతవరకు మూత తెరవకండి.")
                )
            ),
            substitutions = listOf(
                IngredientSubstitution(
                    original = localized("Chicken", "चिकन", "చికెన్"),
                    substitute = localized("Fresh Paneer Cubes or Soya Chaap & Vegetables", "पनीर या सोया चाप व सब्जियां", "పనీర్ లేదా సోయా చాప్ మరియు కూరగాయలు"),
                    ratio = "1:1 weight equivalent",
                    notes = localized("Makes an exquisite traditional Veg Dum Biryani.", "लाजवाब वेज दम बिरयानी बनेगी।", "అద్భుతమైన శాకాహార దమ్ బిర్యానీ సిద్ధమవుతుంది.")
                )
            )
        ),
        Recipe(
            id = "rec_andhra_pesarattu",
            title = localized("Andhra Pesarattu & Allam Chutney", "आंध्र पेसरट्टू और अदरक की चटनी", "ఆంధ్ర పెసరట్టు & అల్లం పచ్చడి"),
            description = localized(
                "A nutritious, crispy traditional Andhra crepe made with whole green gram (moong), cumin, and green chilies, served with zesty ginger-tamarind chutney (Allam Pachadi) and Upma topping.",
                "साबुत हरी मूंग दाल से बना कुरकुरा और पौष्टिक आंध्र का प्रसिद्ध पेसरट्टू, तीखी-मीठी अदरक चटनी के साथ।",
                "పొట్టు పెసలు, జీలకర్ర, పచ్చిమిర్చితో చేసిన ఆరోగ్యకరమైన సాంప్రదాయ కరకరలాడే పెసరట్టు, నోరూరించే అల్లం పచ్చడితో."
            ),
            region = localized("Andhra Pradesh & Telangana", "आंध्र प्रदेश", "ఆంధ్ర ప్రదేశ్ & తెలంగాణ"),
            category = RecipeCategory.BREAKFAST_TIFFINS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 20,
            cookTimeMinutes = 15,
            baseServings = 4,
            caloriesPerServing = 290,
            imageUrl = "https://images.unsplash.com/photo-1668236543090-82eba5ee5976?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Pesarattu", "Healthy", "High Protein", "Breakfast", "Andhra"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Whole Green Gram (Pachai Payaru / Pesalu)", "साबुत हरी मूंग", "పొట్టు పెసలు"),
                    baseQuantity = 2.0,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Raw Rice (for crispiness)", "चावल", "బియ్యం"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Fresh Ginger root", "ताज़ा अदरक", "తాజా అల్లం"),
                    baseQuantity = 2.0,
                    unit = localized("inches", "इंच", "అంగుళాలు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Green Chilies & Cumin seeds (Jeera)", "हरी मिर्च और जीरा", "పచ్చిమిర్చి & జీలకర్ర"),
                    baseQuantity = 1.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Finely Chopped Onions for topping", "बारीक कटी प्याज", "సన్నగా తరిగిన ఉల్లిపాయలు"),
                    baseQuantity = 1.0,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Cold pressed sesame oil or Ghee", "तिल का तेल या घी", "నువ్వుల నూనె లేదా నెయ్యి"),
                    baseQuantity = 3.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Jaggery & Tamarind for Allam Chutney", "गुड़ और इमली (चटनी हेतु)", "బెల్లం & చింతపండు (అల్లం పచ్చడికి)"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Soak and Grind Batter", "दाल भिगोना और पीसना", "నానబెట్టడం మరియు పిండి రుబ్బడం"),
                    instruction = localized(
                        "Soak whole green gram and rice for 4-6 hours. Grind smoothly with ginger, green chilies, cumin seeds, and salt into a pourable batter (no fermentation required!).",
                        "मूंग और चावल को 4-6 घंटे भिगोएँ। अदरक, हरी मिर्च, जीरा और नमक के साथ पीस लें। इसमें खमीर उठाने की ज़रूरत नहीं होती।",
                        "పెసలు మరియు బియ్యాన్ని 4-6 గంటలు నానబెట్టండి. అల్లం, పచ్చిమిర్చి, జీలకర్ర, ఉప్పు వేసి దోశ పిండిలా రుబ్బండి (పులియబెట్టాల్సిన పనిలేదు)."
                    )
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Make Tangy Allam Pachadi", "अदरक की चटनी तैयार करना", "అల్లం పచ్చడి తయారు చేయడం"),
                    instruction = localized(
                        "Sauté fresh ginger, red chilies, urad dal, and hing. Grind with tamarind, jaggery, and salt. Temper with mustard seeds and curry leaves.",
                        "अदरक, सूखी लाल मिर्च, उड़द दाल को भूनकर इमली, गुड़ और नमक के साथ पीसें। राई-कढ़ी पत्ते का तड़का लगाएं।",
                        "అల్లం, ఎండుమిర్చి, మినప్పప్పు వేయించి చింతపండు, బెల్లం, ఉప్పుతో రుబ్బి ఆవాలు, కరివేపాకుతో పోపు పెట్టండి."
                    ),
                    timerSeconds = 300
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Pour & Roast Pesarattu", "पेसरट्टू सेंकना", "పెసరట్టు వేయడం"),
                    instruction = localized(
                        "Heat a cast iron tawa. Pour a ladle of batter and spread into a thin round. Sprinkle chopped onions, cumin, and drizzle ghee or sesame oil around the edges.",
                        "गरम तवे पर घोल फैलाएं। ऊपर बारीक कटी प्याज, जीरा छिड़कें और किनारों पर देसी घी या तिल का तेल लगाएं।",
                        "పెనం వేడెక్కాక పిండి పోసి గుండ్రంగా పరచండి. పైన ఉల్లిపాయ ముక్కలు, జీలకర్ర చల్లి, అంచుల్లో నెయ్యి లేదా నూనె వేయండి."
                    ),
                    timerSeconds = 180,
                    chefTip = localized("Press onions gently into batter so they caramelize cleanly.", "प्याज को हल्के हाथ से दबाएं ताकि वे अच्छे से भुन जाएं।", "ఉల్లిపాయ ముక్కలను గరిటెతో మెల్లగా అదిమితే పెసరట్టుకు బాగా అంటుకుని వేగుతాయి.")
                )
            )
        ),
        Recipe(
            id = "rec_gutti_vankaya",
            title = localized("Gutti Vankaya Kura (Stuffed Brinjal)", "गुत्ती वंकाया (आंध्र भरवां बैंगन)", "గుత్తి వంకాయ కూర (స్టఫ్డ్ బ్రింజాల్)"),
            description = localized(
                "Iconic Royal Andhra spicy stuffed baby eggplants cooked in a velvety roasted masala of peanuts, sesame seeds, desiccated coconut, coriander, and tamarind gravy.",
                "छोटे गोल बैंगनों में भुनी मूंगफली, तिल, नारियल और मसालों का पेस्ट भरकर गाढ़ी खट्टी-तीखी ग्रेवी में पकाया गया प्रसिद्ध आंध्र व्यंजन।",
                "వేరుశెనగ, నువ్వులు, కొబ్బరి, ధనియాలు, చింతపండు గుజ్జుతో గుత్తి వంకాయలను మసాలాతో కూర్చి వండిన ఆంధ్ర స్పెషల్ కూర."
            ),
            region = localized("Rayalaseema, Andhra Pradesh", "आंध्र प्रदेश", "రాయలసీమ, ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.CURRIES_DALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.VERY_SPICY,
            prepTimeMinutes = 25,
            cookTimeMinutes = 30,
            baseServings = 4,
            caloriesPerServing = 340,
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Andhra", "Brinjal", "Spicy", "Rayalaseema", "Traditional"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Small Purple Tender Brinjals (Vankaya)", "छोटे गोल बैंगन", "లేత గుత్తి వంకాయలు"),
                    baseQuantity = 500.0,
                    unit = localized("g", "ग्राम", "గ్రా"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Roasted Peanuts", "भुनी मूंगफली", "వేరుశెనగ గుళ్లు"),
                    baseQuantity = 0.5,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("White Sesame Seeds (Nuvvulu)", "सफेद तिल", "తెల్ల నువ్వులు"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Grated Dry Coconut (Kobbari)", "सूखा कद्दूकस नारियल", "ఎండిన కొబ్బరి తురుము"),
                    baseQuantity = 3.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Tamarind extract", "इमली का गाढ़ा पल्प", "చింతపండు రసం"),
                    baseQuantity = 3.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY,
                    substituteNote = localized("Amchur powder + lemon", "अमचूर पाउडर", "ఆమ్‌చూర్ పొడి")
                ),
                RecipeIngredient(
                    name = localized("Coriander seeds, Cumin, and Cloves", "धनिया बीज, जीरा और लौंग", "ధనియాలు, జీలకర్ర, లవంగాలు"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Groundnut oil or Sesame oil", "मूंगफली का तेल", "వేరుశెనగ నూనె"),
                    baseQuantity = 4.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Roast Masala", "मसाला भूनना", "మసాలా వేయించడం"),
                    instruction = localized(
                        "Dry roast peanuts, sesame seeds, dry coconut, coriander seeds, cumin, and dry red chilies until fragrant. Grind to a thick, flavorful stuffing paste with tamarind and salt.",
                        "मूंगफली, तिल, नारियल, धनिया और सूखी लाल मिर्च को हल्का भूनकर इमली और नमक के साथ दरदरा पीस लें।",
                        "వేరుశెనగ, నువ్వులు, కొబ్బరి, ధనియాలు, జీలకర్ర, ఎండుమిర్చి వేయించి చింతపండు, ఉప్పుతో కలిపి మసాలా పేస్ట్ చేయండి."
                    ),
                    timerSeconds = 300
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Slit and Stuff Brinjals", "बैंगन में मसाला भरना", "వంకాయలను కూర్చడం"),
                    instruction = localized(
                        "Slit brinjals into four quarters keeping the stem intact. Check inside and stuff generously with the prepared masala paste.",
                        "बैंगन को डंठल से जोड़े रखते हुए 4 चीरे लगाएं और तैयार मसाले से अच्छी तरह भरें।",
                        "వంకాయలను తొడిమ ఊడకుండా నాలుగు చీలికలుగా కోసి, తయారుచేసిన మసాలాను లోపల కూర్చండి."
                    )
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Slow Simmer Curry", "धीमी आंच पर पकाना", "నూనెలో మగ్గించడం"),
                    instruction = localized(
                        "Heat oil in a kadai with mustard seeds, curry leaves, and chopped onions. Place stuffed brinjals, add remaining masala paste and 1 cup warm water. Cover and cook on low heat until tender and oil separates.",
                        "कड़ाही में तेल गरम कर राई, कढ़ी पत्ता और प्याज डालें। भरे बैंगन और बचा मसाला डालकर ढककर धीमी आंच पर तेल छूटने तक पकाएं।",
                        "బాణలిలో నూనె వేసి ఆవాలు, కరివేపాకు, ఉల్లిపాయలు వేయించండి. కూర్చిన వంకాయలు, మిగిలిన మసాలా, నీళ్లు పోసి నూనె తేలేంతవరకు చిన్న మంటపై మగ్గించండి."
                    ),
                    timerSeconds = 1200,
                    chefTip = localized("Never stir vigorously; gently shake the pan so brinjals remain whole.", "चम्मच से ज्यादा न चलाएं ताकि बैंगन साबुत रहें।", "గరిటెతో కలపకుండా బాణలిని చేత్తో తిప్పితే వంకాయలు చిదగకుండా ఉంటాయి.")
                )
            )
        ),
        Recipe(
            id = "rec_dal_makhani",
            title = localized("Slow-Cooked Punjabi Dal Makhani", "शाही दाल मखनी (धीमी आंच पर पकी)", "పంజాబీ దాల్ మఖని (సాంప్రదాయం)"),
            description = localized(
                "Legendary whole black urad lentils and kidney beans simmered overnight with tomatoes, pure white butter, fresh ginger juliennes, and cream over charcoal smoke.",
                "काली साबुत उड़द और राजमा को मक्खन, क्रीम और टमाटर के साथ धीमी आंच पर घंटों पकाकर बनाया गया अमृतसरी क्लासिक।",
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
            imageUrl = "https://images.unsplash.com/photo-1546833999-b9f581a1996d?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Dal Makhani", "Creamy", "Lentils", "Punjabi", "Classic"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Whole Black Urad Dal (Sabut Urad)", "साबुत काली उड़द दाल", "నల్ల మినప గుళ్లు"),
                    baseQuantity = 1.0,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Rajma (Red Kidney Beans)", "कश्मीरी राजमा", "రాజ్మా గింజలు"),
                    baseQuantity = 0.25,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Fresh Homemade White Butter (Makhan)", "सफेद मक्खन", "వెన్న (వెన్నపూస)"),
                    baseQuantity = 4.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Fresh Cream / Malai", "ताज़ा मलाई / क्रीम", "తాజా మీగడ / క్రీమ్"),
                    baseQuantity = 3.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Fresh Tomato Puree", "टमाटर प्यूरी", "టమోటా ప్యూరీ"),
                    baseQuantity = 1.5,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Ginger-Garlic Paste & Ginger Juliennes", "अदरक-लहसुन पेस्ट और अदरक के लच्छे", "అల్లం వెల్లుల్లి పేస్ట్ & అల్లం ముక్కలు"),
                    baseQuantity = 2.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Kasuri Methi (crushed fenugreek)", "कसूरी मेथी", "కసూరి మేథి"),
                    baseQuantity = 1.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Degi / Kashmiri Red Chili Powder", "देगी मिर्च", "కశ్మీరీ కారం"),
                    baseQuantity = 1.5,
                    unit = localized("tsp", "छोटा चम्मच", "టీస్పూన్"),
                    category = IngredientCategory.SPICES
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Pressure Cook Dal & Rajma", "दाल और राजमा उबालना", "పప్పు ఉడికించడం"),
                    instruction = localized(
                        "Soak black lentils and rajma overnight. Pressure cook with 4 cups of water, salt, and black cardamom for 6-8 whistles until completely tender and melt-in-mouth.",
                        "दाल और राजमा को रातभर भिगोएँ। 4 कप पानी, नमक और बड़ी इलायची के साथ 6-8 सीटी आने तक खूब अच्छी तरह उबालें।",
                        "పప్పు, రాజ్మాను రాత్రంతా నానబెట్టి, ఉప్పు, పెద్ద యాలకులతో కుక్కర్‌లో 6-8 విజిల్స్ వచ్చేంతవరకు మెత్తగా ఉడికించండి."
                    ),
                    timerSeconds = 900
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Tomato-Butter Base", "तड़का और प्यूरी", "టమోటా-వెన్న గ్రేవీ"),
                    instruction = localized(
                        "In a thick pot, melt butter. Sauté ginger-garlic paste and Kashmiri chili. Add fresh tomato puree and cook on medium flame until butter releases from sides.",
                        "पतीले में मक्खन पिघलाएं। अदरक-लहसुन और कश्मीरी मिर्च भूनें। टमाटर प्यूरी डालकर मक्खन अलग होने तक पकाएं।",
                        "పాత్రలో వెన్న వేసి కరిగించి, అల్లం వెల్లుల్లి పేస్ట్, కారం, టమోటా ప్యూరీ వేసి వెన్న పైకి తేలేవరకు వేయించండి."
                    ),
                    timerSeconds = 480
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Slow Simmer and Mash", "धीमी आंच पर पकाना", "సన్నని మంటపై మరగబెట్టడం"),
                    instruction = localized(
                        "Add cooked dal and its water to the tomato base. Mash gently against pot sides with a ladle. Simmer on low flame for 45 minutes, adding warm water as needed to build natural starch creaminess.",
                        "उबली दाल मिलाएं। कलछी से हल्का मैश करते हुए 45 मिनट तक धीमी आंच पर पकाएं ताकि दाल मखमली गाढ़ी बने।",
                        "ఉడికించిన పప్పును గ్రేవీలో వేసి, గరిటెతో మెదుపుతూ 45 నిమిషాలు సన్నని మంటపై మరిగించండి. పప్పు సహజంగా చిక్కబడుతుంది."
                    ),
                    timerSeconds = 2700,
                    chefTip = localized("The longer it simmers on slow heat, the silkier the dal becomes.", "जितना धीमा पकेगा, उतना ही मक्खन जैसा स्वाद आएगा।", "ఎంత ఎక్కువసేపు సన్నని మంటపై మరిగితే అంత కమ్మటి రుచి వస్తుంది.")
                ),
                RecipeStep(
                    stepNumber = 4,
                    title = localized("Finish with Cream & Kasuri Methi", "मलाई और कसूरी मेथी", "మీగడ & కసూరి మేథితో పూర్తిచేయడం"),
                    instruction = localized(
                        "Finish by stirring in crushed toasted kasuri methi, garam masala, extra knob of white butter, and fresh cream. Serve hot with naan or jeera rice.",
                        "भुनी कसूरी मेथी, गरम मसाला, थोड़ा और सफेद मक्खन और ताज़ा मलाई डालकर गरमा-गरम परोसें।",
                        "చివరగా నలిపిన కసూరి మేథి, గరం మసాలా, ఇంకొంచెం వెన్న మరియు మీగడ కలిపి వేడివేడిగా వడ్డించండి."
                    )
                )
            )
        ),
        Recipe(
            id = "rec_temple_pulihora",
            title = localized("Kovil Pulihora (Temple Tamarind Rice)", "कोविल पुलिहोरा (मंदिर शैली इमली चावल)", "గుడి పులిహోర (ఆంధ్ర సాంప్రదాయం)"),
            description = localized(
                "Sacred temple style tamarind rice infused with slow-simmered tamarind paste, crunchy roasted peanuts, mustard seeds, curry leaves, green chilies, turmeric, and asafoetida.",
                "दक्षिण भारतीय मंदिरों का प्रसिद्ध प्रसादम इमली चावल, मूंगफली, कढ़ी पत्ता, हींग और तिल के तेल के तड़के के साथ।",
                "ఆంధ్ర దేవాలయాల ప్రసాదం రుచిని తలపించే చింతపండు పులిహోర, వేరుశెనగలు, ఆవాలు, పచ్చిమిర్చి, ఇంగువ మరియు కరివేపాకు పోపుతో."
            ),
            region = localized("Tirupati & Andhra Pradesh", "तिरुपति, आंध्र प्रदेश", "తిరుపతి, ఆంధ్ర ప్రదేశ్"),
            category = RecipeCategory.TRADITIONAL_SPECIALS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MEDIUM,
            prepTimeMinutes = 15,
            cookTimeMinutes = 25,
            baseServings = 4,
            caloriesPerServing = 380,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Pulihora", "Temple Style", "Prasadam", "Tamarind", "Festive"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Cooked Sona Masoori Rice (cooled)", "पके हुए चावल (ठंडे)", "ఉడికించిన సోనా మసూరి అన్నం"),
                    baseQuantity = 3.0,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Thick Tamarind Pulp (Pulusu)", "गाढ़ा इमली का पल्प", "చిక్కటి చింతపండు గుజ్జు"),
                    baseQuantity = 0.5,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Raw Peanuts (Verusenaga)", "मूंगफली के दाने", "వేరుశెనగ గుళ్లు"),
                    baseQuantity = 0.33,
                    unit = localized("cup", "कप", "కప్పు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Chana Dal & Urad Dal", "चना दाल और उड़द दाल", "శనగపప్పు & మినప్పప్పు"),
                    baseQuantity = 1.5,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Green Chilies slit & Dry Red Chilies", "हरी और सूखी लाल मिर्च", "పచ్చిమిర్చి చీలికలు & ఎండుమిర్చి"),
                    baseQuantity = 6.0,
                    unit = localized("pieces", "नग", "ముక్కలు"),
                    category = IngredientCategory.PRODUCE
                ),
                RecipeIngredient(
                    name = localized("Curry leaves and Asafoetida (Hing)", "कढ़ी पत्ता और हींग", "కరివేపాకు మరియు ఇంగువ"),
                    baseQuantity = 1.0,
                    unit = localized("bunch", "गुच्छा", "రెమ్మలు"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Sesame oil or Peanut oil", "तिल या मूंगफली का तेल", "నువ్వుల నూనె లేదా పల్లీ నూనె"),
                    baseQuantity = 4.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Turmeric powder and Mustard seeds", "हल्दी पाउडर और राई", "పసుపు మరియు ఆవాలు"),
                    baseQuantity = 1.0,
                    unit = localized("tsp", "छोटा चम्मच", "టీస్పూన్"),
                    category = IngredientCategory.SPICES
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Simmer Pulihora Paste", "इमली का पेस्ट तैयार करना", "పులిహోర గుజ్జు ఉడికించడం"),
                    instruction = localized(
                        "In a pan, cook tamarind pulp with turmeric, slit green chilies, salt, and 1 tsp sesame oil until thick and oil starts floating on top. This is the secret Pulihora paste (Puliogare Gojju).",
                        "इमली के पल्प को हल्दी, हरी मिर्च, नमक और थोड़े तेल के साथ गाढ़ा होने तक पकाएं।",
                        "చింతపండు గుజ్జులో పసుపు, పచ్చిమిర్చి, ఉప్పు, కొద్దిగా నూనె వేసి గుజ్జు దగ్గరపడి నూనె పైకి తేలేవరకు ఉడికించండి."
                    ),
                    timerSeconds = 600,
                    chefTip = localized("Cooking tamarind thoroughly removes any raw pungent throat tickle.", "इमली को खूब अच्छी तरह पकाएं ताकि गले में खट्टापन न लगे।", "చింతపండు గుజ్జును బాగా ఉడికిస్తే గొంతు పట్టేయకుండా రుచిగా ఉంటుంది.")
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Crunchy Tempering (Tadka)", "तड़का लगाना", "కమ్మటి పోపు పెట్టడం"),
                    instruction = localized(
                        "Heat sesame oil. Roast peanuts on low heat until golden. Add mustard seeds, chana dal, urad dal, red chilies, curry leaves, and a generous pinch of hing.",
                        "तेल में मूंगफली को धीमी आंच पर कुरकुरा भूनें। राई, चना दाल, उड़द दाल, सूखी मिर्च, कढ़ी पत्ता और हींग डालें।",
                        "నూనెలో వేరుశెనగలను దోరగా వేయించి ఆవాలు, శనగపప్పు, మినప్పప్పు, ఎండుమిర్చి, కరివేపాకు, ఇంగువ వేసి మంచి వాసన వచ్చేవరకు పోపు వేయించండి."
                    ),
                    timerSeconds = 240
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Mix and Infuse", "चावल में मिलाना", "అన్నంలో కలపడం"),
                    instruction = localized(
                        "Spread warm cooked rice on a wide plate. Pour the cooked tamarind paste and the hot crunchy tempering. Gently mix with a spoon without breaking rice grains. Let sit 30 minutes for flavors to blossom.",
                        "फैले हुए चावलों पर इमली का पेस्ट और गरम तड़का डालकर हल्के हाथ से मिलाएं। 30 मिनट ढककर रखें।",
                        "విరివిగా ఉన్న పళ్ళెంలో అన్నం పరచి, పులిహోర గుజ్జు, వేడి పోపు వేసి మెల్లగా కలపండి. అరగంట ఆగితే అన్నం పులుపును పీల్చుకుని అద్భుతమైన రుచి వస్తుంది."
                    )
                )
            )
        ),
        Recipe(
            id = "rec_royal_gulab_jamun",
            title = localized("Royal Saffron Gulab Jamun", "शाही केसरिया गुलाब जामुन", "రాయల్ కేసరి గులాబ్ జామున్"),
            description = localized(
                "Melt-in-mouth traditional khoya (mawa) and paneer dumplings gently fried in pure desi ghee, soaked in rose and cardamom saffron sugar syrup.",
                "ताज़ा मावा और पनीर से बने, देसी घी में तले और इलायची-केसरिया गुलाब चाशनी में डूबे पारंपरिक रसीले जामुन।",
                "తాజా కోవా మరియు పనీర్‌తో చేసి, నేతిలో వేయించి యాలకులు, కుంకుమపువ్వు, గులాబీ రేకుల పాకంలో నానబెట్టిన రసగుల్లల్లాంటి గులాబ్ జామున్లు."
            ),
            region = localized("North & Central India Heritage", "उत्तर भारत", "ఉత్తర & దక్షిణ భారత సాంప్రదాయం"),
            category = RecipeCategory.SNACKS_SWEETS,
            isVegetarian = true,
            spiceLevel = SpiceLevel.MILD,
            prepTimeMinutes = 20,
            cookTimeMinutes = 25,
            baseServings = 6,
            caloriesPerServing = 320,
            imageUrl = "https://images.unsplash.com/photo-1596797038530-2c107229654b?w=800&auto=format&fit=crop&q=80",
            tags = listOf("Gulab Jamun", "Dessert", "Saffron", "Mawa", "Sweet"),
            ingredients = listOf(
                RecipeIngredient(
                    name = localized("Fresh Soft Khoya / Mawa", "ताज़ा मावा (खोया)", "తాజా కోవా / మావా"),
                    baseQuantity = 200.0,
                    unit = localized("g", "ग्राम", "గ్రా"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Fresh Paneer (grated and kneaded)", "कद्दूकस किया पनीर", "తురిమిన పనీర్"),
                    baseQuantity = 50.0,
                    unit = localized("g", "ग्राम", "గ్రా"),
                    category = IngredientCategory.DAIRY
                ),
                RecipeIngredient(
                    name = localized("Maida (All-purpose flour)", "मैदा", "మైదా పిండి"),
                    baseQuantity = 3.0,
                    unit = localized("tbsp", "चम्मच", "టేబుల్ స్పూన్లు"),
                    category = IngredientCategory.GRAINS
                ),
                RecipeIngredient(
                    name = localized("Sugar for syrup", "चीनी (चाशनी हेतु)", "చక్కెర (పాకం కోసం)"),
                    baseQuantity = 1.75,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Green Cardamom pods (crushed)", "हरी इलायची", "యాలకులు"),
                    baseQuantity = 5.0,
                    unit = localized("pods", "नग", "గింజలు"),
                    category = IngredientCategory.SPICES
                ),
                RecipeIngredient(
                    name = localized("Saffron strands & Rose water", "केसर और गुलाब जल", "కుంకుమపువ్వు & రోజ్ వాటర్"),
                    baseQuantity = 1.0,
                    unit = localized("tsp", "छोटा चम्मच", "టీస్పూన్"),
                    category = IngredientCategory.PANTRY
                ),
                RecipeIngredient(
                    name = localized("Pure Desi Ghee for frying", "तलने के लिए शुद्ध देसी घी", "వేయించడానికి స్వచ్ఛమైన నెయ్యి"),
                    baseQuantity = 2.0,
                    unit = localized("cups", "कप", "కప్పులు"),
                    category = IngredientCategory.DAIRY
                )
            ),
            instructions = listOf(
                RecipeStep(
                    stepNumber = 1,
                    title = localized("Prepare Saffron Syrup", "केसरिया चाशनी बनाना", "కుంకుమపువ్వు పాకం తయారు చేయడం"),
                    instruction = localized(
                        "Boil sugar and water (1.5 cups) for 6-8 minutes until sticky (half-string consistency). Add crushed cardamom pods, saffron strands, and rose water. Keep warm.",
                        "चीनी और पानी को 6-8 मिनट चिपचिपी चाशनी बनने तक उबालें। इलायची, केसर और गुलाब जल मिलाएं।",
                        "చక్కెర మరియు నీళ్లను 6-8 నిమిషాలు తీగపాకం రాకముందు వరకు మరిగించండి. యాలకుల పొడి, కుంకుమపువ్వు, రోజ్ వాటర్ కలిపి గోరువెచ్చగా ఉంచండి."
                    ),
                    timerSeconds = 480
                ),
                RecipeStep(
                    stepNumber = 2,
                    title = localized("Knead Dough & Shape Balls", "मावा गूंधना और गोलियां बनाना", "పిండి కలపడం మరియు ఉండలు చుట్టడం"),
                    instruction = localized(
                        "Grate khoya and paneer. Mash with palms until smooth with no grains. Add maida and knead into a soft, supple dough. Roll into smooth, crack-free marble-sized balls.",
                        "मावा और पनीर को हथेलियों से चिकना होने तक मसलें। मैदा मिलाकर मुलायम आटा गूंथें और दरार-रहित गोलियां बनाएं।",
                        "కోవా మరియు పనీర్‌ను అరచేతితో మెత్తగా రుద్దండి. మైదా కలిపి మృదువైన పిండిలా చేసి, పగుళ్లు లేకుండా నునుపైన చిన్న ఉండలు చుట్టండి."
                    )
                ),
                RecipeStep(
                    stepNumber = 3,
                    title = localized("Slow Fry in Pure Ghee", "घी में धीमी आंच पर तलना", "నేతిలో వేయించడం"),
                    instruction = localized(
                        "Heat ghee on low flame. Gently slide balls. Swirl the hot ghee around the balls without touching them directly. Fry on low heat until even golden brown all around.",
                        "धीमी आंच पर घी गरम करें। गोलियां डालकर करछुल से घी को घुमाएं। सुनहरा भूरा होने तक धीमी आंच पर तलें।",
                        "చిన్న మంటపై నెయ్యి వేడి చేయండి. ఉండలను వేసి గరిటెతో నెయ్యిని చుట్టూ తిప్పుతూ బంగారు రంగు వచ్చేంతవరకు వేయించండి."
                    ),
                    timerSeconds = 600,
                    chefTip = localized("Never fry on high heat, otherwise balls stay raw inside.", "आंच कभी तेज न करें, वरना जामुन अंदर से कच्चे रह जाएंगे।", "ఎప్పుడూ పెద్ద మంట పెట్టవద్దు, లేకపోతే లోపల పచ్చిగా ఉండిపోతాయి.")
                ),
                RecipeStep(
                    stepNumber = 4,
                    title = localized("Soak and Savor", "चाशनी में भिगोना", "పాకంలో నానబెట్టడం"),
                    instruction = localized(
                        "Transfer hot fried jamuns directly into warm sugar syrup. Let soak for at least 2 hours to absorb the syrup thoroughly. Serve warm with pistachios.",
                        "तले हुए गरम जामुन को हल्की गरम चाशनी में डालें और 2 घंटे भीगने दें। पिस्ते से सजाकर परोसें।",
                        "వేడి గులాబ్ జామున్లను గోరువెచ్చని చక్కెర పాకంలో వేసి 2 గంటలు నాననివ్వండి. పిస్తా పప్పులతో అలంకరించి వడ్డించండి."
                    ),
                    timerSeconds = 7200
                )
            )
        )
    )
}
