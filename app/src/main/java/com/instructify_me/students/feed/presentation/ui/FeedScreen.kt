package com.instructify_me.students.feed.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
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
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkGray
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.portal.presentation.ui.Tag

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    requestActiveState: () -> Unit,
    navigateUp: () -> Unit
) {
    LaunchedEffect(Unit) {
        requestActiveState()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(88.dp)
                    .padding(start = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = stringResource(id = R.string.feed),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 48.sp,
                    color = Color.Black
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(4) {
                    when(it) {
                        0 -> FilterTag(value = stringResource(id = R.string.latest), selectionValue = true)
                        1 -> FilterTag(value = stringResource(id = R.string.popular))
                        2 -> FilterTag(value = stringResource(id = R.string.unread))
                    }
                    Spacer(modifier = Modifier.width(6.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(3) {
                    when(it) {
                        0 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Android development",
                                tutorName = "Sanad Mohammed",
                                date = "24 Sep"
                            )
                        }
                        1 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Android development",
                                tutorName = "Sanad Mohammed",
                                date = "24 Sep"
                            )
                        }
                        2 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Android development",
                                tutorName = "Sanad Mohammed",
                                date = "24 Sep"
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}

@Composable
fun FilterTag(
    modifier: Modifier = Modifier,
    value: String,
    selectionValue: Boolean = false
) {
    var isSelected by remember { mutableStateOf(selectionValue) }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                isSelected = !isSelected
            }
            .then(
                if (isSelected) Modifier.background(
                    Color.Black,
                    shape = RoundedCornerShape(16.dp)
                ) else Modifier.border(1.dp, Color.Black, shape = RoundedCornerShape(16.dp))
            )
            .padding(horizontal = 8.dp)
    ) {
        Text(
            text = value,
            fontFamily = fontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = if (isSelected) Color.White else Color.Black
        )
    }
}


@Composable
fun FeedItem(
    request: String,
    tutorName: String,
    date: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .shadow(1.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Image(
            modifier = Modifier
                .size(56.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.tutor_3),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = request,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = tutorName,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
                Spacer(modifier = Modifier.width(4.dp))
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_separator),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = date,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row {
                VoteUpTag(
                    text = "956",
                )
                Spacer(modifier = Modifier.width(10.dp))
                VoteDownTag(
                    text = "65"
                )
            }
        }
    }
}

@Composable
fun VoteUpTag(
    text: String,
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { isSelected = !isSelected }
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape),
                painter = if (isSelected) painterResource(id = R.drawable.ic_up_vote) else painterResource(id = R.drawable.ic_vote),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White
            )

        }
    }
}


@Composable
fun VoteDownTag(
    text: String
) {
    var isSelected by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .clickable { isSelected = !isSelected }
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .rotate(180f),
                painter = if (isSelected) painterResource(id = R.drawable.ic_down_vote) else painterResource(id = R.drawable.ic_vote),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White
            )

        }
    }
}