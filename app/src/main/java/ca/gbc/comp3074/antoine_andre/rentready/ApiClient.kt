package ca.gbc.comp3074.antoine_andre.rentready

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 10.0.2.2 points to the computer's localhost from the Android emulator.
object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:5050/api/"

    val equipmentApi: EquipmentApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EquipmentApi::class.java)
    }
}
