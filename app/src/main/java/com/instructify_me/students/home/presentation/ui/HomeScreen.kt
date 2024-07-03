package com.instructify_me.students.home.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.instructify_me.students.core.domain.navigation.nav_data.Screen
import com.instructify_me.students.home.domain.navigation.BottomNavScreens
import com.instructify_me.students.home.domain.navigation.HomeNavigation
import com.instructify_me.students.home.presentation.ui.components.BottomNavBar

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    var isIdeal by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                HomeNavigation(
                    navController = navController,
                    idealBottomNav = {
                        isIdeal = true
                    },
                    requestActiveState = {
                        isIdeal = false
                    },
                )
            }
            BottomNavBar(
                isIdeal = isIdeal,
                requestActiveState = {
                    isIdeal = false
                },
                onActivePositionChanged = { activeScreen: BottomNavScreens ->
                    when (activeScreen) {
                        BottomNavScreens.FEED -> {
                            navController.navigate(Screen.MainScreen.HomeScreen.FeedScreen.route)
                        }

                        BottomNavScreens.PORTAL -> {
                            navController.navigate(Screen.MainScreen.HomeScreen.PortalScreen.route)
                        }

                        BottomNavScreens.PROFILE -> {
                            navController.navigate(Screen.MainScreen.HomeScreen.ProfileScreen.route)
                        }
                    }
                }
            )
        }
    }
}