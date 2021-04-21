package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import java.util.*

data class Income(
    override var drawable: Drawable?,
    override var place: String,
    override var cost: Double,
    override var date: Date,
    override var category: Category
) : Operation()