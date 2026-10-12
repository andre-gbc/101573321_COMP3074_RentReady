package ca.gbc.comp3074.antoine_andre.rentready

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Reservations
@Composable
fun ReservationsScreen(reservations: MutableList<Reservation>) {
    val context = LocalContext.current
    val cancelledMessage = stringResource(R.string.toast_reservation_cancelled)

    Column(modifier = Modifier.fillMaxSize()) {
        // Header
        Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 16.dp)) {
            Text(
                stringResource(R.string.header_your_account),
                fontSize = 13.sp,
                color = MutedText,
                letterSpacing = 1.sp
            )
            Text(
                stringResource(R.string.reservations_title),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkText
            )
        }
        HorizontalDivider(thickness = 1.5.dp, color = BorderDark)

        Column(modifier = Modifier.padding(20.dp)) {
            // title + count badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(R.string.current_reservations),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = BorderDark,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .background(AccentGray, CircleShape)
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Text("${reservations.size}", fontWeight = FontWeight.Bold, color = DarkText)
                }
            }

            Spacer(Modifier.height(14.dp))

            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(reservations) { reservation ->
                    ReservationCard(
                        reservation = reservation,
                        onCancel = {
                            reservations.remove(reservation)
                            Toast.makeText(context, cancelledMessage, Toast.LENGTH_SHORT).show()
                        }
                    )
                }

                // hint box at the bottom of the list
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, MutedText)
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            stringResource(R.string.reservations_hint),
                            color = MutedText,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ReservationCard(reservation: Reservation, onCancel: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = BorderStroke(1.5.dp, BorderDark)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ImagePlaceholder(reservation.emoji, 80)
                Spacer(Modifier.width(16.dp))
                Column {
                    Text(
                        reservation.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = null,
                            tint = MutedText,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(reservation.dates, color = MutedText)
                    }
                    Spacer(Modifier.height(6.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(BorderDark, CircleShape)
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(stringResource(R.string.status_label), fontSize = 13.sp, color = DarkText)
                        Text(
                            stringResource(R.string.status_reserved),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )
                    }
                }
            }

            Spacer(Modifier.height(14.dp))

            OutlinedButton(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(2.dp),
                border = BorderStroke(2.dp, BorderDark),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = CardWhite,
                    contentColor = DarkText
                )
            ) {
                Text(
                    stringResource(R.string.cancel_button),
                    fontSize = 18.sp,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}