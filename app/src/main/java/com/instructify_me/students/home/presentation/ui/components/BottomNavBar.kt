package com.instructify_me.students.home.presentation.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.instructify_me.students.R
import com.instructify_me.students.core.presentation.ui.theme.Blue
import com.instructify_me.students.core.presentation.ui.theme.DarkBlue
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.home.domain.navigation.BottomNavScreens

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    isIdeal: Boolean = false,
    onActivePositionChanged: (activeScreen: BottomNavScreens) -> Unit,
    requestActiveState: () -> Unit
) {
    var activePos by remember {
        mutableIntStateOf(if (isIdeal) -1 else 0)
    }
    val interactionSource = remember { MutableInteractionSource() }


    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(White)
            .padding(bottom = 16.dp)
            .height(72.dp)
    ) {
        BottomNavBarItem(
            modifier = Modifier
                .align(Alignment.Bottom)
                .fillMaxHeight()
                .weight(1f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        requestActiveState()
                        activePos = 0
                        onActivePositionChanged(BottomNavScreens.FEED)
                    }
                ),
            text = stringResource(id = R.string.feed),
            imageVector = if(activePos == 0 && !isIdeal) ImageVector.vectorResource(R.drawable.ic_home_active)
            else ImageVector.vectorResource(R.drawable.ic_home_ideal),
            isActive = (activePos == 0 && !isIdeal)
        )
        BottomNavBarItem(
            modifier = Modifier
                .align(Alignment.Bottom)
                .weight(1f)
                .fillMaxHeight()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        requestActiveState()
                        activePos = 3
                        onActivePositionChanged(BottomNavScreens.PORTAL)
                    }
                ),
            text = stringResource(id = R.string.portal),
            imageVector = if(activePos == 3 && !isIdeal) ImageVector.vectorResource(R.drawable.ic_feedbacks_active)
            else ImageVector.vectorResource(R.drawable.ic_feedbacks),
            isActive = (activePos == 3 && !isIdeal)
        )
        BottomNavBarItem(
            modifier = Modifier
                .align(Alignment.Bottom)
                .weight(1f)
                .fillMaxHeight()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        requestActiveState()
                        activePos = 4
                        onActivePositionChanged(BottomNavScreens.PROFILE)
                    }
                ),
            text = stringResource(id = R.string.profile),
            imageVector = if(activePos == 4 && !isIdeal) ImageVector.vectorResource(R.drawable.ic_profile_active)
            else ImageVector.vectorResource(R.drawable.ic_profile),
            isActive = (activePos == 4 && !isIdeal)
        )
    }
}

@Composable
fun BottomNavBarItem(
    modifier: Modifier = Modifier,
    isActive: Boolean = false,
    text: String = "",
    imageVector: ImageVector
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = imageVector,
                contentDescription = text
            )
            Spacer(modifier = Modifier.height(9.dp))
            Text(
                text = text,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = if (isActive) DarkBlue else LightGray
            )
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.TopCenter),
            visible = isActive,
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.horizontalGradient(listOf(DarkBlue, Blue)),
                        shape = RoundedCornerShape(
                            bottomStart = 8.dp, bottomEnd = 8.dp
                        )
                    )
                    .height(5.dp)
            )
        }
    }
}