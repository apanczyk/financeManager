package pl.edu.pja.ap1.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GraphView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private val dataSet = mutableListOf<DataPoint>()
    private var xMin = 0
    private var xMax = 0
    private var yMin = 0
    private var yMax = 0
    private var yPos = 0
    private var yPosRatio = 0f

    private val dataPointLinePaint = Paint().apply {
        color = Color.GREEN
        strokeWidth = 7f
        isAntiAlias = true
    }

    private val axisLinePaint = Paint().apply {
        color = Color.MAGENTA
        strokeWidth = 5f
    }

    fun setData(newDataSet: List<DataPoint>) {
        xMin = newDataSet.minBy { it.xVal }?.xVal ?: 0
        yMin = newDataSet.filter{ it.yVal > 0}.minBy { it.yVal }?.yVal ?: 0
        xMax = newDataSet.maxBy { it.xVal }?.xVal ?: 0
        yMax = newDataSet.maxBy { it.yVal }?.yVal ?: 0

        val minimum = newDataSet.minBy { it.yVal }?.yVal ?: 0
        yPos = if(minimum < 0) yMax - minimum else yMax
        val wtf = yMax - minimum
        yPosRatio = if(minimum < 0) yMax.toFloat() / (yMax - minimum) else 1f
        dataSet.clear()
        dataSet.addAll(newDataSet)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        dataSet.forEachIndexed { index, currentDataPoint ->
            if (index < dataSet.size - 1) {
                val nextDataPoint = dataSet[index + 1]
                val startX = currentDataPoint.xVal.toRealX()
                val startY = currentDataPoint.yVal.convertToNormalValues().toRealY()
                val endX = nextDataPoint.xVal.toRealX()
                val endY = nextDataPoint.yVal.convertToNormalValues().toRealY()

                dataPointLinePaint.color = if(currentDataPoint.yVal < 0) Color.RED else Color.GREEN

                canvas.drawLine(startX, startY, endX, endY, dataPointLinePaint)
            }
        }

        canvas.drawLine(0f, 0f, 0f, height.toFloat(), axisLinePaint)
        canvas.drawLine(0f, yPosRatio*height, width.toFloat(), yPosRatio*height, axisLinePaint)
    }


    private fun Int.toRealX() = toFloat() / xMax * width
    private fun Int.toRealY() = (toFloat() / yPos  * height) * yPosRatio
    private fun Int.convertToNormalValues(): Int = ((yPos - toFloat())).toInt()
}

data class DataPoint(
    var xVal: Int,
    var yVal: Int
)