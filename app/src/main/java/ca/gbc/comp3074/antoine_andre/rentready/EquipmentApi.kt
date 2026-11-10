package ca.gbc.comp3074.antoine_andre.rentready

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET

// Response wrapper returned by GET /api/equipment
data class EquipmentListResponse(
    val count: Int,
    val equipment: List<EquipmentDto>
)

// Matches one equipment document from MongoDB.
data class EquipmentDto(
    @SerializedName("_id") val id: String,
    val name: String,
    val description: String,
    val category: String,
    val image: String,
    val condition: String,
    val status: String
)

interface EquipmentApi {
    @GET("equipment")
    suspend fun getEquipment(): EquipmentListResponse
}
