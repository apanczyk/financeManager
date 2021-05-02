package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import androidx.databinding.Bindable
import java.util.*

class Income(override var drawable: Drawable?,
             override var place: String,
             override var cost: Double,
             override var date: Calendar,
             override var category: Category) : Operation() {


}
