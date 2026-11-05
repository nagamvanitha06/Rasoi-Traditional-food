package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.RecipeData
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.model.MealPlanItem
import com.example.model.MealSlot
import com.example.model.PlanDay
import com.example.model.Recipe
import com.example.ui.components.DietIndicatorBadge
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.TurmericGold

@Composable
fun MealPlannerScreen(
    currentLanguage: AppLanguage,
    mealPlan: List<MealPlanItem>,
    onAddMeal: (PlanDay, MealSlot, String) -> Unit,
    onRemoveMeal: (String) -> Unit,
    onClearDay: (PlanDay) -> Unit,
    onGenerateGrocery: () -> Unit,
    onRecipeClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedDay by remember { mutableStateOf(PlanDay.MON) }
    var slotToAssign by remember { mutableStateOf<MealSlot?>(null) }

    val recipesById = remember { RecipeData.traditionalRecipes.associateBy { it.id } }
    val dayMeals = mealPlan.filter { it.day == selectedDay }
    val totalCalories = dayMeals.mapNotNull { recipesById[it.recipeId]?.caloriesPerServing }.sum()

    Column(
        modifier = modifier
            .fillMaxSize()
            .testTag("meal_planner_screen")
    ) {
        // Header Strip
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = Strings.plannerTitle.get(currentLanguage),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronDeep
                        )
                    )
                    Text(
                        text = Strings.plannerSubtitle.get(currentLanguage),
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Action: Generate Grocery List for the Plan
            Button(
                onClick = onGenerateGrocery,
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("generate_weekly_grocery_button"),
                colors = ButtonDefaults.buttonColors(containerColor = SaffronDeep),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = Strings.exportGrocery.get(currentLanguage),
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Days of the Week Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedDay.ordinal,
            edgePadding = 16.dp,
            containerColor = MaterialTheme.colorScheme.surface,
            indicator = {},
            divider = {}
        ) {
            PlanDay.entries.forEach { day ->
                val isSelected = selectedDay == day
                Tab(
                    selected = isSelected,
                    onClick = { selectedDay = day },
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp)
                ) {
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = if (isSelected) SaffronDeep else MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = day.shortLabel.get(currentLanguage),
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                )
                            )
                        }
                    }
                }
            }
        }

        // Daily Summary Strip
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${selectedDay.fullLabel.get(currentLanguage)} • $totalCalories kcal",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )

            if (dayMeals.isNotEmpty()) {
                Text(
                    text = Strings.clearDay.get(currentLanguage),
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .clickable { onClearDay(selectedDay) }
                        .padding(4.dp)
                )
            }
        }

        // Meal Slots for the selected day
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(bottom = 96.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(MealSlot.entries) { slot ->
                val planItem = dayMeals.firstOrNull { it.slot == slot }
                val recipe = planItem?.let { recipesById[it.recipeId] }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (recipe != null) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = if (recipe != null) 2.dp else 0.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = slot.label.get(currentLanguage),
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = SaffronDeep
                                    )
                                )
                                Text(
                                    text = " • ${slot.defaultTime}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                )
                            }

                            if (planItem != null) {
                                IconButton(
                                    onClick = { onRemoveMeal(planItem.id) },
                                    modifier = Modifier.size(28.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = "Remove",
                                        tint = MaterialTheme.colorScheme.error,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        if (recipe != null) {
                            // Assigned recipe row
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { onRecipeClick(recipe) }
                                    .background(MaterialTheme.colorScheme.surfaceVariant)
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    DietIndicatorBadge(isVegetarian = recipe.isVegetarian)
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = recipe.title.get(currentLanguage),
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold
                                            ),
                                            maxLines = 1
                                        )
                                        Text(
                                            text = "${recipe.totalTimeMinutes} min • ${recipe.caloriesPerServing} kcal",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        )
                                    }
                                }

                                Button(
                                    onClick = { onRecipeClick(recipe) },
                                    colors = ButtonDefaults.buttonColors(containerColor = SaffronDeep),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Icon(Icons.Default.PlayArrow, contentDescription = "Cook", modifier = Modifier.size(16.dp))
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Cook", fontSize = 12.sp)
                                }
                            }
                        } else {
                            // Empty slot placeholder
                            OutlinedButton(
                                onClick = { slotToAssign = slot },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Add")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(Strings.addMeal.get(currentLanguage))
                            }
                        }
                    }
                }
            }
        }

        // Pick Recipe Dialog
        slotToAssign?.let { slot ->
            SelectRecipeDialog(
                currentLanguage = currentLanguage,
                slotName = slot.label.get(currentLanguage),
                onDismiss = { slotToAssign = null },
                onSelect = { selectedRecipeId ->
                    onAddMeal(selectedDay, slot, selectedRecipeId)
                    slotToAssign = null
                }
            )
        }
    }
}

@Composable
fun SelectRecipeDialog(
    currentLanguage: AppLanguage,
    slotName: String,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    val recipes = RecipeData.traditionalRecipes

    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Add Dish to $slotName",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(14.dp))

                LazyColumn(
                    modifier = Modifier.height(360.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(recipes) { recipe ->
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .clickable { onSelect(recipe.id) }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                DietIndicatorBadge(isVegetarian = recipe.isVegetarian)
                                Spacer(modifier = Modifier.width(10.dp))
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = recipe.title.get(currentLanguage),
                                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                                        maxLines = 1
                                    )
                                    Text(
                                        text = "${recipe.region.get(currentLanguage)} • ${recipe.totalTimeMinutes}m",
                                        style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    androidx.compose.material3.TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            }
        }
    }
}
