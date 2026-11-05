package com.example.ui.screens

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.model.MealSlot
import com.example.model.PlanDay
import com.example.model.Recipe
import com.example.ui.components.DietIndicatorBadge
import com.example.ui.theme.CardamomCream
import com.example.ui.theme.ChiliRed
import com.example.ui.theme.CurryGreen
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronLight
import com.example.ui.theme.TurmericGold
import com.example.ui.theme.TurmericLight

@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    currentLanguage: AppLanguage,
    isFavorite: Boolean,
    activeServings: Int,
    onBack: () -> Unit,
    onFavoriteToggle: () -> Unit,
    onServingsChange: (Int) -> Unit,
    onStartCooking: () -> Unit,
    onAddToShoppingList: (Recipe, Double) -> Unit,
    onAddToMealPlan: (PlanDay, MealSlot, String) -> Unit,
    onOpenSubstitutions: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler(onBack = onBack)

    val context = LocalContext.current
    val servingsScale = activeServings.toDouble() / recipe.baseServings.toDouble()
    val checkedIngredients = remember { mutableStateMapOf<Int, Boolean>() }
    var showMealPlanDialog by remember { mutableStateOf(false) }
    var isAddedToShopping by remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("recipe_detail_screen"),
            contentPadding = PaddingValues(bottom = 110.dp)
        ) {
            // Hero Image Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    // Image or Fallback Gradient
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(recipe.imageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = recipe.title.get(currentLanguage),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Scrim gradient
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0.6f),
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.8f)
                                    )
                                )
                            )
                    )

                    // Top Navigation Action Icons
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Black.copy(alpha = 0.5f))
                                .testTag("detail_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            // Share Button
                            IconButton(
                                onClick = {
                                    val shareIntent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "Check out this traditional recipe for ${recipe.title.get(currentLanguage)} on Rasoi App!"
                                        )
                                        type = "text/plain"
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Share Recipe"))
                                },
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.5f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    tint = Color.White
                                )
                            }

                            // Favorite Button
                            IconButton(
                                onClick = onFavoriteToggle,
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.5f))
                                    .testTag("detail_favorite_button")
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorite",
                                    tint = if (isFavorite) ChiliRed else Color.White
                                )
                            }
                        }
                    }

                    // Bottom info overlay on hero image
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            DietIndicatorBadge(isVegetarian = recipe.isVegetarian)
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = SaffronDeep
                            ) {
                                Text(
                                    text = recipe.region.get(currentLanguage),
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = recipe.title.get(currentLanguage),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontSize = 24.sp
                            )
                        )
                    }
                }
            }

            // Description & Cultural Notes
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = recipe.description.get(currentLanguage),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            lineHeight = 22.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Metrics Strip: Prep, Cook, Total, Calories
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        MetricCard(
                            label = Strings.prepTime.get(currentLanguage),
                            value = "${recipe.prepTimeMinutes}m",
                            modifier = Modifier.weight(1f)
                        )
                        MetricCard(
                            label = Strings.cookTime.get(currentLanguage),
                            value = "${recipe.cookTimeMinutes}m",
                            modifier = Modifier.weight(1f)
                        )
                        MetricCard(
                            label = Strings.totalTime.get(currentLanguage),
                            value = "${recipe.totalTimeMinutes}m",
                            modifier = Modifier.weight(1f)
                        )
                        MetricCard(
                            label = Strings.calories.get(currentLanguage),
                            value = "${recipe.caloriesPerServing}",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }

            // Servings Scaler Control
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = Strings.scaleServings.get(currentLanguage),
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                            )
                            Text(
                                text = "Quantities adjust automatically",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    fontSize = 11.sp
                                )
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            IconButton(
                                onClick = { onServingsChange(activeServings - 1) },
                                enabled = activeServings > 1,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surface)
                                    .testTag("servings_decrement")
                            ) {
                                Icon(Icons.Default.Remove, contentDescription = "Decrease", modifier = Modifier.size(18.dp))
                            }

                            Text(
                                text = "$activeServings ${Strings.servings.get(currentLanguage)}",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SaffronDeep
                                )
                            )

                            IconButton(
                                onClick = { onServingsChange(activeServings + 1) },
                                enabled = activeServings < 16,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.surface)
                                    .testTag("servings_increment")
                            ) {
                                Icon(Icons.Default.Add, contentDescription = "Increase", modifier = Modifier.size(18.dp))
                            }
                        }
                    }
                }
            }

            // Ingredients Header & Grocery Action
            item {
                Spacer(modifier = Modifier.height(14.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Strings.ingredientsHeader.get(currentLanguage),
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )

                    OutlinedButton(
                        onClick = {
                            onAddToShoppingList(recipe, servingsScale)
                            isAddedToShopping = true
                        },
                        modifier = Modifier.testTag("add_to_grocery_button")
                    ) {
                        Icon(
                            imageVector = if (isAddedToShopping) Icons.Default.Check else Icons.Default.AddShoppingCart,
                            contentDescription = "Add to grocery",
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isAddedToShopping) Strings.addedToShopping.get(currentLanguage) else Strings.addAllToShopping.get(currentLanguage),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // Ingredients List with Dynamic Quantities & In-line Substitution Notes
            items(recipe.ingredients.indices.toList()) { index ->
                val ingredient = recipe.ingredients[index]
                val isChecked = checkedIngredients[index] == true

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { checkedIngredients[index] = !isChecked }
                        .background(if (isChecked) MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f) else Color.Transparent)
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checkedIngredients[index] = it },
                        colors = CheckboxDefaults.colors(checkedColor = SaffronDeep)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = ingredient.name.get(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = if (isChecked) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onSurface
                            )
                        )

                        ingredient.substituteNote?.let { sub ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .padding(top = 2.dp)
                                    .clickable { onOpenSubstitutions() }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.SwapHoriz,
                                    contentDescription = "Sub",
                                    tint = TurmericGold,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = "Substitute: ${sub.get(currentLanguage)}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = TurmericGold,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                            }
                        }
                    }

                    Text(
                        text = ingredient.formattedQuantity(servingsScale, currentLanguage),
                        style = MaterialTheme.typography.labelMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = SaffronDeep
                        )
                    )
                }
            }

            // Step-by-Step Instructions Header
            item {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = Strings.instructionsHeader.get(currentLanguage),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
                )
            }

            // Steps
            items(recipe.instructions) { step ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .clip(CircleShape)
                                        .background(SaffronDeep),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${step.stepNumber}",
                                        style = MaterialTheme.typography.labelMedium.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                                Text(
                                    text = step.title.get(currentLanguage),
                                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                                )
                            }

                            step.timerSeconds?.let { sec ->
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = SaffronLight
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AccessTime,
                                            contentDescription = "Timer",
                                            tint = SaffronDeep,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "${sec / 60}m",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = SaffronDeep,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = step.instruction.get(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                lineHeight = 21.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        )

                        step.chefTip?.let { tip ->
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = TurmericLight,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = "Tip",
                                        tint = TurmericGold,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = tip.get(currentLanguage),
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            color = Color(0xFF6B4500),
                                            fontSize = 12.sp,
                                            lineHeight = 16.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Sticky Bottom Action Bar
        Surface(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 10.dp,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Add to Meal Plan Button
                OutlinedButton(
                    onClick = { showMealPlanDialog = true },
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.CalendarMonth, contentDescription = "Plan")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(Strings.addMeal.get(currentLanguage), maxLines = 1)
                }

                // Start Hands-free Guided Cooking Button
                Button(
                    onClick = onStartCooking,
                    modifier = Modifier
                        .weight(1.5f)
                        .height(48.dp)
                        .testTag("start_cooking_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = SaffronDeep)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = "Start")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = Strings.cookModeButton.get(currentLanguage),
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }
            }
        }

        // Add to Meal Plan Dialog
        if (showMealPlanDialog) {
            AddToMealPlanDialog(
                currentLanguage = currentLanguage,
                onDismiss = { showMealPlanDialog = false },
                onConfirm = { day, slot ->
                    onAddToMealPlan(day, slot, recipe.id)
                    showMealPlanDialog = false
                }
            )
        }
    }
}

@Composable
fun MetricCard(label: String, value: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SaffronDeep
                )
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 10.sp
                ),
                maxLines = 1
            )
        }
    }
}

@Composable
fun AddToMealPlanDialog(
    currentLanguage: AppLanguage,
    onDismiss: () -> Unit,
    onConfirm: (PlanDay, MealSlot) -> Unit
) {
    var selectedDay by remember { mutableStateOf(PlanDay.MON) }
    var selectedSlot by remember { mutableStateOf(MealSlot.LUNCH) }

    androidx.compose.ui.window.Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Add to Meal Planner",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Select Day:",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    PlanDay.entries.forEach { day ->
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (selectedDay == day) SaffronDeep else MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { selectedDay = day }
                        ) {
                            Text(
                                text = day.shortLabel.get(currentLanguage),
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedDay == day) Color.White else MaterialTheme.colorScheme.onSurfaceVariant
                                ),
                                modifier = Modifier.padding(vertical = 8.dp),
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "Select Meal Slot:",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    MealSlot.entries.forEach { slot ->
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = if (selectedSlot == slot) SaffronLight else MaterialTheme.colorScheme.surfaceVariant,
                            border = if (selectedSlot == slot) androidx.compose.foundation.BorderStroke(1.dp, SaffronDeep) else null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .clickable { selectedSlot = slot }
                        ) {
                            Row(
                                modifier = Modifier.padding(12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = slot.label.get(currentLanguage),
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = if (selectedSlot == slot) FontWeight.Bold else FontWeight.Normal,
                                        color = if (selectedSlot == slot) SaffronDeep else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                                Text(
                                    text = slot.defaultTime,
                                    style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    androidx.compose.material3.TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { onConfirm(selectedDay, selectedSlot) },
                        colors = ButtonDefaults.buttonColors(containerColor = SaffronDeep)
                    ) {
                        Text("Add to Plan")
                    }
                }
            }
        }
    }
}
