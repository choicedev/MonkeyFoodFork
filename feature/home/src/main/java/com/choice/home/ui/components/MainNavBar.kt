package com.choice.home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.choice.design.util.BottomNavItem
import com.choice.theme.MonkeyTheme
import com.google.accompanist.insets.navigationBarsPadding


@Composable
fun BottomBarNavigation(
    navController: NavController
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .clip(MonkeyTheme.shapes.medium),
        backgroundColor = MonkeyTheme.colors.primaryVariant,
        contentColor = MonkeyTheme.colors.onPrimary
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination
        BottomNavItem.items.forEach { item ->
            val select = currentRoute?.hierarchy?.any { it.route == item.route } == true
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                        tint = if (select) MonkeyTheme.colors.onPrimary else MonkeyTheme.colors.primary
                    )
                },
                label = {
                        Text(
                            text = item.title,
                            style = MonkeyTheme.typography.subtitle2.copy(
                                color = if(select) MonkeyTheme.colors.onPrimary else MonkeyTheme.colors.primary
                            )
                        )
                    },
                    selectedContentColor = MonkeyTheme.colors.onPrimary,
                    unselectedContentColor = MonkeyTheme.colors.primary.copy(0.4f),
                    selected = select,
                    onClick = {
                        if(currentRoute?.route != item.route) {
                            navController.navigate(item.route) {

                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }

                        }
                    }
                )
            }
    }
}