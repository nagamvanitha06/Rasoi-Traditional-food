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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.AppTab
import com.example.data.RecipeData
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.model.Recipe
import com.example.model.RecipeCategory
import com.example.ui.components.RecipeCard
import com.example.ui.theme.CardamomCream
import com.example.ui.theme.CurryGreen
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronOrange
import com.example.ui.theme.TurmericGold

@Composable
fun HomeScreen(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    favoriteIds: Set<String>,
    onRecipeClick: (Recipe) -> Unit,
    onFavoriteToggle: (String) -> Unit,
    onNavigateTab: (AppTab) -> Unit,
    onOpenVoice: () -> Unit,
    onOpenSubstitutions: () -> Unit,
    modifier: Modifier = Modifier
) {
    val recipes = RecipeData.traditionalRecipes
    val featuredRoyal = recipes.filter { it.category == RecipeCategory.RICE_BIRYANI || it.category == RecipeCategory.SNACKS_SWEETS }
    val teluguHeritage = recipes.filter { it.region.en.contains("Andhra") || it.region.en.contains("Telangana") }
    val traditionalDals = recipes.filter { it.category == RecipeCategory.CURRIES_DALS }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 96.dp)
    ) {
        // Hero Culinary Banner
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(SaffronDeep, SaffronOrange, TurmericGold)
                        )
                    )
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color.Black.copy(alpha = 0.25f)
                        ) {
                            Text(
                                text = "TRADITIONAL KITCHEN",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                ),
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }

                        // Voice Prompt Pill
                        Surface(
                            shape = RoundedCornerShape(16.dp),
                            color = Color.White.copy(alpha = 0.25f),
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .clickable { onOpenVoice() }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Mic,
                                    contentDescription = "Voice Search",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Voice Assistant",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = Color.White,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ENGLISH -> "Master Authentic Traditional Indian Cooking"
                            AppLanguage.HINDI -> "सीखें भारत के असली पारंपरिक शाही स्वाद"
                            AppLanguage.TELUGU -> "మన ప్రాచీన తెలుగు & భారతీయ వంటల వైభవం"
                        },
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            lineHeight = 28.sp
                        )
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.ENGLISH -> "Hyderabadi Dum Biryani, Andhra Pesarattu, Gutti Vankaya, Slow-cooked Dal Makhani & Temple Pulihora."
                            AppLanguage.HINDI -> "दम बिरयानी, पेसरट्टू, भरवां बैंगन, दाल मखनी और मंदिर प्रसादम।"
                            AppLanguage.TELUGU -> "హైదరాబాదీ బిర్యానీ, పెసరట్టు, గుత్తి వంకాయ, కోవిల్ పులిహోర మరియు గులాబ్ జామున్."
                        },
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Language Quick Pills inside banner
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AppLanguage.entries.forEach { lang ->
                            val isSelected = currentLanguage == lang
                            Surface(
                                shape = RoundedCornerShape(20.dp),
                                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.2f),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .clickable { onLanguageChange(lang) }
                            ) {
                                Text(
                                    text = lang.nativeName,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = if (isSelected) SaffronDeep else Color.White
                                    ),
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Quick Search Bar Box
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shadowElevation = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { onNavigateTab(AppTab.EXPLORE) }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = SaffronDeep
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = Strings.searchPlaceholder.get(currentLanguage),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            ),
                            modifier = Modifier.weight(1f)
                        )
                        IconButton(
                            onClick = onOpenVoice,
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "Voice",
                                tint = SaffronDeep
                            )
                        }
                    }
                }
            }
        }

        // Ingredient Substitution Card (Feature highlight!)
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable { onOpenSubstitutions() }
                    .testTag("ingredient_substitution_card"),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(TurmericGold),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.SwapHoriz,
                                contentDescription = "Substitutions",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = Strings.substitutionsTitle.get(currentLanguage),
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer
                                )
                            )
                            Text(
                                text = Strings.substitutionsSubtitle.get(currentLanguage),
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                                    fontSize = 12.sp
                                ),
                                maxLines = 1
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "View",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }

        // Section: Andhra & Telangana Ruchulu
        item {
            SectionHeader(
                title = Strings.teluguSpecials.get(currentLanguage),
                onViewAll = { onNavigateTab(AppTab.EXPLORE) },
                currentLanguage = currentLanguage
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(teluguHeritage) { recipe ->
                    Box(modifier = Modifier.width(280.dp)) {
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

        // Section: Royal Heritage Specials
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = Strings.featuredSection.get(currentLanguage),
                onViewAll = { onNavigateTab(AppTab.EXPLORE) },
                currentLanguage = currentLanguage
            )
        }

        item {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(featuredRoyal) { recipe ->
                    Box(modifier = Modifier.width(280.dp)) {
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

        // Section: Curries and Dals
        item {
            Spacer(modifier = Modifier.height(16.dp))
            SectionHeader(
                title = RecipeCategory.CURRIES_DALS.label.get(currentLanguage),
                onViewAll = { onNavigateTab(AppTab.EXPLORE) },
                currentLanguage = currentLanguage
            )
        }

        items(traditionalDals) { recipe ->
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

@Composable
fun SectionHeader(
    title: String,
    onViewAll: () -> Unit,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        )
        TextButton(onClick = onViewAll) {
            Text(
                text = Strings.viewAll.get(currentLanguage),
                style = MaterialTheme.typography.labelMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = SaffronDeep
                )
            )
        }
    }
}
