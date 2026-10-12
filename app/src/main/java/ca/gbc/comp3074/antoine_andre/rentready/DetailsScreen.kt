package ca.gbc.comp3074.antoine_andre.rentready

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
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

// Equipment Details
@Composable
fun DetailsScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    val startedMessage = stringResource(R.string.toast_reservation_started)

    Column(modifier = Modifier.fillMaxSize()) {
        // Top bar: back button + title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = DarkText)
            }
            Text(
                stringResource(R.string.details_title),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }
        HorizontalDivider(thickness = 1.5.dp, color = BorderDark)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            // big image placeholder
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp)
                    .background(PlaceholderGray)
                    .border(1.5.dp, MutedText),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("📷", fontSize = 48.sp)
                    Text(
                        stringResource(R.string.equipment_image),
                        color = MutedText,
                        letterSpacing = 1.sp,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            // category and name adn availability
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        stringResource(R.string.details_category),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = MutedText,
                        letterSpacing = 2.sp
                    )
                    Text(
                        stringResource(R.string.details_name),
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = DarkText
                    )
                }
                StatusPill(stringResource(R.string.status_available), reserved = false)
            }

            Spacer(Modifier.height(14.dp))
            HorizontalDivider(color = AccentGray)
            Spacer(Modifier.height(14.dp))

            // description
            Text(
                stringResource(R.string.details_description_label),
                fontWeight = FontWeight.Bold,
                color = DarkText,
                letterSpacing = 1.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                stringResource(R.string.details_description),
                fontSize = 16.sp,
                color = MutedText
            )

            Spacer(Modifier.height(20.dp))

            // rental information box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PlaceholderGray)
                    .border(1.dp, MutedText)
                    .padding(16.dp)
            ) {
                Text(
                    stringResource(R.string.rental_information),
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = DarkText,
                    letterSpacing = 1.sp
                )
                Spacer(Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = DarkText)
                    Spacer(Modifier.width(10.dp))
                    Text(stringResource(R.string.select_dates), color = MutedText)
                }
            }

            Spacer(Modifier.height(20.dp))

            // reserve button -> shows a Toast
            Button(
                onClick = {
                    Toast.makeText(context, startedMessage, Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkText,
                    contentColor = Color.White
                )
            ) {
                Text(
                    stringResource(R.string.reserve_button),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}