package com.example.ui.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.AppTab
import com.example.data.Strings
import com.example.model.AppLanguage
import com.example.ui.theme.SaffronDeep
import com.example.ui.theme.SaffronLight

@Composable
fun RasoiBottomBar(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    currentLanguage: AppLanguage,
    shoppingCount: Int,
    favoritesCount: Int,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .testTag("bottom_nav_bar"),
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        // Home
        NavigationBarItem(
            selected = currentTab == AppTab.HOME,
            onClick = { onTabSelected(AppTab.HOME) },
            icon = { Icon(Icons.Default.Home, contentDescription = Strings.navHome.get(currentLanguage)) },
            label = {
                Text(
                    text = Strings.navHome.get(currentLanguage),
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == AppTab.HOME) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronDeep,
                selectedTextColor = SaffronDeep,
                indicatorColor = SaffronLight
            ),
            modifier = Modifier.testTag("nav_tab_home")
        )

        // Explore
        NavigationBarItem(
            selected = currentTab == AppTab.EXPLORE,
            onClick = { onTabSelected(AppTab.EXPLORE) },
            icon = { Icon(Icons.Default.Search, contentDescription = Strings.navExplore.get(currentLanguage)) },
            label = {
                Text(
                    text = Strings.navExplore.get(currentLanguage),
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == AppTab.EXPLORE) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronDeep,
                selectedTextColor = SaffronDeep,
                indicatorColor = SaffronLight
            ),
            modifier = Modifier.testTag("nav_tab_explore")
        )

        // Meal Planner
        NavigationBarItem(
            selected = currentTab == AppTab.PLANNER,
            onClick = { onTabSelected(AppTab.PLANNER) },
            icon = { Icon(Icons.Default.CalendarMonth, contentDescription = Strings.navPlanner.get(currentLanguage)) },
            label = {
                Text(
                    text = Strings.navPlanner.get(currentLanguage),
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == AppTab.PLANNER) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronDeep,
                selectedTextColor = SaffronDeep,
                indicatorColor = SaffronLight
            ),
            modifier = Modifier.testTag("nav_tab_planner")
        )

        // Shopping List
        NavigationBarItem(
            selected = currentTab == AppTab.SHOPPING,
            onClick = { onTabSelected(AppTab.SHOPPING) },
            icon = {
                BadgedBox(
                    badge = {
                        if (shoppingCount > 0) {
                            Badge { Text("$shoppingCount") }
                        }
                    }
                ) {
                    Icon(Icons.Default.ShoppingCart, contentDescription = Strings.navShopping.get(currentLanguage))
                }
            },
            label = {
                Text(
                    text = Strings.navShopping.get(currentLanguage),
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == AppTab.SHOPPING) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronDeep,
                selectedTextColor = SaffronDeep,
                indicatorColor = SaffronLight
            ),
            modifier = Modifier.testTag("nav_tab_shopping")
        )

        // Favorites
        NavigationBarItem(
            selected = currentTab == AppTab.FAVORITES,
            onClick = { onTabSelected(AppTab.FAVORITES) },
            icon = {
                BadgedBox(
                    badge = {
                        if (favoritesCount > 0) {
                            Badge { Text("$favoritesCount") }
                        }
                    }
                ) {
                    Icon(Icons.Default.Favorite, contentDescription = Strings.navFavorites.get(currentLanguage))
                }
            },
            label = {
                Text(
                    text = Strings.navFavorites.get(currentLanguage),
                    fontSize = 11.sp,
                    fontWeight = if (currentTab == AppTab.FAVORITES) FontWeight.Bold else FontWeight.Normal
                )
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = SaffronDeep,
                selectedTextColor = SaffronDeep,
                indicatorColor = SaffronLight
            ),
            modifier = Modifier.testTag("nav_tab_favorites")
        )
    }
}
