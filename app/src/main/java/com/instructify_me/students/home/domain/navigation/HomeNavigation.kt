package com.instructify_me.students.home.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instructify_me.students.core.domain.navigation.nav_data.Screen
import com.instructify_me.students.feed.presentation.ui.FeedScreen
import com.instructify_me.students.portal.presentation.ui.PortalScreen
import com.instructify_me.students.profile.presentation.ui.ProfileScreen


@Composable
fun HomeNavigation(
    navController: NavHostController,
    requestActiveState: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.HomeScreen.FeedScreen.route,
        route = Screen.MainScreen.HomeScreen.route
    ) {
        composable(route = Screen.MainScreen.HomeScreen.FeedScreen.route) {
            FeedScreen(
                requestActiveState = requestActiveState,
            )
        }

        composable(route = Screen.MainScreen.HomeScreen.PortalScreen.route) {
            PortalScreen(
                requestActiveState = requestActiveState,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }

        composable(route = Screen.MainScreen.HomeScreen.ProfileScreen.route) {
            ProfileScreen(
                requestActiveState = requestActiveState,
                navigateUp = {
                    navController.navigateUp()
                }
            )
        }
    }
}