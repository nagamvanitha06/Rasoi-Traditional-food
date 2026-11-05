package com.example.data

import com.example.model.IngredientSubstitution
import com.example.model.localized

object SubstitutionsData {
    val masterSubstitutions: List<IngredientSubstitution> = listOf(
        IngredientSubstitution(
            original = localized("Tamarind (Imli / Chintapandu)", "इमली (Tamarind)", "చింతపండు (Tamarind)"),
            substitute = localized("Amchur (Dry Mango) or Kokum or Lemon Juice", "अमचूर पाउडर या कोकम या नींबू का रस", "ఆమ్‌చూర్ పొడి, కోకుం లేదా నిమ్మరసం"),
            ratio = "1 tbsp tamarind paste = 1 tsp amchur OR 1 tbsp lemon juice",
            notes = localized(
                "Amchur provides a very close fruity tartness ideal for sambar, rasam, and curries without altering consistency.",
                "अमचूर सांभर, रसम और दाल में इमली जैसा खट्टा स्वाद देता है।",
                "ఆమ్‌చూర్ లేదా నిమ్మరసం సాంబార్, రసంలో చింతపండు లాంటి సహజమైన పులుపును అందిస్తుంది."
            )
        ),
        IngredientSubstitution(
            original = localized("Desi Ghee (Clarified Butter)", "शुद्ध देसी घी (Ghee)", "స్వచ్ఛమైన నెయ్యి (Ghee)"),
            substitute = localized("Cold-Pressed Mustard Oil, Coconut Oil, or Unsalted Butter", "सरसों का तेल, नारियल तेल या मक्खन", "నువ్వుల నూనె, కొబ్బరి నూనె లేదా వెన్న"),
            ratio = "1:1 equal replacement",
            notes = localized(
                "For South Indian dishes use virgin coconut oil; for North Indian curries use mustard oil or butter for rich aroma.",
                "दक्षिण भारतीय व्यंजनों में नारियल तेल और उत्तर भारतीय करी में मक्खन या सरसों का तेल उपयोग करें।",
                "దక్షిణ భారత వంటకాలకు కొబ్బరి నూనె, ఉత్తర భారత వంటకాలకు వెన్న లేదా ఆవనూనె అద్భుతంగా సరిపోతాయి."
            )
        ),
        IngredientSubstitution(
            original = localized("Asafoetida / Hing", "हींग (Asafoetida)", "ఇంగువ (Hing)"),
            substitute = localized("Garlic Powder + Onion Powder pinch", "लहसुन पाउडर + प्याज पाउडर की चुटकी", "వెల్లుల్లి పొడి + ఉల్లిపాయ పొడి చిటికెడు"),
            ratio = "1/4 tsp hing = 1/4 tsp garlic powder",
            notes = localized(
                "If cooking without onion-garlic (sattvic/Jain), use toasted ground cumin and ginger powder instead.",
                "यदि सात्विक भोजन बना रहे हैं, तो भुना जीरा और सोंठ पाउडर का उपयोग करें।",
                "ఉల్లి-వెల్లుల్లి వాడని పక్షంలో వేయించిన జీలకర్ర మరియు అల్లం పొడిని వాడవచ్చు."
            )
        ),
        IngredientSubstitution(
            original = localized("Fresh Curry Leaves (Kadi Patta)", "कढ़ी पत्ता (Curry Leaves)", "కరివేపాకు (Curry Leaves)"),
            substitute = localized("Indian Bay Leaf (Tej Patta) + Lime Zest", "तेजपत्ता + नींबू का छिलका (Zest)", "బిర్యానీ ఆకు (తేజ్‌పత్తా) + నిమ్మ తొక్క చెక్క"),
            ratio = "6-8 curry leaves = 1 small bay leaf + 1/4 tsp lime zest",
            notes = localized(
                "While fresh curry leaves have a distinct aroma, bay leaf with lemon zest mimics the fresh herbal citrus finish in tempering.",
                "तेजपत्ता और नींबू का छिलका तड़के में ताज़ा हर्बल स्वाद लाता है।",
                "పోపులో బిర్యానీ ఆకు మరియు నిమ్మ తొక్క సువాసనను కరివేపాకు లాగే అందిస్తుంది."
            )
        ),
        IngredientSubstitution(
            original = localized("Fresh Paneer", "पनीर (Paneer)", "పనీర్ (Paneer)"),
            substitute = localized("Extra Firm Tofu (Pressed) or Ricotta / Halloumi", "अतिरिक्त सख्त टोफू (Tofu) या हलूमी", "గట్టి సోయా పనీర్ (టోఫు) లేదా కాటేజ్ చీజ్"),
            ratio = "1:1 weight equivalent",
            notes = localized(
                "Press tofu with heavy weight for 15 minutes to drain water, then lightly sauté in ghee or oil before adding to curries.",
                "टोफू को 15 मिनट दबाकर पानी निकालें, फिर हल्का भूनकर करी में डालें। बिल्कुल पनीर जैसा बनेगा।",
                "టోఫు నుండి నీటిని పిండేసి, కొద్దిగా నేతిలో వేయించి కూరలో వేస్తే అచ్చం పనీర్ లాగే ఉంటుంది."
            )
        ),
        IngredientSubstitution(
            original = localized("Jaggery (Gur / Bellam)", "गुड़ (Jaggery)", "బెల్లం (Bellam)"),
            substitute = localized("Palm Jaggery, Coconut Palm Sugar, or Dark Muscovado Sugar", "ताड़ का गुड़, नारियल चीनी या डार्क ब्राउन शुगर", "తాటి బెల్లం, కొబ్బరి చక్కెర లేదా బ్రౌన్ షుగర్"),
            ratio = "1 tbsp jaggery = 1 tbsp coconut/brown sugar",
            notes = localized(
                "Brown sugar gives caramel undertones. Palm jaggery is especially authentic for traditional South Indian payasam and sweets.",
                "ताड़ का गुड़ पारंपरिक खीर और मिठाइयों के लिए सबसे बेहतरीन प्राकृतिक विकल्प है।",
                "తాటి బెల్లం పాయసం మరియు సాంప్రదాయ పిండివంటలకు అత్యంత శ్రేష్టమైన సహజ తీపి."
            )
        ),
        IngredientSubstitution(
            original = localized("Curd / Dahi (Plain Yogurt)", "दही (Curd / Yogurt)", "పెరుగు (Perugu)"),
            substitute = localized("Coconut Milk Yogurt, Greek Yogurt, or Cashew Paste + Lemon", "नारियल दही या काजू पेस्ट + थोड़ा नींबू", "కొబ్బరి పెరుగు లేదా జీడిపప్పు పేస్ట్ + నిమ్మరసం"),
            ratio = "1/2 cup curd = 1/2 cup dairy-free yogurt OR 1/3 cup cashew cream + 1 tsp lemon",
            notes = localized(
                "For rich biryani marinades or gravies, cashew paste with a squeeze of lemon provides equal creamy tang without dairy.",
                "बिरयानी मैरिनेशन और गाढ़ी ग्रेवी के लिए काजू पेस्ट और नींबू का रस एकदम सही क्रीमी विकल्प है।",
                "బిర్యానీ లేదా గ్రేవీల కోసం జీడిపప్పు పేస్ట్ మరియు నిమ్మరసం అద్భుతమైన చిక్కదనాన్ని, పులుపును ఇస్తాయి."
            )
        ),
        IngredientSubstitution(
            original = localized("Kasuri Methi (Dried Fenugreek Leaves)", "कसूरी मेथी (Kasuri Methi)", "కసూరి మేథి (ఎండిన మెంతికూర)"),
            substitute = localized("Fresh Finely Chopped Fenugreek, or Dried Celery Leaves", "ताज़ी बारीक कटी मेथी या सूखी अजवाइन पत्ती", "తాజా సన్నగా తరిగిన మెంతికూర లేదా సెలెరీ ఆకులు"),
            ratio = "1 tbsp dried kasuri methi = 2 tbsp fresh methi (sautéed)",
            notes = localized(
                "Sauté fresh methi leaves in a teaspoon of ghee for 2 minutes before stirring into butter chicken or dal makhani.",
                "ताज़ी मेथी को 1 चम्मच घी में 2 मिनट भूनकर ग्रेवी में मिलाएं।",
                "తాజా మెంతికూరను నేతిలో 2 నిమిషాలు వేయించి గ్రేవీలో కలిపితే అద్భుతమైన సువాసన వస్తుంది."
            )
        ),
        IngredientSubstitution(
            original = localized("Poppy Seeds (Khus Khus / Gasa Gasalu)", "खसखस (Khus Khus)", "గసగసాలు (Gasa Gasalu)"),
            substitute = localized("White Sesame Seeds, Cashew Nut Paste, or Melon Seeds (Magaz)", "सफेद तिल, काजू का पेस्ट या खरबूजे के बीज", "తెల్ల నువ్వులు, జీడిపప్పు పేస్ట్ లేదా పుచ్చగింజలు"),
            ratio = "1 tbsp poppy seeds = 1 tbsp white sesame or 1 tbsp soaked cashew paste",
            notes = localized(
                "Essential for thick traditional Andhra and Hyderabadi gravies. Cashew or melon seed paste reproduces the creamy nutty base.",
                "गाढ़ी पारंपरिक ग्रेवी के लिए भीगे काजू या खरबूजे के बीजों का पेस्ट सबसे उत्तम है।",
                "ఆంధ్ర మరియు రాయలసీమ మసాలా కూరలకు జీడిపప్పు లేదా నువ్వుల పేస్ట్ అద్భుతమైన చిక్కటి గ్రేవీని ఇస్తుంది."
            )
        ),
        IngredientSubstitution(
            original = localized("Kashmiri Red Chili Powder", "कश्मीरी लाल मिर्च (Kashmiri Mirch)", "కాశ్మీరీ ఎర్ర కారం (Kashmiri Mirch)"),
            substitute = localized("Sweet Hungarian Paprika + 1/4 tsp Cayenne Pepper", "मीठी लाल शिमला मिर्च (Paprika) + चुटकी तीखी मिर्च", "స్వీట్ పాప్రికా + చిటికెడు ఘాటైన కారం"),
            ratio = "1 tsp Kashmiri chili = 3/4 tsp sweet paprika + 1/4 tsp hot red chili powder",
            notes = localized(
                "Yields the signature deep royal crimson color in tandoori and curries with gentle warmth and zero burning heat.",
                "यह बिना ज्यादा तीखेपन के शाही लाल रंग और हल्का स्वाद देता है।",
                "ఎక్కువ ఘాటు లేకుండా రాయల్ ఎరుపు రంగును, చక్కటి సువాసనను అందిస్తుంది."
            )
        )
    )
}
