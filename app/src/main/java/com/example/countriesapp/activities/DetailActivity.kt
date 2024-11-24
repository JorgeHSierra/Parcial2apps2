
// DetailActivity.kt
package com.example.countriesapp.activities


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.countriesapp.databinding.ActivityDetailBinding
import com.example.countriesapp.model.Country

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el país seleccionado del Intent
        val country = intent.getParcelableExtra<Country>("country")


        // Mostrar los datos
        country?.let {
            binding.tvCountryName.text = it.name.common
            binding.tvPopulation.text = "Población: ${it.population}"
            binding.tvRegion.text = "Región: ${it.region}"
            binding.tvCapital.text = "Capital: ${it.capital?.joinToString(", ") ?: "Sin capital"}"

            Glide.with(this)
                .load(it.flag.pngUrl) // Imagen grande de la bandera
                .into(binding.ivFlagLarge)
        }
    }
}
