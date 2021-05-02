package pl.edu.pja.ap1
import pl.edu.pja.ap1.model.Operation
import java.time.LocalDate
import java.util.*

object Shared {
    val operationlist = mutableListOf<Operation>()
    val currentMonthOperationMap = mutableMapOf<Int,Double>()
}