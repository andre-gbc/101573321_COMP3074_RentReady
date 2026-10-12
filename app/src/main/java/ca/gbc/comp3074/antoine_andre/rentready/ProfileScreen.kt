package ca.gbc.comp3074.antoine_andre.rentready

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Profile (placeholder)
@Composable
fun ProfileScreen() {
    val context = LocalContext.current
    val logoutMessage = stringResource(R.string.toast_logged_out)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.profile_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkText,
            modifier = Modifier.padding(20.dp)
        )
        HorizontalDivider(thickness = 1.5.dp, color = BorderDark)

        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(88.dp)
                    .background(AccentGray, CircleShape)
                    .border(1.5.dp, BorderDark, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = null,
                    tint = DarkText,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(Modifier.height(16.dp))

            // sample account information
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                colors = CardDefaults.cardColors(containerColor = CardWhite),
                border = BorderStroke(1.5.dp, BorderDark)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(R.string.profile_name), fontWeight = FontWeight.Bold, color = DarkText)
                    Spacer(Modifier.height(6.dp))
                    Text(stringResource(R.string.profile_email), color = MutedText)
                    Spacer(Modifier.height(6.dp))
                    Text(stringResource(R.string.profile_student_id), color = MutedText)
                }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = { Toast.makeText(context, logoutMessage, Toast.LENGTH_SHORT).show() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkText,
                    contentColor = Color.White
                )
            ) {
                Text(stringResource(R.string.logout_button), fontSize = 18.sp, letterSpacing = 2.sp)
            }
        }
    }
}