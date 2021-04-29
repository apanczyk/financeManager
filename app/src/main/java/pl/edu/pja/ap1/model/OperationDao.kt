package pl.edu.pja.ap1.model

import androidx.databinding.BaseObservable
import java.io.Serializable
import java.util.*


data class OperationDao(
    var place: String,
    var cost: String,
    var date: String,
    var category: String
)