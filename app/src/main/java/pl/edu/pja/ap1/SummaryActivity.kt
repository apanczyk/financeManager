package pl.edu.pja.ap1

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pl.edu.pja.ap1.databinding.ActivityAddBinding
import pl.edu.pja.ap1.databinding.ActivitySummaryBinding
import pl.edu.pja.ap1.views.DataPoint
import pl.edu.pja.ap1.views.GraphView
import java.util.*

class SummaryActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySummaryBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.graphView.setData(generateRandomDataPoints())
    }
    private fun generateRandomDataPoints(): List<DataPoint> {
        val random = Random()
        var tmp = -100
        return (0..20).map {
            tmp += 20
            DataPoint(it, tmp)
        }
    }
}