package com.example.model

enum class PlanDay(val shortLabel: LocalizedText, val fullLabel: LocalizedText) {
    MON(localized("Mon", "सोम", "సోమ"), localized("Monday", "सोमवार", "సోమవారం")),
    TUE(localized("Tue", "मंगल", "మంగళ"), localized("Tuesday", "मंगलवार", "మంగళవారం")),
    WED(localized("Wed", "बुध", "బుధ"), localized("Wednesday", "बुधवार", "బుధవారం")),
    THU(localized("Thu", "गुरु", "గురు"), localized("Thursday", "गुरुवार", "గురువారం")),
    FRI(localized("Fri", "शुक्र", "శుక్ర"), localized("Friday", "शुक्रवार", "శుక్రవారం")),
    SAT(localized("Sat", "शनि", "శని"), localized("Saturday", "शनिवार", "శనివారం")),
    SUN(localized("Sun", "रवि", "ఆది"), localized("Sunday", "रविवार", "ఆదివారం"))
}

enum class MealSlot(val label: LocalizedText, val defaultTime: String) {
    BREAKFAST(localized("Breakfast", "नाश्ता", "అల్పాహారం"), "08:30 AM"),
    LUNCH(localized("Lunch", "दोपहर का खाना", "మధ్యాహ్న భోజనం"), "01:00 PM"),
    SNACKS(localized("Evening Snacks", "शाम का नाश्ता", "సాయంత్రం స్నాక్స్"), "05:00 PM"),
    DINNER(localized("Dinner", "रात का खाना", "రాత్రి భోజనం"), "08:30 PM")
}

data class MealPlanItem(
    val id: String,
    val day: PlanDay,
    val slot: MealSlot,
    val recipeId: String,
    val note: String = ""
)
