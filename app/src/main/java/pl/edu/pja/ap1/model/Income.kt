package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import java.util.*

class Income(override var drawable: String?,
             override var place: String,
             override var cost: String,
             override var date: Date,
             override var category: Category) : Operation() {
}
