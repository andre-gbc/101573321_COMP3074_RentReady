package ca.gbc.comp3074.antoine_andre.rentready

import retrofit2.http.GET

// Response wrapper returned by GET /api/equipment
data class EquipmentListResponse(
    val count: Int,
    val equipment: List<Equipment>
)

interface EquipmentApi {
    @GET("equipment")
    suspend fun getEquipment(): EquipmentListResponse
}
