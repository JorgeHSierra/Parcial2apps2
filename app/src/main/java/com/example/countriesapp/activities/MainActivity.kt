package com.example.countriesapp.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesapp.adapter.CountryAdapter
import com.example.countriesapp.databinding.ActivityMainBinding
import com.example.countriesapp.model.Country
import com.example.countriesapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val countryList = mutableListOf<Country>()
    private lateinit var adapter: CountryAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("USER_PREF", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("LOGGED_IN", false)
        //esto se agrega porque si no la app directamente una vez que te registraste te lleva siempre a la lista de banderas

        // Log para verificar el estado de sesión
        println("Estado de sesión actual: $isLoggedIn")

        if (!isLoggedIn) {
            // Si no está logueado, redirigir al LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Configuración de la actividad
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CountryAdapter(countryList) { country ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("country", country)
            startActivity(intent)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        fetchCountries()
    }

    private fun fetchCountries() {
        val apiService = ApiService.create()
        apiService.getCountries().enqueue(object : Callback<List<Country>> {
            override fun onResponse(call: Call<List<Country>>, response: Response<List<Country>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        countryList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Country>>, t: Throwable) {
                println("Error al conectar con la API: ${t.message}")
            }
        })
    }
}



