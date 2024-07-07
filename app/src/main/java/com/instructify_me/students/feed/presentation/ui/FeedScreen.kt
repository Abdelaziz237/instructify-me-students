package com.instructify_me.students.feed.presentation.ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
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
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.DarkGray
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.feed.presentation.info.FeedEvents

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    requestActiveState: () -> Unit,
    viewModel: FeedViewModel = viewModel(modelClass = FeedViewModel::class.java)
) {
    var isLoading by remember { mutableStateOf(false) }
//    var isDialogVisible by remember { mutableStateOf(false) }


    val state = viewModel.state.value

    LaunchedEffect(Unit) {
        requestActiveState()
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(24.dp))

            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp),
                    color = DarkGray,
                    trackColor = Color.LightGray,
                    strokeCap = StrokeCap.Round
                )
            }

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

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(3) {
                    when(it) {
                        0 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Android development",
                                tutorName = "Sanad Mohammed",
                                date = "24 Sep",
                                votes = "12",
                                commentsCount = "2",
                                onCommentClick = {
                                    viewModel.onEvent(FeedEvents.OpenAddCommentDialog)
                                }
                            )
                        }
                        1 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Data Science",
                                tutorName = "Rahma Sanad",
                                date = "26 Sep",
                                votes = "16",
                                commentsCount = "5",
                                onCommentClick = {
                                    viewModel.onEvent(FeedEvents.OpenAddCommentDialog)
                                }
                            )
                        }
                        2 -> {
                            FeedItem(
                                request = "I want an instructor to explain to me the basics of the Web development",
                                tutorName = "Sanad Mohammed",
                                date = "30 Sep",
                                votes = "20",
                                commentsCount = "8",
                                onCommentClick = {
                                    viewModel.onEvent(FeedEvents.OpenAddCommentDialog)
                                }
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
        if (state.isAddCommentDialogVisible) {
            CommentDialog(
                modifier = Modifier.align(Alignment.Center),
                onConfirm = {

                },
                onDismissRequest = {
                    viewModel.onEvent(FeedEvents.DismissAddCommentDialog)
                }
            )
        }

        if (state.isPostDialogVisible) {
            PostDialog(
                modifier = Modifier.align(Alignment.Center),
                onConfirm = {

                },
                onDismissRequest = {
                    viewModel.onEvent(FeedEvents.DismissPostDialog)
                }
            )
        }

        CircularFAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 16.dp, end = 32.dp),
            onClick = {
                viewModel.onEvent(FeedEvents.OpenPostDialog)
            }
        )
    }
}

@Composable
fun CircularFAB(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .size(64.dp),
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        onClick = {
            onClick()
        }
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            DarkBlue,
                            Blue
                        ),
                    ),
                    shape = CircleShape
                ),
            contentScale = ContentScale.Inside,
            painter = painterResource(id = R.drawable.ic_plus),
            contentDescription = null
        )
    }
}

@Composable
fun FeedItem(
    request: String,
    tutorName: String,
    date: String,
    votes: String,
    commentsCount: String,
    onCommentClick: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(horizontal = 16.dp)
            .shadow(1.dp, shape = RoundedCornerShape(16.dp))
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                isExpanded = !isExpanded
            }
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

            Row(verticalAlignment = Alignment.CenterVertically) {
                VoteTag(text = votes)
                Spacer(modifier = Modifier.width(10.dp))
                CommentTag(text = commentsCount)
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    modifier = Modifier
                        .size(16.dp)
                        .clip(CircleShape)
                        .clickable {
                            onCommentClick()
                        },
                    painter = painterResource(id = R.drawable.ic_reply),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(8.dp))
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                
                CommentItem(senderName = "Ahmed", text = "Great tutor!")
                CommentItem(senderName = "Khaled", text = "Great tutor!")
            }
        }
    }
}

@Composable
fun CommentItem(
    senderName: String,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .padding(vertical = 12.dp)
    ) {
        Box(modifier = Modifier
            .height(48.dp)
            .width(1.dp)
            .background(Color.Black))

        Spacer(modifier = Modifier.width(4.dp))

        Image(
            modifier = Modifier
                .size(24.dp)
                .clip(CircleShape),
            painter = painterResource(id = R.drawable.tutor_3),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = senderName,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                fontSize = 11.sp
            )
        }
    }
}

data class Vote(var up: Boolean, var down: Boolean)
@Composable
fun VoteTag(
    text: String,
) {
    var vote by remember { mutableStateOf(Vote(up = false, down = false)) }

    Box(
        modifier = Modifier
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .clickable {
                        vote = Vote(!vote.up, false)
                    },
                painter = if (vote.up) painterResource(id = R.drawable.ic_up_vote) else painterResource(id = R.drawable.ic_vote),
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

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .rotate(180f)
                    .clickable {
                        vote = Vote(false, !vote.down)
                    },
                painter = if (vote.down) painterResource(id = R.drawable.ic_down_vote) else painterResource(id = R.drawable.ic_vote),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}


@Composable
fun CommentTag(
    text: String,
) {
    Box(
        modifier = Modifier
            .background(
                color = DarkGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.width(4.dp))

            Image(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_comment),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun CommentDialog(
    modifier: Modifier,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    var comment by remember { mutableStateOf("") }

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
                containerColor = Color.White
            ),
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(id = R.string.comment),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )

                BasicTextField(
                    label = stringResource(id = R.string.comment_label),
                    onValueChanged = {
                        comment = it
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(id = R.string.post),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun PostDialog(
    modifier: Modifier,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    var post by remember { mutableStateOf("") }

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
                containerColor = Color.White
            ),
        ) {
            Column {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    modifier = Modifier.padding(start = 20.dp),
                    text = stringResource(id = R.string.post_label),
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )

                BasicTextField(
                    label = stringResource(id = R.string.title),
                    onValueChanged = {
                        post = it
                    }
                )

                BasicTextField(
                    label = stringResource(id = R.string.description),
                    onValueChanged = {
                        post = it
                    }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Button(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.End),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp),
                    onClick = {
                        onConfirm()
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = stringResource(id = R.string.post),
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}