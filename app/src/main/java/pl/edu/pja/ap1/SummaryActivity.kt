package pl.edu.pja.ap1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pl.edu.pja.ap1.databinding.ActivitySummaryBinding
import pl.edu.pja.ap1.views.DataPoint

class SummaryActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySummaryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.graphView.setData(createDataPoints())
    }
    private fun createDataPoints(): List<DataPoint> {
        var costs = 0.0
        return (1..Shared.currentMonthOperationMap.size).map {
            costs += Shared.currentMonthOperationMap[it]!!
            DataPoint(it-1, costs.toInt())
        }
    }
}