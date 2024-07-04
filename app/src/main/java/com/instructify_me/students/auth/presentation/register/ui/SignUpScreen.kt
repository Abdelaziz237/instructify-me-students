package com.instructify_me.students.auth.presentation.register.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instructify_me.students.R
import com.instructify_me.students.auth.presentation.components.BasicTextField
import com.instructify_me.students.auth.presentation.components.PasswordTextField
import com.instructify_me.students.auth.presentation.register.info.RegisterPageEvent
import com.instructify_me.students.auth.presentation.register.viewmodel.RegisterViewModel
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.LightBlue
import com.instructify_me.students.core.presentation.ui.theme.fontFamily

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    onRegistered: () -> Unit,
    viewModel: RegisterViewModel = viewModel(modelClass = RegisterViewModel::class.java)
) {
    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val isLoading = viewModel.state.value

    Box(modifier = modifier
        .background(Color.White)) {
        Column {
            if (isLoading) {
                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = DarkBlue,
                    trackColor = Color.LightGray,
                    strokeCap = StrokeCap.Round
                )
            }
            Box(modifier = Modifier
                .weight(3f)
                .padding(top = 24.dp)
            ) {
                Column {
                    Spacer(modifier = Modifier.fillMaxHeight(0.15f))

                    Image(
                        modifier = Modifier
                            .size(104.dp)
                            .padding(horizontal = 16.dp),
                        painter = painterResource(id = R.drawable.app_logo),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(id = R.string.welcome_back),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black,
                        fontSize = 28.sp,
                    )

                    BasicTextField(
                        stringResource(id = R.string.username),
                        onValueChanged = { value ->
                            username = value
                        }
                    )

                    BasicTextField(
                        stringResource(id = R.string.email),
                        onValueChanged = { value ->
                            email = value
                        }
                    )
                    PasswordTextField(
                        onValueChanged = { value ->
                            password = value
                        }
                    )
                }
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .padding(start = 16.dp, end = 16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            viewModel.onEvent(RegisterPageEvent.StartLoading, callback = { })
                            viewModel.onEvent(RegisterPageEvent.Register(username, email, password), onRegistered)
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.register),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = Color.White
                        )
                    }
                    Row (modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(id = R.string.already_have_account),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = DarkBlue
                        )
                        TextButton(
                            onClick = {
                                onRegistered()
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.login),
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.LightGray
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}