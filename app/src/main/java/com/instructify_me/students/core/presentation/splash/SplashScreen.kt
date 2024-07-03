package com.instructify_me.students.core.presentation.splash

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instructify_me.students.R
import com.instructify_me.students.core.domain.objects.LocalClient
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.LightBlue
import com.instructify_me.students.core.presentation.ui.theme.fontFamily

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
    client: LocalClient,
    viewModel: SplashViewModel = viewModel(modelClass = SplashViewModel::class.java, factory = SplashViewModel.SplashViewModelFactory(client))
) {
    val state = viewModel.state.value

    LaunchedEffect(key1 = true) {
        viewModel.hasValidUser()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .imePadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            if (state.isLoading) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 16.dp),
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.weight(1f))
            } else {
                if (state.hasUser) {
                    navigateToHome()
                } else {

                    Spacer(modifier = Modifier.fillMaxHeight(0.2f))

                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(horizontal = 16.dp),
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.height(48.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            text = stringResource(id = R.string.explore_app),
                            fontWeight = FontWeight.Black,
                            fontFamily = fontFamily,
                            fontSize = 32.sp,
                            textAlign = TextAlign.Center,
                            color = Color.Black,
                        )

                        Text(
                            text = stringResource(id = R.string.welcome_message),
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = fontFamily,
                            fontSize = 20.sp,
                            color = Color.Black,
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonColors(
                            containerColor = Color.Black,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        onClick = {
                            navigateToSignIn()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.sign_in),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.White,
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .padding(horizontal = 16.dp),
                        border = BorderStroke(1.dp, Color.Black),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            disabledContentColor = Color.Transparent
                        ),
                        onClick = {
                            navigateToSignUp()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.create_account),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black,
                        )
                    }
                    Spacer(modifier = Modifier.height(48.dp))
                }
            }
        }
    }
}