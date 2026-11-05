package com.example.model

enum class RecipeCategory(val label: LocalizedText) {
    ALL(localized("All Traditional", "सभी पारंपरिक", "అన్ని సాంప్రదాయ")),
    BREAKFAST_TIFFINS(localized("Tiffins & Breakfast", "नाश्ता और टिफिन", "టిఫిన్స్ & అల్పాహారం")),
    RICE_BIRYANI(localized("Rice & Biryani", "चावल और बिरयानी", "అన్నం & బిర్యానీ")),
    CURRIES_DALS(localized("Curries & Dals", "करी और दालें", "కూరలు & పప్పులు")),
    SNACKS_SWEETS(localized("Sweets & Snacks", "मिठाई और स्नैक्स", "స్వీట్లు & స్నాక్స్")),
    TRADITIONAL_SPECIALS(localized("Festival Specials", "त्यौहार विशेष", "పండుగ వంటకాలు"))
}

enum class DietType(val label: LocalizedText) {
    ALL(localized("All", "सभी", "అన్నీ")),
    VEG(localized("Pure Vegetarian", "शुद्ध शाकाहारी", "శుద్ధ శాకాహారం")),
    NON_VEG(localized("Non-Vegetarian", "माँसाहारी", "మాంసాహారం"))
}

enum class SpiceLevel(val label: LocalizedText, val flames: Int) {
    MILD(localized("Mild", "हल्का तीखा", "తక్కువ కారం"), 1),
    MEDIUM(localized("Medium", "मध्यम", "మధ్యస్థ కారం"), 2),
    SPICY(localized("Spicy", "तीखा", "మంచి కారం"), 3),
    VERY_SPICY(localized("Very Spicy", "बहुत तीखा", "చాలా ఘాటు"), 4)
}

enum class IngredientCategory(val label: LocalizedText) {
    PRODUCE(localized("Produce & Veggies", "सब्जियां", "కూరగాయలు")),
    SPICES(localized("Spices & Masalas", "मसाले", "మసాలాలు & పోపులు")),
    DAIRY(localized("Dairy & Ghee", "डेयरी और घी", "డైరీ & నెయ్యి")),
    GRAINS(localized("Grains, Dals & Flours", "अनाज, दाल और आटा", "ధాన్యాలు, పప్పులు & పిండి")),
    PANTRY(localized("Pantry & Oils", "तेल और अन्य", "నూనె & ఇతరములు"))
}

data class RecipeIngredient(
    val name: LocalizedText,
    val baseQuantity: Double,
    val unit: LocalizedText,
    val category: IngredientCategory,
    val substituteNote: LocalizedText? = null
) {
    fun formattedQuantity(scale: Double, language: AppLanguage): String {
        val qty = baseQuantity * scale
        val displayQty = if (qty == qty.toInt().toDouble()) {
            qty.toInt().toString()
        } else {
            String.format(java.util.Locale.US, "%.1f", qty).trimEnd('0').trimEnd('.')
        }
        val unitStr = unit.get(language)
        return if (unitStr.isBlank()) displayQty else "$displayQty $unitStr"
    }
}

data class RecipeStep(
    val stepNumber: Int,
    val title: LocalizedText,
    val instruction: LocalizedText,
    val timerSeconds: Int? = null,
    val chefTip: LocalizedText? = null
)

data class IngredientSubstitution(
    val original: LocalizedText,
    val substitute: LocalizedText,
    val ratio: String,
    val notes: LocalizedText
)

data class Recipe(
    val id: String,
    val title: LocalizedText,
    val description: LocalizedText,
    val region: LocalizedText,
    val category: RecipeCategory,
    val isVegetarian: Boolean,
    val spiceLevel: SpiceLevel,
    val prepTimeMinutes: Int,
    val cookTimeMinutes: Int,
    val baseServings: Int = 4,
    val caloriesPerServing: Int,
    val imageUrl: String,
    val tags: List<String>,
    val ingredients: List<RecipeIngredient>,
    val instructions: List<RecipeStep>,
    val substitutions: List<IngredientSubstitution> = emptyList()
) {
    val totalTimeMinutes: Int get() = prepTimeMinutes + cookTimeMinutes
}
