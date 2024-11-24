package com.example.countriesapp.network




import com.example.countriesapp.model.Country
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("all") // Endpoint para obtener todos los países
    fun getCountries(): Call<List<Country>>

    companion object {
        private const val BASE_URL = "https://restcountries.com/v3.1/"

        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}

