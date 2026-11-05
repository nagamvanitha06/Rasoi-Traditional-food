package com.example.model

data class UserProfile(
    val id: String = "user_rasoi_1",
    val name: String = "Traditional Gourmet",
    val email: String = "vanithasrin2006@gmail.com",
    val isGoogleUser: Boolean = true,
    val dietaryPreference: DietType = DietType.ALL,
    val spiceTolerance: SpiceLevel = SpiceLevel.MEDIUM,
    val preferredLanguage: AppLanguage = AppLanguage.ENGLISH
)
