package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import pl.edu.pja.ap1.model.Category
import java.io.Serializable
import java.util.*

abstract class Operation : BaseObservable(), Serializable  {
    abstract var drawable: Drawable?
    abstract var place: String
    abstract var cost: Double
    abstract var date: Date
    abstract var category: Category
}