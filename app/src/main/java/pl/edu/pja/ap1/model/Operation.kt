package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import pl.edu.pja.ap1.model.Category
import java.util.*

abstract class Operation {
    abstract var drawable: Drawable?
    abstract var place: String
    abstract var cost: Double
    abstract var date: Date
    abstract var category: Category
}