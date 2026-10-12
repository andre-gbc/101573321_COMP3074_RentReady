package ca.gbc.comp3074.antoine_andre.rentready

import androidx.compose.ui.graphics.Color

val AppBackground = Color(0xFFF1F1F1)
val CardWhite = Color(0xFFFFFFFF)
val DarkText = Color(0xFF111111)
val MutedText = Color(0xFF55555F)
val BorderDark = Color(0xFF3A3A44)
val PlaceholderGray = Color(0xFFDDDDE2)
val AccentGray = Color(0xFFCFCFD6)

data class Equipment(val name: String, val status: String, val emoji: String)
data class Reservation(val name: String, val dates: String, val emoji: String)

val sampleEquipment = listOf(
    Equipment("DSLR Camera", "Available", "📷"),
    Equipment("Tripod", "Available", "📐"),
    Equipment("Microphone", "Reserved", "🎙️")
)

enum class Screen { HOME, DETAILS, RESERVATIONS, RENTALS, PROFILE }