package pl.edu.pja.ap1.model

import android.R.attr.name
import android.graphics.drawable.Drawable
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import java.io.Serializable
import java.util.*


abstract class Operation : BaseObservable(), Serializable  {
    abstract var drawable: String?
    abstract var place: String
    abstract var cost: String
    abstract var date: Date
    abstract var category: Category
}