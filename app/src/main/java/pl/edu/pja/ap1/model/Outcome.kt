package pl.edu.pja.ap1.model

import android.graphics.drawable.Drawable
import pl.edu.pja.ap1.model.Category
import pl.edu.pja.ap1.model.Operation
import java.util.*

data class Outcome(
        override var drawable: String?,
        override var place: String,
        override var cost: String,
        override var date: Date,
        override var category: Category
) : Operation()