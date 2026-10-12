package ca.gbc.comp3074.antoine_andre.rentready

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Rentals (placeholder)
@Composable
fun RentalsScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            stringResource(R.string.rentals_title),
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkText,
            modifier = Modifier.padding(20.dp)
        )
        HorizontalDivider(thickness = 1.5.dp, color = BorderDark)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(R.string.rentals_empty),
                color = MutedText,
                fontSize = 16.sp
            )
        }
    }
}