package com.instructify_me.students.auth.presentation.interest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.instructify_me.students.R
import com.instructify_me.students.auth.presentation.interest.info.InterestsEvent
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkGray
import com.instructify_me.students.core.presentation.ui.theme.LightBlue
import com.instructify_me.students.core.presentation.ui.theme.SkyBlue
import com.instructify_me.students.core.presentation.ui.theme.fontFamily

@Composable
fun InterestsScreen(
    modifier: Modifier = Modifier,
    goToSignIn: () -> Unit,
    viewModel: InterestsViewModel = viewModel()
) {
    val topics = listOf("Technology", "Python" , "Front-end" , "JavaScript" ,
        "Mathematics", "Android Development" , "Machine Learning" , "Data Science", "Data Analysis", "Data Structures",
        "Algorithms", "Artificial Intelligence", "Web Development", "DTS", "Red-Hat tree")

    val selectedTopics = mutableListOf<String>()

    Box(
        modifier = modifier
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(id = R.string.interests),
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 32.sp,
                color = Color.Black,
                lineHeight = 40.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.choose_interests),
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(3),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(topics) { topic ->
                        TopicCircle(
                            topic = topic,
                            onTopicSelected = { isSelected, topic ->
                                if (isSelected) {
                                    selectedTopics.add(topic)
                                } else {
                                    selectedTopics.remove(topic)
                                }
                            }
                        )
                    }
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = DarkGray, shape = CircleShape)
                    .clickable {
                        if (selectedTopics.size >= 5) {
                            viewModel.onEvent(
                                InterestsEvent.PostInterests(
                                    interests = selectedTopics.toList()
                                ),
                                callback = {
                                    goToSignIn()
                                }
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ){
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape),
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_forward),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}


@Composable
fun TopicCircle(
    topic: String,
    onTopicSelected: (Boolean, String) -> Unit,
) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    
    Box(
        modifier = Modifier
            .size((topic.length * 3 + 55).dp)
            .clip(CircleShape)
            .then(
                if (isSelected) Modifier.background(
                    color = listOf(
                        LightBlue,
                        SkyBlue,
                        Blue
                    ).random(), shape = CircleShape
                )
                else Modifier
                    .background(color = Color.Transparent)
                    .border(width = 1.dp, color = DarkGray, shape = CircleShape),
            )
            .clickable {
                isSelected = !isSelected
                onTopicSelected(isSelected, topic)
            },
        contentAlignment = Alignment.Center
    ){
        Text(
            text = topic,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}