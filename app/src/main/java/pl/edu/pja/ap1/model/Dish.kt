package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable

data class Dish(
    val drawable: Drawable,
    val name: String,
    val ingredients: List<String>
)
