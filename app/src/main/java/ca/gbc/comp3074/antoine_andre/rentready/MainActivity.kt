package ca.gbc.comp3074.antoine_andre.rentready

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.gbc.comp3074.antoine_andre.rentready.ui.theme.RentReadyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentReadyTheme {
                RentReadyApp()
            }
        }
    }
}

// Root composable holds state and switches screens
@Composable
fun RentReadyApp() {
    var currentScreen by remember { mutableStateOf(Screen.HOME) }

    // reservation list here so "cancel" can remove items
    val reservations = remember {
        mutableStateListOf(
            Reservation("DSLR Camera", "Oct 12–14, 2026", "📷"),
            Reservation("Tripod", "Oct 12–14, 2026", "📐")
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppBackground,
        bottomBar = {
            // details screen has no bottom bar
            if (currentScreen != Screen.DETAILS) {
                AppBottomBar(
                    selected = currentScreen,
                    onSelect = { currentScreen = it }
                )
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                Screen.HOME -> HomeScreen(
                    onEquipmentClick = { currentScreen = Screen.DETAILS },
                    onProfileClick = { currentScreen = Screen.PROFILE }
                )
                Screen.DETAILS -> DetailsScreen(
                    onBack = { currentScreen = Screen.HOME }
                )
                Screen.RESERVATIONS -> ReservationsScreen(reservations)
                Screen.RENTALS -> RentalsScreen()
                Screen.PROFILE -> ProfileScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RentReadyPreview() {
    RentReadyTheme {
        RentReadyApp()
    }
}