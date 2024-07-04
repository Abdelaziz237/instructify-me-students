package com.instructify_me.students.profile.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
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
import com.instructify_me.students.core.presentation.ui.theme.fontFamily
import com.instructify_me.students.home.presentation.ui.components.AppBar


@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    requestActiveState: () -> Unit,
    navigateUp: () -> Unit
) {
    LaunchedEffect(Unit) {
        requestActiveState()
    }

    Box(
        modifier = modifier
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .alpha(0.6f),
            imageVector = ImageVector.vectorResource(id = R.drawable.bg_header),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(text = stringResource(id = R.string.profile), navigateUp = navigateUp)

            Spacer(modifier = Modifier.fillMaxHeight(0.1f))

            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.ic_student),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sanad Mohammed Sanad",
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = Color.Black
            )

            Text(
                text = "sanad@sanad.com",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(1.dp, shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White, shape = RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                LazyColumn {
                    item {
                        ProfileItem(
                            icon = painterResource(id = R.drawable.ic_profile_active),
                            text = stringResource(id = R.string.phone_number),
                            info = "01151689482"
                        )
                    }
                    item {
                        ProfileItem(
                            icon = painterResource(id = R.drawable.ic_tag),
                            text = stringResource(id = R.string.my_skills),
                            info = "Python, JavaScript, Java, C++, C#",
                        )
                    }
                    item {
                        ProfileItem(
                            icon = painterResource(id = R.drawable.ic_info),
                            text = stringResource(id = R.string.info),
                            info = "I'm CS graduated from FCAI"
                        )
                    }
                    item {
                        ProfileItem(
                            icon = painterResource(id = R.drawable.ic_edu),
                            text = stringResource(id = R.string.education),
                            info = "GPA: 3.88, Faculty of Computers and Artificial Intelligence"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileItem(
    icon: Painter,
    text: String,
    info: String
) {
   Row(
       modifier = Modifier
           .clip(RoundedCornerShape(16.dp))
           .clickable { }
           .padding(8.dp)
           .padding(vertical = 10.dp),
       verticalAlignment = Alignment.CenterVertically
   ) {
       Box(
           modifier = Modifier
               .size(32.dp),
           contentAlignment = Alignment.Center
       ) {
           Image(
               modifier = Modifier.size(32.dp),
               imageVector = ImageVector.vectorResource(id = R.drawable.bg_ellipse),
               contentDescription = null
           )
           Image(
               modifier = Modifier.size(24.dp),
               painter = icon,
               contentDescription = null
           )
       }
       Spacer(modifier = Modifier.width(16.dp))
       Column(
           modifier = Modifier.weight(1f).padding(end = 8.dp)
       ) {
           Text(
               text = text,
               fontFamily = fontFamily,
               fontWeight = FontWeight.SemiBold,
               fontSize = 16.sp,
               color = Color.Black
           )

           Text(
               text = info,
               fontFamily = fontFamily,
               fontWeight = FontWeight.Normal,
               fontSize = 14.sp,
               color = Color.Black
           )
       }
       Image(
           modifier = Modifier.size(24.dp),
           imageVector = ImageVector.vectorResource(id = R.drawable.ic_forward),
           contentDescription = null
       )
   }
}