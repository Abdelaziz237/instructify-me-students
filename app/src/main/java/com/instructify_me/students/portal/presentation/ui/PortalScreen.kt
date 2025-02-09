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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instructify_me.students.R
import com.instructify_me.students.auth.presentation.components.BasicTextField
import com.instructify_me.students.auth.presentation.interest.TopicCircle
import com.instructify_me.students.auth.presentation.sign_in.info.LoginPageEvent
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.ErrorRed
import com.instructify_me.students.core.presentation.ui.theme.SuccessGreen
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.home.presentation.ui.components.AppBar
import com.instructify_me.students.portal.presentation.info.PortalEvents
import com.instructify_me.students.portal.presentation.info.TutorState


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

    var isInfoDialogVisible by remember { mutableIntStateOf(-1) }

    val filteredTags = mutableListOf<String>()

    LaunchedEffect(Unit) {
        requestActiveState()
        viewModel.onEvent(PortalEvents.LoadTags(context))
        viewModel.onEvent(PortalEvents.LoadTutors)
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
                        itemsIndexed(state.tutors) { i, tutor ->
                            TutorCard(
                                image = painterResource(id = R.drawable.tutor_4),
                                tutorName = tutor.name,
                                tutorJobTitle = "${tutor.jobTitle} @${tutor.jobSite}",
                                tags = tutor.tags,
                                sessionPrice = tutor.sessionFees,
                                similarity = tutor.similarity,
                                availability = tutor.availability,
                                onClick = {
                                    isInfoDialogVisible = i
                                }
                            )
                            
                            Spacer(modifier = Modifier.width(16.dp))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        if (isInfoDialogVisible != -1){
            TutorInfoCard(
                tutor = state.tutors[isInfoDialogVisible],
                onDismissRequest = {
                    isInfoDialogVisible = -1
                },
                onConfirm = {

                }
            )
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
    availability: Boolean,
    onClick: () -> Unit
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
                        onClick()
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
fun TutorInfoCard(
    modifier: Modifier = Modifier,
    tutor: TutorState,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit
) {
    Dialog(
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        ),
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            ),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .align(Alignment.Start),
                    text = stringResource(id = R.string.tutor_info_lable),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(18.dp))

                Image(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = R.drawable.tutor_4),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = tutor.name,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                LazyRow {
                    items(tutor.tags) { tag ->
                        InstructorTag(value = tag)
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    text = tutor.bio,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                    text = stringResource(id = R.string.day),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(6),
                    verticalItemSpacing = 4.dp,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    content = {
                        items(7) { day ->
                            when (day) {
                                0 -> TimeTag(
                                    value = "Sat",
                                    onTagClick = {

                                    }
                                )
                                1 -> TimeTag(
                                    value = "Sun",
                                    onTagClick = {

                                    }
                                )
                                2 -> TimeTag(
                                    value = "Mon",
                                    onTagClick = {

                                    }
                                )
                                3 -> TimeTag(
                                    value = "Tue",
                                    onTagClick = {

                                    }
                                )
                                4 -> TimeTag(
                                    value = "Wed",
                                    onTagClick = {

                                    }
                                )
                                5 -> TimeTag(
                                    value = "Thu",
                                    onTagClick = {

                                    }
                                )
                            }

                        }
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 16.dp),
                    text = stringResource(id = R.string.hour),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(8.dp))

                LazyVerticalStaggeredGrid(
                    columns = StaggeredGridCells.Fixed(3),
                    verticalItemSpacing = 8.dp,
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    content = {
                        items(23) { time ->
                            if (time > 11) {
                                TimeTag(value = formatToTime(time)) {}
                            }
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(horizontal = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.hourly_rate) + " ${tutor.sessionFees} EGP",
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = DarkBlue
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkBlue
                        ),
                        shape = RoundedCornerShape(16.dp),
                        onClick = {
                            onConfirm()
                        }
                    ) {
                        Text(
                            modifier = Modifier.padding(horizontal = 20.dp),
                            text = stringResource(id = R.string.book_),
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = White
                        )
                    }
                }



                Spacer(modifier = Modifier.height(20.dp))
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

@Composable
fun TimeTag(
    modifier: Modifier = Modifier,
    value: String,
    onTagClick: (Boolean) -> Unit
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .then(
                if (isSelected) Modifier.background(Blue)
                else Modifier.border(
                    width = 1.dp,
                    color = DarkBlue,
                    shape = RoundedCornerShape(8.dp)
                )
            )
            .clickable {
                isSelected = !isSelected
                onTagClick(isSelected)
            }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            color = if (isSelected) White else DarkBlue
        )
    }
}

private fun formatToTime(i: Int): String{
    if (i == 12) return "12:00 pm"
    if (i == 0) return "12:00 am"
    return if (i < 12) if (i < 10) "0$i:00 am" else "$i:00 am"
    else if (i - 12 < 10) "0${i - 12}:00 pm" else "${i - 12}:00 pm"
}