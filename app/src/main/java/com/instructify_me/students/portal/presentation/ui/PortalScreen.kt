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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instructify_me.students.R
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.ErrorRed
import com.instructify_me.students.core.presentation.ui.theme.SuccessGreen
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.home.presentation.ui.components.AppBar
import com.instructify_me.students.portal.presentation.info.PortalEvents


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PortalScreen(
    modifier: Modifier = Modifier,
    requestActiveState: () -> Unit,
    navigateUp: () -> Unit,
    viewModel: PortalViewModel = viewModel(modelClass = PortalViewModel::class.java)
) {
    val context = LocalContext.current
    val state = viewModel.state.value

    val filteredTags = mutableListOf<String>()

    LaunchedEffect(Unit) {
        requestActiveState()
        viewModel.onEvent(PortalEvents.LoadTags(context))
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
                    items(state.tags) { tag ->
                        Tag(
                            value = tag,
                            onTagClick = { isSelected ->
                                if (isSelected) filteredTags.add(tag)
                                else filteredTags.remove(tag)

                                viewModel.onEvent(PortalEvents.FilterByTag(filteredTags))
                            }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
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
                        items(state.tutors) { tutor ->
                            TutorCard(
                                image = if (tutor.name == "Rahma Sanad") painterResource(id = R.drawable.tutor_5) else painterResource(id = R.drawable.tutor_4),
                                tutorName = tutor.name,
                                tutorJobTitle = "${tutor.jobTitle} @${tutor.jobSite}",
                                tags = tutor.tags,
                                sessionPrice = tutor.sessionFees,
                                similarity = tutor.similarity,
                                availability = tutor.availability
                            )
                            
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
    tags: List<String> = listOf("python"),
    sessionPrice: String,
    similarity: Double,
    availability: Boolean
){
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
                .background(White, shape = RoundedCornerShape(16.dp))
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            ) {
            LazyRow {
                items(tags.subList(0, if (tags.size > 3) 3 else tags.size)) { tag ->
                    InstructorTag(value = tag)
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = tutorName,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Image(
                            modifier = Modifier.size(12.dp),
                            painter = if (similarity > 0.8) painterResource(id = R.drawable.ic_gold_sim) else if  (similarity > 0.5) painterResource(id = R.drawable.ic_norm_sim) else painterResource(
                                id = R.drawable.bg_ellipse
                            ),
                            contentDescription = null,
                            alpha = if (similarity < 0.5) 0f else 1f
                        )
                    }
                    Text(
                        text = tutorJobTitle,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.LightGray
                    )
                }

                StatusTag(isAvailable = availability)
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
                        text = stringResource(id = R.string.show_info),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = White
                    )
                }
            }
        }
    }
}

@Composable
fun StatusTag(
    modifier: Modifier = Modifier,
    isAvailable: Boolean
) {
    val color = if (isAvailable) SuccessGreen else ErrorRed
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(width = 1.dp, color = color, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 6.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(color),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = stringResource(id = R.string.status),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 10.sp,
                color = color
            )
        }
    }
}

@Composable
fun InstructorTag(
    modifier: Modifier = Modifier,
    value: String
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(DarkBlue)
            .padding(horizontal = 6.dp)
    ) {
        Text(
            text = value,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 8.sp,
            color = Color.White
        )
    }
}

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    value: String,
    onTagClick: (Boolean) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .then(
                if (isSelected) Modifier.background(Blue)
                else Modifier.border(
                    width = 1.dp,
                    color = DarkBlue,
                    shape = RoundedCornerShape(10.dp)
                )
            )
            .clickable {
                isSelected = !isSelected
                onTagClick(isSelected)
            }
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = value,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = if (isSelected) White else DarkBlue
        )
    }
}