package com.instructify_me.students.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.instructify_me.students.core.domain.navigation.AppNavigation
import com.instructify_me.students.core.domain.objects.LocalClient
import com.instructify_me.students.core.domain.objects.ToastManager
import com.instructify_me.students.core.presentation.ui.theme.OutTheme
import com.sanad.studentsapp.core.presentation.toast.CustomToastUI
import com.instructify_me.students.core.presentation.toast.ToastViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OutTheme {
                val navController = rememberNavController()
                val toastViewModel: ToastViewModel = viewModel()
                ToastManager.initializeToastManager(toastViewModel)

                Box(modifier = Modifier.fillMaxSize()) {
                    AppNavigation(
                        navController = navController,
                    )
                    CustomToastUI(
                        modifier = Modifier
                            .padding(top = 56.dp)
                            .alpha(0.9f)
                            .align(Alignment.TopCenter),
                        viewModel = toastViewModel
                    )
                }
                WindowCompat.setDecorFitsSystemWindows(window, false)
            }
        }
    }
}