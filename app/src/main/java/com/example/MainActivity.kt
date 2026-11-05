package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.RasoiBottomBar
import com.example.ui.components.RasoiTopBar
import com.example.ui.components.VoiceAssistantDialog
import com.example.ui.screens.CookingModeScreen
import com.example.ui.screens.ExploreScreen
import com.example.ui.screens.FavoritesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MealPlannerScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.RecipeDetailScreen
import com.example.ui.screens.ShoppingListScreen
import com.example.ui.screens.SubstitutionsScreen
import com.example.ui.theme.RasoiTheme

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RasoiTheme {
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                var isSubstitutionsScreenOpen by remember { mutableStateOf(false) }

                // Voice Dialog
                if (uiState.isVoiceModalOpen) {
                    VoiceAssistantDialog(
                        voiceAssistant = viewModel.voiceAssistant,
                        currentLanguage = uiState.language,
                        onDismiss = { viewModel.closeVoiceAssistant() },
                        onCommandSelect = { cmd -> viewModel.handleVoiceCommand(cmd) }
                    )
                }

                // Screen Routing
                when {
                    // 1. Hands-Free Guided Cooking Mode
                    uiState.selectedRecipe != null && uiState.isCookingMode -> {
                        CookingModeScreen(
                            recipe = uiState.selectedRecipe!!,
                            currentStepIndex = uiState.currentCookingStep,
                            currentLanguage = uiState.language,
                            isTimerRunning = uiState.isTimerRunning,
                            timerSecondsRemaining = uiState.timerSecondsRemaining,
                            initialTimerDuration = uiState.initialTimerDuration,
                            isSpeakingCurrentStep = uiState.isSpeakingCurrentStep,
                            onNextStep = { viewModel.nextCookingStep() },
                            onPrevStep = { viewModel.prevCookingStep() },
                            onToggleTimer = { viewModel.toggleStepTimer() },
                            onToggleReadAloud = { viewModel.toggleReadCurrentStep() },
                            onExitCooking = { viewModel.closeRecipe() },
                            onOpenVoiceAssistant = { viewModel.openVoiceAssistant() }
                        )
                    }

                    // 2. Recipe Detail Screen
                    uiState.selectedRecipe != null && !uiState.isCookingMode -> {
                        RecipeDetailScreen(
                            recipe = uiState.selectedRecipe!!,
                            currentLanguage = uiState.language,
                            isFavorite = uiState.favoriteRecipeIds.contains(uiState.selectedRecipe!!.id),
                            activeServings = uiState.activeServings,
                            onBack = { viewModel.closeRecipe() },
                            onFavoriteToggle = { viewModel.toggleFavorite(uiState.selectedRecipe!!.id) },
                            onServingsChange = { viewModel.setServings(it) },
                            onStartCooking = { viewModel.startCookingMode() },
                            onAddToShoppingList = { recipe, scale -> viewModel.addRecipeToShoppingList(recipe, scale) },
                            onAddToMealPlan = { day, slot, recipeId -> viewModel.addMealPlanItem(day, slot, recipeId) },
                            onOpenSubstitutions = { isSubstitutionsScreenOpen = true }
                        )
                    }

                    // 3. Ingredient Substitutions Screen
                    isSubstitutionsScreenOpen -> {
                        SubstitutionsScreen(
                            currentLanguage = uiState.language,
                            onBack = { isSubstitutionsScreenOpen = false }
                        )
                    }

                    // 4. Main App Container with Scaffold, TopBar, and BottomBar
                    else -> {
                        BackHandler(enabled = uiState.currentTab != AppTab.HOME) {
                            viewModel.selectTab(AppTab.HOME)
                        }

                        Scaffold(
                            modifier = Modifier.fillMaxSize(),
                            topBar = {
                                RasoiTopBar(
                                    currentLanguage = uiState.language,
                                    onLanguageChange = { viewModel.setLanguage(it) },
                                    onVoiceClick = { viewModel.openVoiceAssistant() },
                                    onProfileClick = { viewModel.selectTab(AppTab.PROFILE) }
                                )
                            },
                            bottomBar = {
                                RasoiBottomBar(
                                    currentTab = uiState.currentTab,
                                    onTabSelected = { viewModel.selectTab(it) },
                                    currentLanguage = uiState.language,
                                    shoppingCount = uiState.shoppingList.count { !it.isChecked },
                                    favoritesCount = uiState.favoriteRecipeIds.size
                                )
                            }
                        ) { innerPadding ->
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                                    .background(MaterialTheme.colorScheme.background)
                            ) {
                                AnimatedContent(
                                    targetState = uiState.currentTab,
                                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                                    label = "tab_content"
                                ) { targetTab ->
                                    when (targetTab) {
                                        AppTab.HOME -> HomeScreen(
                                            currentLanguage = uiState.language,
                                            onLanguageChange = { viewModel.setLanguage(it) },
                                            favoriteIds = uiState.favoriteRecipeIds,
                                            onRecipeClick = { viewModel.openRecipe(it) },
                                            onFavoriteToggle = { viewModel.toggleFavorite(it) },
                                            onNavigateTab = { viewModel.selectTab(it) },
                                            onOpenVoice = { viewModel.openVoiceAssistant() },
                                            onOpenSubstitutions = { isSubstitutionsScreenOpen = true }
                                        )

                                        AppTab.EXPLORE -> ExploreScreen(
                                            currentLanguage = uiState.language,
                                            searchQuery = uiState.searchQuery,
                                            selectedCategory = uiState.selectedCategory,
                                            selectedDiet = uiState.selectedDiet,
                                            selectedMaxTime = uiState.selectedMaxTime,
                                            onSearchChange = { viewModel.updateSearchQuery(it) },
                                            onCategoryChange = { viewModel.updateCategory(it) },
                                            onDietChange = { viewModel.updateDiet(it) },
                                            onMaxTimeChange = { viewModel.updateMaxTime(it) },
                                            favoriteIds = uiState.favoriteRecipeIds,
                                            onRecipeClick = { viewModel.openRecipe(it) },
                                            onFavoriteToggle = { viewModel.toggleFavorite(it) },
                                            onOpenVoice = { viewModel.openVoiceAssistant() }
                                        )

                                        AppTab.PLANNER -> MealPlannerScreen(
                                            currentLanguage = uiState.language,
                                            mealPlan = uiState.mealPlan,
                                            onAddMeal = { day, slot, recipeId -> viewModel.addMealPlanItem(day, slot, recipeId) },
                                            onRemoveMeal = { viewModel.removeMealPlanItem(it) },
                                            onClearDay = { viewModel.clearDayMealPlan(it) },
                                            onGenerateGrocery = { viewModel.generateWeeklyShoppingFromMealPlan() },
                                            onRecipeClick = { viewModel.openRecipe(it) }
                                        )

                                        AppTab.SHOPPING -> ShoppingListScreen(
                                            currentLanguage = uiState.language,
                                            shoppingList = uiState.shoppingList,
                                            onToggleItem = { viewModel.toggleShoppingItem(it) },
                                            onDeleteItem = { viewModel.deleteShoppingItem(it) },
                                            onClearCompleted = { viewModel.clearCheckedShoppingItems() },
                                            onAddCustomItem = { name, qty, cat -> viewModel.addCustomShoppingItem(name, qty, cat) }
                                        )

                                        AppTab.FAVORITES -> FavoritesScreen(
                                            currentLanguage = uiState.language,
                                            favoriteIds = uiState.favoriteRecipeIds,
                                            onRecipeClick = { viewModel.openRecipe(it) },
                                            onFavoriteToggle = { viewModel.toggleFavorite(it) },
                                            onExploreClick = { viewModel.selectTab(AppTab.EXPLORE) }
                                        )

                                        AppTab.PROFILE -> ProfileScreen(
                                            currentLanguage = uiState.language,
                                            userProfile = uiState.userProfile,
                                            favoritesCount = uiState.favoriteRecipeIds.size,
                                            plannedMealsCount = uiState.mealPlan.size,
                                            shoppingItemsCount = uiState.shoppingList.size,
                                            onLanguageChange = { viewModel.setLanguage(it) },
                                            onDietChange = { viewModel.updateDietaryPreference(it) },
                                            onUserNameChange = { viewModel.updateUserName(it) }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
