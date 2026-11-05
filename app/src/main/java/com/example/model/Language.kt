package com.example.model

enum class AppLanguage(val displayName: String, val code: String, val nativeName: String) {
    ENGLISH("English", "en", "English"),
    HINDI("Hindi", "hi", "हिंदी"),
    TELUGU("Telugu", "te", "తెలుగు")
}

data class LocalizedText(
    val en: String,
    val hi: String,
    val te: String
) {
    fun get(language: AppLanguage): String = when (language) {
        AppLanguage.ENGLISH -> en
        AppLanguage.HINDI -> hi
        AppLanguage.TELUGU -> te
    }
}

fun localized(en: String, hi: String, te: String): LocalizedText = LocalizedText(en, hi, te)
