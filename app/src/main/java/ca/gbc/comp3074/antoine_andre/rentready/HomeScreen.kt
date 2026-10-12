package ca.gbc.comp3074.antoine_andre.rentready

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 1. Home / Equipment Catalogue
@Composable
fun HomeScreen(onEquipmentClick: () -> Unit, onProfileClick: () -> Unit) {
    var searchText by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("Cameras") }

    // filter the list from what the user types in the search
    val visibleItems = sampleEquipment.filter {
        it.name.contains(searchText, ignoreCase = true)
    }

    val categories = listOf(
        "Cameras" to R.string.category_cameras,
        "Audio" to R.string.category_audio,
        "Computers" to R.string.category_computers
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        // titles + profile icon button
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    stringResource(R.string.header_equipment_rental),
                    fontSize = 13.sp,
                    color = MutedText,
                    letterSpacing = 1.sp
                )
                Text(
                    stringResource(R.string.app_title),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = DarkText
                )
            }
            IconButton(
                onClick = onProfileClick,
                modifier = Modifier
                    .size(48.dp)
                    .background(AccentGray, CircleShape)
                    .border(1.5.dp, BorderDark, CircleShape)
            ) {
                Icon(Icons.Default.AccountCircle, contentDescription = "Profile", tint = DarkText)
            }
        }

        Spacer(Modifier.height(20.dp))

        // Search field
        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(stringResource(R.string.search_hint)) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            singleLine = true,
            shape = RoundedCornerShape(6.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = BorderDark,
                unfocusedBorderColor = BorderDark,
                focusedContainerColor = CardWhite,
                unfocusedContainerColor = CardWhite
            )
        )

        Spacer(Modifier.height(16.dp))

        // Category filter buttons
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categories.forEach { (key, labelRes) ->
                if (selectedCategory == key) {
                    Button(
                        onClick = { selectedCategory = key },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = DarkText,
                            contentColor = Color.White
                        )
                    ) { Text(stringResource(labelRes)) }
                } else {
                    OutlinedButton(
                        onClick = { selectedCategory = key },
                        border = BorderStroke(1.dp, BorderDark),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = CardWhite,
                            contentColor = DarkText
                        )
                    ) { Text(stringResource(labelRes)) }
                }
            }
        }

        Spacer(Modifier.height(20.dp))

        // Section title and item count
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.equipment_section),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText,
                modifier = Modifier.weight(1f)
            )
            Text("${visibleItems.size} items", color = MutedText, fontSize = 13.sp)
        }

        Spacer(Modifier.height(12.dp))

        //  list of equipment cards
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(visibleItems) { item ->
                EquipmentCard(item, onClick = onEquipmentClick)
            }
        }
    }
}

@Composable
fun EquipmentCard(item: Equipment, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        border = BorderStroke(1.5.dp, BorderDark)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagePlaceholder(item.emoji, 72)
            Spacer(Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkText)
                Text(
                    stringResource(R.string.equipment_label),
                    fontSize = 13.sp,
                    color = MutedText
                )
            }
            StatusPill(item.status, reserved = item.status == "Reserved")
        }
    }
}