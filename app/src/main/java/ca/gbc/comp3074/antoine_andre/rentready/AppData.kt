package ca.gbc.comp3074.antoine_andre.rentready

import androidx.compose.ui.graphics.Color
import com.google.gson.annotations.SerializedName

val AppBackground = Color(0xFFF1F1F1)
val CardWhite = Color(0xFFFFFFFF)
val DarkText = Color(0xFF111111)
val MutedText = Color(0xFF55555F)
val BorderDark = Color(0xFF3A3A44)
val PlaceholderGray = Color(0xFFDDDDE2)
val AccentGray = Color(0xFFCFCFD6)

data class Equipment(
    @SerializedName("_id") val id: String,
    val name: String,
    val description: String,
    val category: String,
    val image: String,
    val condition: String,
    val status: String
) {
    // The backend does not send an emoji, so use a simple category-based one.
    val emoji: String
        get() = when (category) {
            "Cameras" -> "📷"
            "Audio" -> "🎙️"
            "Computing" -> "💻"
            "Presentation" -> "📽️"
            "Lab Equipment" -> "🔬"
            else -> "📦"
        }
}

data class Reservation(val name: String, val dates: String, val emoji: String)

enum class Screen { HOME, DETAILS, RESERVATIONS, RENTALS, PROFILE }
