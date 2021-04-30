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

    private val dataPointLinePaint = Paint().apply {
        color = Color.BLUE
        strokeWidth = 7f
        isAntiAlias = true
    }

    private val axisLinePaint = Paint().apply {
        color = Color.MAGENTA
        strokeWidth = 20f
    }

    fun setData(newDataSet: List<DataPoint>) {

        xMin = newDataSet.minBy { it.xVal > 0 }?.xVal ?: 0
        yMin = newDataSet.minBy { it.yVal > 0 }?.yVal ?: 0
        xMax = newDataSet.maxBy { it.xVal }?.xVal ?: 0
        yMax = newDataSet.maxBy { it.yVal }?.yVal ?: 0
        dataSet.clear()
        dataSet.addAll(newDataSet)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        dataSet.forEachIndexed { index, currentDataPoint ->
            val realX = currentDataPoint.xVal.toRealX()
            val realY = currentDataPoint.yVal.toRealY()

            if (index < dataSet.size - 1) {
                val nextDataPoint = dataSet[index + 1]
                if(nextDataPoint.xVal<0)
                    nextDataPoint.xVal=xMax - nextDataPoint.xVal
                if(nextDataPoint.yVal<0)
                    nextDataPoint.yVal=yMax - nextDataPoint.yVal
                val startX = currentDataPoint.xVal.toRealX()
                val startY = currentDataPoint.yVal.toRealY()
                val endX = nextDataPoint.xVal.toRealX()
                val endY = nextDataPoint.yVal.toRealY()
                canvas.drawLine(startX, startY, endX, endY, dataPointLinePaint)
            }

//            canvas.drawCircle(realX, realY, 7f, dataPointFillPaint)
//            canvas.drawCircle(realX, realY, 7f, dataPointPaint)
        }

        canvas.drawLine(0f, 0f, 0f, height.toFloat(), axisLinePaint)
        canvas.drawLine(0f, height.toFloat(), width.toFloat(), height.toFloat(), axisLinePaint)
    }
    private fun Int.toRealX() = toFloat() / xMax * width
    private fun Int.toRealY() = toFloat() / yMax * height
    private fun Int.maxDodatnie() = toFloat()

}

data class DataPoint(
    var xVal: Int,
    var yVal: Int
)