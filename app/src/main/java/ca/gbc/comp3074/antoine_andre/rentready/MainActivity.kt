package ca.gbc.comp3074.antoine_andre.rentready

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
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
    val context = LocalContext.current
    var currentScreen by remember { mutableStateOf(Screen.HOME) }
    var localNetworkGranted by remember {
        mutableStateOf(hasLocalNetworkAccess(context))
    }
    var hasRequestedPermission by rememberSaveable { mutableStateOf(false) }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        localNetworkGranted = granted
    }

    // Ask once automatically. Later attempts only happen from the button.
    LaunchedEffect(Unit) {
        if (!localNetworkGranted && !hasRequestedPermission) {
            hasRequestedPermission = true
            permissionLauncher.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
        }
    }

    val requestLocalNetworkPermission = {
        hasRequestedPermission = true
        permissionLauncher.launch(Manifest.permission.ACCESS_LOCAL_NETWORK)
    }

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
                    localNetworkGranted = localNetworkGranted,
                    onRequestPermission = requestLocalNetworkPermission,
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

private fun hasLocalNetworkAccess(context: Context): Boolean {
    // Local network permission is required only for apps targeting Android 17+.
    if (Build.VERSION.SDK_INT < 37) return true

    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_LOCAL_NETWORK
    ) == PackageManager.PERMISSION_GRANTED
}

@Preview(showBackground = true)
@Composable
fun RentReadyPreview() {
    RentReadyTheme {
        RentReadyApp()
    }
}