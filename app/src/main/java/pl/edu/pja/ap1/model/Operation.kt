package pl.edu.pja.ap1.model

import android.R.attr.name
import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Serializable
import java.time.LocalDate
import java.util.*


abstract class Operation   {
    abstract var drawable: Drawable?
    abstract var place: String
    abstract var cost: Double
    abstract var date: Calendar
    abstract var category: Category
}