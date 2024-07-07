package com.instructify_me.students.core.domain.navigation.nav_data


sealed class Screen(val route: String) {
    object SplashScreen: Screen(route = Routes.SPLASH_SCREEN.name)

//    object OnBoardingScreen: Screen(route = ON_BOARDING_SCREEN) {
//        object Boarding1: Screen(route = ON_BOARDING_SCREEN_1)
//        object Boarding2: Screen(route = ON_BOARDING_SCREEN_2)
//        object Boarding3: Screen(route = ON_BOARDING_SCREEN_3)
//    }

    object AuthScreen: Screen(route = Routes.AUTH_SCREEN.name) {
        object SignInScreen: Screen(route = Routes.SIGN_IN_SCREEN.name)
        object SignUpScreen: Screen(route = Routes.SIGN_UP_SCREEN.name)
        object InterestsScreen: Screen(route = Routes.INTERESTS_SCREEN.name)

//        object OtpVerificationScreen: Screen(route = OTP_VERIFICATION_SCREEN)

//        object DetailSignUpScreen: Screen(route = DETAIL_SIGN_UP_SCREEN) {
//            object PhoneInfoScreen: Screen(route = PHONE_INFO_SCREEN)
//            object BirthDateInfoScreen: Screen(route = BIRTH_DATE_INFO_SCREEN)
//            object AddressInfoScreen: Screen(route = ADDRESS_INFO_SCREEN)
//            object ProfileImageScreen: Screen(route = PROFILE_IMAGE_SCREEN)
//        }

//        object ForgotPasswordScreen: Screen(route = FORGOT_PASSWORD_SCREEN)
    }

    object MainScreen: Screen(route = Routes.MAIN_SCREEN.name) {
        object HomeScreen: Screen(route = Routes.HOME_SCREEN.name) {
            object FeedScreen: Screen(route = Routes.FEED_SCREEN.name)
            object PortalScreen: Screen(route = Routes.PORTAL_SCREEN.name)
            object TutorInfoScreen: Screen(route = Routes.TUTOR_INFO_SCREEN.name)
            object ProfileScreen: Screen(route = Routes.PROFILE_SCREEN.name)
        }

    }

}