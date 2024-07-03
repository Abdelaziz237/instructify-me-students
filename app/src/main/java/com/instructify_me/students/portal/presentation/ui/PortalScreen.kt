package com.instructify_me.students.portal.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.instructify_me.students.R
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.home.presentation.ui.components.AppBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortalScreen(modifier: Modifier = Modifier, requestActiveState: () -> Unit, navigateUp: () -> Unit) {

    LaunchedEffect(Unit) {
        requestActiveState()
    }

    Box(modifier = modifier) {
        Column {
            AppBar(
                text = stringResource(id = R.string.portal),
                navigateUp = navigateUp
            )

            Spacer(modifier = Modifier.height(24.dp))

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color.Black,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.portal_title),
                        modifier = Modifier.basicMarquee(),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = stringResource(id = R.string.categories),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkBlue
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyRow {
                    items(12) {
                        Tag(value = "Biology")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(id = R.string.recommended_tutors),
                    modifier = Modifier.basicMarquee(),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = DarkBlue
                )

                Spacer(modifier = Modifier.height(8.dp))

                Box(modifier = Modifier.weight(1f)) {
                    LazyRow {
                        items(6) {
                            when (it) {
                                0 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_4),
                                        tutorName = "Mohammed Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }

                                1 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_1),
                                        tutorName = "Mohammed Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }

                                2 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_2),
                                        tutorName = "Mohammed Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }

                                3 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_3),
                                        tutorName = "Mohammed Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }

                                4 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_5),
                                        tutorName = "Rahma Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }

                                5 -> {
                                    TutorCard(
                                        image = painterResource(id = R.drawable.tutor_6),
                                        tutorName = "Huda Sanad",
                                        tutorJobTitle = "Business Advisor",
                                        sessionPrice = "150"
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }


                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun TutorCard(
    modifier: Modifier = Modifier,
    image: Painter,
    tutorName: String,
    tutorJobTitle: String,
    sessionPrice: String
){
    var booked by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .width(300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.85f)
                    .clip(RoundedCornerShape(16.dp)),
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = tutorName,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                    Text(
                        text = tutorJobTitle,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                }
                Image(
                    modifier = Modifier
                        .size(14.dp)
                        .clickable { booked = !booked },
                    painter = if (booked) painterResource(id = R.drawable.ic_bookmark_active) else painterResource(id = R.drawable.ic_bookmark_ideal),
                    contentDescription = null
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$sessionPrice EGP",
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    color = Blue
                )

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Blue
                    ),
                    shape = RoundedCornerShape(50.dp),
                    onClick = {

                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.book),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    value: String
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                isSelected = !isSelected
            }
            .padding(horizontal = 8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(14.dp),
                painter = if (isSelected) painterResource(id = R.drawable.ic_biology_active) else painterResource(id = R.drawable.ic_biology_ideal),
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(4.dp))

            Text(
                text = value,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = if (isSelected) Blue else Color.Black
            )
        }
    }
}