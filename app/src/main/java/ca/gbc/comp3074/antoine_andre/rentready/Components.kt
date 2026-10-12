package ca.gbc.comp3074.antoine_andre.rentready

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Bottom navigation bar
data class NavItem(val screen: Screen, val labelRes: Int, val icon: ImageVector)

@Composable
fun AppBottomBar(selected: Screen, onSelect: (Screen) -> Unit) {
    val items = listOf(
        NavItem(Screen.HOME, R.string.nav_home, Icons.Default.Home),
        NavItem(Screen.RESERVATIONS, R.string.nav_reservations, Icons.Default.DateRange),
        NavItem(Screen.RENTALS, R.string.nav_rentals, Icons.AutoMirrored.Filled.List),
        NavItem(Screen.PROFILE, R.string.nav_profile, Icons.Default.Person)
    )

    Column {
        HorizontalDivider(thickness = 1.5.dp, color = BorderDark)
        NavigationBar(containerColor = AppBackground) {
            items.forEach { item ->
                NavigationBarItem(
                    selected = selected == item.screen,
                    onClick = { onSelect(item.screen) },
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(stringResource(item.labelRes), fontSize = 11.sp, maxLines = 1) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = DarkText,
                        selectedTextColor = DarkText,
                        unselectedIconColor = MutedText,
                        unselectedTextColor = MutedText,
                        indicatorColor = AccentGray
                    )
                )
            }
        }
    }
}

// stand in for an equipment picture
@Composable
fun ImagePlaceholder(emoji: String, size: Int) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(PlaceholderGray)
            .border(1.dp, MutedText),
        contentAlignment = Alignment.Center
    ) {
        Text(text = emoji, fontSize = (size / 2.5).sp)
    }
}

// showing Available / Reserved
@Composable
fun StatusPill(text: String, reserved: Boolean) {
    Box(
        modifier = Modifier
            .background(
                if (reserved) AccentGray else AppBackground,
                RoundedCornerShape(50)
            )
            .border(1.dp, BorderDark, RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Text(text, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = DarkText)
    }
}