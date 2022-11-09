package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import pl.edu.pja.ap1.model.Category
import pl.edu.pja.ap1.model.Operation
import java.time.LocalDate
import java.util.*

data class Outcome(
        override var place: String,
        override var cost: Double,
        override var date: Calendar,
        override var category: Category
) : Operation()