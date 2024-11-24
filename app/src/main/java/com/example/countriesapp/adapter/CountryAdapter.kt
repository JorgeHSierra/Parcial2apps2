package com.example.countriesapp.adapter
// CountryAdapter.kt





import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countriesapp.databinding.ItemCountryBinding
import com.example.countriesapp.model.Country

class CountryAdapter(//esta clase es necesaria para poder pasarle los datos al recycle view

    private val countries: List<Country>,
    private val onClick: (Country) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    // ViewHolder: Define cómo se muestra cada elemento del RecyclerView
    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Método para vincular los datos del país al layout
        fun bind(country: Country) {
            binding.tvCountryName.text = country.name.common // Nombre del país
            Glide.with(binding.root.context)
                .load(country.flag.pngUrl) // URL de la bandera pequeña
                .into(binding.ivFlagSmall)

            // Configurar clic en el elemento
            binding.root.setOnClickListener {
                onClick(country)
            }
        }
    }

    // Crear la vista para cada elemento
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    // Vincular los datos del país con el ViewHolder
    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    // Cantidad de elementos en la lista
    override fun getItemCount(): Int = countries.size
}


