package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.RecipeData
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.model.DietType
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.ui.components.RecipeCard
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronLight

@Composable
fun ExploreScreen(
    currentLanguage: AppLanguage,
    searchQuery: String,
    selectedCategory: RecipeCategory,
    selectedDiet: DietType,
    selectedMaxTime: Int?,
    onSearchChange: (String) -> Unit,
    onCategoryChange: (RecipeCategory) -> Unit,
    onDietChange: (DietType) -> Unit,
    onMaxTimeChange: (Int?) -> Unit,
    favoriteIds: Set<String>,
    onRecipeClick: (Recipe) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    onOpenVoice: () -> Unit,
    modifier: Modifier = Modifier
) {
    val allRecipes = RecipeData.traditionalRecipes

    val filtered = allRecipes.filter { recipe ->
        val matchesCategory = selectedCategory == RecipeCategory.ALL || recipe.category == selectedCategory
        val matchesDiet = when (selectedDiet) {
            DietType.ALL -> true
            DietType.VEG -> recipe.isVegetarian
            DietType.NON_VEG -> !recipe.isVegetarian
        }
        val matchesTime = selectedMaxTime == null || recipe.totalTimeMinutes <= selectedMaxTime
        val q = searchQuery.trim().lowercase()
        val matchesSearch = q.isEmpty() ||
                recipe.title.en.lowercase().contains(q) ||
                recipe.title.hi.lowercase().contains(q) ||
                recipe.title.te.lowercase().contains(q) ||
                recipe.region.en.lowercase().contains(q) ||
                recipe.tags.any { it.lowercase().contains(q) } ||
                recipe.ingredients.any {
                    it.name.en.lowercase().contains(q) ||
                    it.name.hi.lowercase().contains(q) ||
                    it.name.te.lowercase().contains(q)
                }

        matchesCategory && matchesDiet && matchesTime && matchesSearch
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("explore_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Search Input Bar
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearchChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("search_text_field"),
                    placeholder = {
                        Text(
                            text = Strings.searchPlaceholder.get(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = SaffronDeep
                        )
                    },
                    trailingIcon = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(onClick = { onSearchChange("") }) {
                                    Icon(Icons.Default.Clear, contentDescription = "Clear")
                                }
                            }
                            IconButton(onClick = onOpenVoice) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Voice Search",
                                    tint = SaffronDeep
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = SaffronDeep,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }

        // Category Filter Chips
        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(RecipeCategory.entries) { cat ->
                    val isSelected = selectedCategory == cat
                    FilterChip(
                        selected = isSelected,
                        onClick = { onCategoryChange(cat) },
                        label = {
                            Text(
                                text = cat.label.get(currentLanguage),
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = SaffronLight,
                            selectedLabelColor = SaffronDeep
                        )
                    )
                }
            }
        }

        // Diet and Cooking Time Filter Chips
        item {
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Diet filter
                items(DietType.entries) { diet ->
                    val isSelected = selectedDiet == diet
                    FilterChip(
                        selected = isSelected,
                        onClick = { onDietChange(diet) },
                        label = { Text(diet.label.get(currentLanguage)) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                            selectedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    )
                }

                // Time filter chips
                val isQuick = selectedMaxTime == 30
                item {
                    FilterChip(
                        selected = isQuick,
                        onClick = { onMaxTimeChange(if (isQuick) null else 30) },
                        label = { Text(Strings.filterQuick.get(currentLanguage)) }
                    )
                }
            }
        }

        // Results count header
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "${filtered.size} recipes found",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Recipe items or Empty State
        if (filtered.isEmpty()) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, start = 24.dp, end = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Restaurant,
                            contentDescription = "No recipes",
                            tint = SaffronDeep,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "No matching traditional recipes",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Try clearing filters or search by spices, ingredients, or regions.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                }
            }
        } else {
            items(filtered) { recipe ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 6.dp)
                ) {
                    RecipeCard(
                        recipe = recipe,
                        currentLanguage = currentLanguage,
                        isFavorite = favoriteIds.contains(recipe.id),
                        onFavoriteToggle = { onFavoriteToggle(recipe.id) },
                        onClick = { onRecipeClick(recipe) }
                    )
                }
            }
        }
    }
}
