package com.instructify_me.students.core.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.instructify_me.students.auth.presentation.interest.InterestsScreen
import com.instructify_me.students.auth.presentation.register.ui.SignUpScreen
import com.instructify_me.students.auth.presentation.sign_in.ui.SignInScreen
import com.instructify_me.students.core.domain.navigation.nav_data.Screen
import com.instructify_me.students.core.domain.objects.LocalClient
import com.instructify_me.students.core.presentation.splash.SplashScreen
import com.instructify_me.students.home.presentation.ui.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
) {
    NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(
                navigateToSignIn = {
                    navController.navigate(Screen.AuthScreen.SignInScreen.route)
                },
                navigateToSignUp = {
                    navController.navigate(Screen.AuthScreen.SignUpScreen.route)
                },
                navigateToHome = {
                    navController.navigate(Screen.MainScreen.route)
                }
            )
        }

        composable(route = Screen.AuthScreen.SignInScreen.route) {
            SignInScreen(
                onSignedIn = {
                    navController.navigate(Screen.MainScreen.route)
                },
                onCreateNewAccount = {
                    navController.navigate(Screen.AuthScreen.SignUpScreen.route)
                }
            )
        }

        composable(route = Screen.AuthScreen.SignUpScreen.route) {
            SignUpScreen(
                onRegistered = {
                    navController.navigate(Screen.AuthScreen.InterestsScreen.route)
                }
            )
        }

        composable(route = Screen.AuthScreen.InterestsScreen.route) {
            InterestsScreen(
                goToSignIn = {
                    navController.navigate(Screen.AuthScreen.SignInScreen.route)
                }
            )
        }

        composable(route = Screen.MainScreen.route) {
            HomeScreen()
        }
//
//        composable(route = Screen.MainScreen.AssignmentsScreen.ExamScreen.route) {
//            ExamScreen()
//        }
    }
}