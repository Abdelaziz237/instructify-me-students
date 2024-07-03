package com.instructify_me.students.auth.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.instructify_me.students.R
import com.instructify_me.students.core.presentation.ui.theme.Blue

@Composable
fun CustomCheckbox(
    modifier: Modifier,
    isEditable: Boolean = true,
    isAccepted : (Boolean) -> Unit
) {
    var checked by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (checked) Blue else Color.White)
            .border(
                width = 1.dp,
                color = LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .clickable {
                if (isEditable) {
                    checked = !checked
                    isAccepted(checked)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = ColorPainter(Color.Transparent),
            contentDescription = "Unchecked"
        )
        AnimatedVisibility(
            visible = checked,
            exit = shrinkOut(shrinkTowards = Alignment.TopStart) + fadeOut()
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_check),
                contentDescription = "Checked"
            )
        }
    }
}
