package com.example.countriesapp.model



import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize//  es como un "atajo mágico" que hace que tus objetos puedan viajar entre actividades sin que tengas que escribir mucho código extra.
data class Country(
    val name: Name,
    val capital: List<String>?, // Algunas entradas no tienen capital
    val region: String,
    val population: Int,
    @SerializedName("flags")
    val flag: Flag // Usamos un objeto para las URLs de las banderas
) : Parcelable

@Parcelize
data class Name(
    val common: String // Nombre común del país
) : Parcelable

@Parcelize
data class Flag(
    @SerializedName("png")// esto es solamente para  decirle al modelo de la api
    // que tiene el campo pngUrl en lugar de png, entonces se usa @SerializedName para decirle a Gson: "El campo png del JSON debe guardarse en el campo pngUrl del modelo".
    val pngUrl: String // URL de la bandera en formato PNG
) : Parcelable


