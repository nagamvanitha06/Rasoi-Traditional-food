package com.example

import android.app.Application
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RecipeData
import com.example.data.SubstitutionsData
import com.example.model.AppLanguage
import com.example.model.DietType
import com.example.model.IngredientCategory
import com.example.model.IngredientSubstitution
import com.example.model.MealPlanItem
import com.example.model.MealSlot
import com.example.model.PlanDay
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.model.ShoppingItem
import com.example.model.UserProfile
import com.example.util.VoiceAssistant
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID

enum class AppTab {
    HOME,
    EXPLORE,
    PLANNER,
    SHOPPING,
    FAVORITES,
    PROFILE
}

data class RasoiUiState(
    val language: AppLanguage = AppLanguage.ENGLISH,
    val currentTab: AppTab = AppTab.HOME,
    val selectedRecipe: Recipe? = null,
    val isCookingMode: Boolean = false,
    val currentCookingStep: Int = 0,
    val activeServings: Int = 4,
    val favoriteRecipeIds: Set<String> = setOf("rec_hyderabadi_biryani", "rec_andhra_pesarattu"),
    val searchQuery: String = "",
    val selectedCategory: RecipeCategory = RecipeCategory.ALL,
    val selectedDiet: DietType = DietType.ALL,
    val selectedMaxTime: Int? = null,
    val mealPlan: List<MealPlanItem> = emptyList(),
    val shoppingList: List<ShoppingItem> = emptyList(),
    val userProfile: UserProfile = UserProfile(),
    val isVoiceModalOpen: Boolean = false,
    val isTimerRunning: Boolean = false,
    val timerSecondsRemaining: Int = 0,
    val initialTimerDuration: Int = 0,
    val isSpeakingCurrentStep: Boolean = false,
    val toastMessage: String? = null
)

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(RasoiUiState())
    val uiState: StateFlow<RasoiUiState> = _uiState.asStateFlow()

    private var countDownTimer: CountDownTimer? = null
    val voiceAssistant: VoiceAssistant = VoiceAssistant(application) { recognized ->
        handleVoiceCommand(recognized)
    }

    init {
        initDefaultMealPlan()
        initDefaultShoppingList()
    }

    private fun initDefaultMealPlan() {
        val initialPlan = listOf(
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.MON, MealSlot.BREAKFAST, "rec_andhra_pesarattu"),
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.MON, MealSlot.LUNCH, "rec_gutti_vankaya"),
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.MON, MealSlot.DINNER, "rec_dal_makhani"),
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.WED, MealSlot.BREAKFAST, "rec_temple_pulihora"),
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.SUN, MealSlot.LUNCH, "rec_hyderabadi_biryani"),
            MealPlanItem(UUID.randomUUID().toString(), PlanDay.SUN, MealSlot.SNACKS, "rec_royal_gulab_jamun")
        )
        _uiState.update { it.copy(mealPlan = initialPlan) }
    }

    private fun initDefaultShoppingList() {
        val sampleItems = listOf(
            ShoppingItem(UUID.randomUUID().toString(), "Aged Basmati Rice", "500 g", IngredientCategory.GRAINS, false, "Hyderabadi Dum Biryani"),
            ShoppingItem(UUID.randomUUID().toString(), "Fresh Paneer / Chicken", "600 g", IngredientCategory.PRODUCE, false, "Hyderabadi Dum Biryani"),
            ShoppingItem(UUID.randomUUID().toString(), "Whole Green Gram (Pesalu)", "2 cups", IngredientCategory.GRAINS, true, "Andhra Pesarattu"),
            ShoppingItem(UUID.randomUUID().toString(), "Pure Desi Ghee", "250 ml", IngredientCategory.DAIRY, false, "Heritage Pantry"),
            ShoppingItem(UUID.randomUUID().toString(), "Curry Leaves & Coriander", "2 bunches", IngredientCategory.PRODUCE, false, "Heritage Pantry"),
            ShoppingItem(UUID.randomUUID().toString(), "Saffron strands (Kesar)", "1 box", IngredientCategory.SPICES, false, "Royal Gulab Jamun")
        )
        _uiState.update { it.copy(shoppingList = sampleItems) }
    }

    fun setLanguage(language: AppLanguage) {
        _uiState.update {
            it.copy(
                language = language,
                userProfile = it.userProfile.copy(preferredLanguage = language)
            )
        }
    }

    fun selectTab(tab: AppTab) {
        _uiState.update { it.copy(currentTab = tab, selectedRecipe = null, isCookingMode = false) }
    }

    fun openRecipe(recipe: Recipe) {
        _uiState.update {
            it.copy(
                selectedRecipe = recipe,
                activeServings = recipe.baseServings,
                isCookingMode = false,
                currentCookingStep = 0
            )
        }
    }

    fun closeRecipe() {
        if (_uiState.value.isCookingMode) {
            stopTimer()
            voiceAssistant.stopSpeaking()
            _uiState.update { it.copy(isCookingMode = false, isSpeakingCurrentStep = false) }
        } else {
            _uiState.update { it.copy(selectedRecipe = null) }
        }
    }

    fun toggleFavorite(recipeId: String) {
        _uiState.update { state ->
            val updated = if (state.favoriteRecipeIds.contains(recipeId)) {
                state.favoriteRecipeIds - recipeId
            } else {
                state.favoriteRecipeIds + recipeId
            }
            state.copy(favoriteRecipeIds = updated)
        }
    }

    fun setServings(servings: Int) {
        if (servings in 1..16) {
            _uiState.update { it.copy(activeServings = servings) }
        }
    }

    fun startCookingMode() {
        val recipe = _uiState.value.selectedRecipe ?: return
        _uiState.update {
            it.copy(
                isCookingMode = true,
                currentCookingStep = 0,
                isTimerRunning = false,
                timerSecondsRemaining = 0
            )
        }
        setupStepTimer(0)
    }

    fun nextCookingStep() {
        val recipe = _uiState.value.selectedRecipe ?: return
        val next = _uiState.value.currentCookingStep + 1
        if (next < recipe.instructions.size) {
            voiceAssistant.stopSpeaking()
            stopTimer()
            _uiState.update { it.copy(currentCookingStep = next, isSpeakingCurrentStep = false) }
            setupStepTimer(next)
            readCurrentStepAloud()
        }
    }

    fun prevCookingStep() {
        val prev = _uiState.value.currentCookingStep - 1
        if (prev >= 0) {
            voiceAssistant.stopSpeaking()
            stopTimer()
            _uiState.update { it.copy(currentCookingStep = prev, isSpeakingCurrentStep = false) }
            setupStepTimer(prev)
            readCurrentStepAloud()
        }
    }

    private fun setupStepTimer(stepIndex: Int) {
        val recipe = _uiState.value.selectedRecipe ?: return
        val step = recipe.instructions.getOrNull(stepIndex) ?: return
        val duration = step.timerSeconds ?: 0
        _uiState.update {
            it.copy(
                initialTimerDuration = duration,
                timerSecondsRemaining = duration,
                isTimerRunning = false
            )
        }
    }

    fun toggleStepTimer() {
        if (_uiState.value.isTimerRunning) {
            stopTimer()
        } else {
            startTimer()
        }
    }

    private fun startTimer() {
        val remaining = _uiState.value.timerSecondsRemaining
        if (remaining <= 0) return

        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer((remaining * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _uiState.update { it.copy(timerSecondsRemaining = (millisUntilFinished / 1000).toInt()) }
            }

            override fun onFinish() {
                _uiState.update { it.copy(isTimerRunning = false, timerSecondsRemaining = 0) }
                triggerVibration()
                val lang = _uiState.value.language
                val msg = when (lang) {
                    AppLanguage.ENGLISH -> "Cooking timer finished!"
                    AppLanguage.HINDI -> "कुकिंग टाइमर समाप्त हुआ!"
                    AppLanguage.TELUGU -> "వంట టైమర్ పూర్తయింది!"
                }
                voiceAssistant.speak(msg, lang)
            }
        }.start()

        _uiState.update { it.copy(isTimerRunning = true) }
    }

    fun stopTimer() {
        countDownTimer?.cancel()
        countDownTimer = null
        _uiState.update { it.copy(isTimerRunning = false) }
    }

    private fun triggerVibration() {
        try {
            val vibrator = getApplication<Application>().getSystemService<Vibrator>()
            vibrator?.vibrate(VibrationEffect.createOneShot(800, VibrationEffect.DEFAULT_AMPLITUDE))
        } catch (_: Exception) {}
    }

    fun toggleReadCurrentStep() {
        if (_uiState.value.isSpeakingCurrentStep) {
            voiceAssistant.stopSpeaking()
            _uiState.update { it.copy(isSpeakingCurrentStep = false) }
        } else {
            readCurrentStepAloud()
        }
    }

    private fun readCurrentStepAloud() {
        val recipe = _uiState.value.selectedRecipe ?: return
        val step = recipe.instructions.getOrNull(_uiState.value.currentCookingStep) ?: return
        val lang = _uiState.value.language
        val textToRead = "${step.title.get(lang)}. ${step.instruction.get(lang)}"
        voiceAssistant.speak(textToRead, lang)
        _uiState.update { it.copy(isSpeakingCurrentStep = true) }
    }

    // Voice Command Handler
    fun handleVoiceCommand(spokenText: String) {
        val lower = spokenText.lowercase().trim()
        val lang = _uiState.value.language

        when {
            lower.contains("next") || lower.contains("aage") || lower.contains("अगला") || lower.contains("తరువాత") -> {
                if (_uiState.value.isCookingMode) nextCookingStep()
            }
            lower.contains("back") || lower.contains("previous") || lower.contains("peeche") || lower.contains("पिछला") || lower.contains("మునుపటి") -> {
                if (_uiState.value.isCookingMode) prevCookingStep()
            }
            lower.contains("timer") || lower.contains("start timer") || lower.contains("समय") || lower.contains("టైమర్") -> {
                if (_uiState.value.isCookingMode) toggleStepTimer()
            }
            lower.contains("read") || lower.contains("repeat") || lower.contains("सुनो") || lower.contains("చదువు") -> {
                if (_uiState.value.isCookingMode) readCurrentStepAloud()
            }
            lower.contains("stop") || lower.contains("pause") || lower.contains("रुको") || lower.contains("ఆపు") -> {
                voiceAssistant.stopSpeaking()
                stopTimer()
            }
            lower.contains("biryani") || lower.contains("बिरयानी") || lower.contains("బిర్యానీ") -> {
                val found = RecipeData.traditionalRecipes.firstOrNull { it.id == "rec_hyderabadi_biryani" }
                if (found != null) openRecipe(found)
            }
            lower.contains("pesarattu") || lower.contains("पेसरट्टू") || lower.contains("పెసరట్టు") -> {
                val found = RecipeData.traditionalRecipes.firstOrNull { it.id == "rec_andhra_pesarattu" }
                if (found != null) openRecipe(found)
            }
            lower.contains("veg") || lower.contains("शाकाहारी") || lower.contains("శాకాహారం") -> {
                _uiState.update { it.copy(currentTab = AppTab.EXPLORE, selectedDiet = DietType.VEG) }
            }
            lower.contains("shopping") || lower.contains("grocery") || lower.contains("किराना") || lower.contains("షాపింగ్") -> {
                selectTab(AppTab.SHOPPING)
            }
            lower.contains("plan") || lower.contains("planner") || lower.contains("भोजन") || lower.contains("ప్లాన్") -> {
                selectTab(AppTab.PLANNER)
            }
            else -> {
                // If not matched to direct navigation, execute as search query
                _uiState.update {
                    it.copy(
                        currentTab = AppTab.EXPLORE,
                        searchQuery = spokenText
                    )
                }
            }
        }
        _uiState.update { it.copy(isVoiceModalOpen = false) }
    }

    fun openVoiceAssistant() {
        _uiState.update { it.copy(isVoiceModalOpen = true) }
        voiceAssistant.startListening(_uiState.value.language)
    }

    fun closeVoiceAssistant() {
        voiceAssistant.stopListening()
        _uiState.update { it.copy(isVoiceModalOpen = false) }
    }

    // Search and filters
    fun updateSearchQuery(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun updateCategory(category: RecipeCategory) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun updateDiet(diet: DietType) {
        _uiState.update { it.copy(selectedDiet = diet) }
    }

    fun updateMaxTime(minutes: Int?) {
        _uiState.update { it.copy(selectedMaxTime = minutes) }
    }

    // Shopping List management
    fun addRecipeToShoppingList(recipe: Recipe, scale: Double = 1.0) {
        val lang = _uiState.value.language
        val recipeName = recipe.title.get(lang)
        val newItems = recipe.ingredients.map { ing ->
            ShoppingItem(
                id = UUID.randomUUID().toString(),
                name = ing.name.get(lang),
                quantity = ing.formattedQuantity(scale, lang),
                category = ing.category,
                isChecked = false,
                recipeSource = recipeName
            )
        }
        _uiState.update { it.copy(shoppingList = it.shoppingList + newItems) }
    }

    fun toggleShoppingItem(itemId: String) {
        _uiState.update { state ->
            val updated = state.shoppingList.map { item ->
                if (item.id == itemId) item.copy(isChecked = !item.isChecked) else item
            }
            state.copy(shoppingList = updated)
        }
    }

    fun deleteShoppingItem(itemId: String) {
        _uiState.update { state ->
            state.copy(shoppingList = state.shoppingList.filter { it.id != itemId })
        }
    }

    fun clearCheckedShoppingItems() {
        _uiState.update { state ->
            state.copy(shoppingList = state.shoppingList.filter { !it.isChecked })
        }
    }

    fun addCustomShoppingItem(name: String, quantity: String, category: IngredientCategory) {
        if (name.isBlank()) return
        val newItem = ShoppingItem(
            id = UUID.randomUUID().toString(),
            name = name.trim(),
            quantity = quantity.trim(),
            category = category,
            isChecked = false,
            recipeSource = "Custom Staple"
        )
        _uiState.update { it.copy(shoppingList = it.shoppingList + newItem) }
    }

    // Meal Planner management
    fun addMealPlanItem(day: PlanDay, slot: MealSlot, recipeId: String) {
        val newItem = MealPlanItem(
            id = UUID.randomUUID().toString(),
            day = day,
            slot = slot,
            recipeId = recipeId
        )
        _uiState.update { it.copy(mealPlan = it.mealPlan + newItem) }
    }

    fun removeMealPlanItem(itemId: String) {
        _uiState.update { state ->
            state.copy(mealPlan = state.mealPlan.filter { it.id != itemId })
        }
    }

    fun clearDayMealPlan(day: PlanDay) {
        _uiState.update { state ->
            state.copy(mealPlan = state.mealPlan.filter { it.day != day })
        }
    }

    fun generateWeeklyShoppingFromMealPlan() {
        val recipesById = RecipeData.traditionalRecipes.associateBy { it.id }
        val allNewItems = mutableListOf<ShoppingItem>()
        val lang = _uiState.value.language

        _uiState.value.mealPlan.forEach { planItem ->
            val recipe = recipesById[planItem.recipeId]
            if (recipe != null) {
                val rName = "${recipe.title.get(lang)} (${planItem.day.shortLabel.get(lang)})"
                recipe.ingredients.forEach { ing ->
                    allNewItems.add(
                        ShoppingItem(
                            id = UUID.randomUUID().toString(),
                            name = ing.name.get(lang),
                            quantity = ing.formattedQuantity(1.0, lang),
                            category = ing.category,
                            isChecked = false,
                            recipeSource = rName
                        )
                    )
                }
            }
        }
        _uiState.update {
            it.copy(
                shoppingList = it.shoppingList + allNewItems,
                currentTab = AppTab.SHOPPING
            )
        }
    }

    // User Profile
    fun updateDietaryPreference(diet: DietType) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(dietaryPreference = diet)) }
    }

    fun updateUserName(name: String) {
        _uiState.update { it.copy(userProfile = it.userProfile.copy(name = name)) }
    }

    override fun onCleared() {
        super.onCleared()
        stopTimer()
        voiceAssistant.destroy()
    }
}
