package pl.edu.pja.ap1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.ap1.adapter.OperationAdapter
import pl.edu.pja.ap1.databinding.ActivityMainBinding
import pl.edu.pja.ap1.model.*
import java.util.*


const val REQ = 1

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val operationAdapter by lazy { OperationAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupOperationList()
        if(savedInstanceState == null){
            seedData()
        }
        refreshSummary()
    }
    override fun onResume() {
        super.onResume()
        operationAdapter.operations = Shared.operationlist
    }

    private fun setupOperationList() {
        binding.operationlist.apply {
            adapter = operationAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ) {
            if (resultCode == Activity.RESULT_OK) {
                operationAdapter.operations = Shared.operationlist
                refreshSummary()
            }
        }
        else super.onActivityResult(requestCode, resultCode, data)
    }

    private fun refreshSummary() {
        var costs = 0.0
        Shared.operationlist.forEach { operation ->
            if(operation is Income) {
                costs += operation.cost
            } else costs -= operation.cost
        }
        binding.summary.text = costs.toInt().toString()
    }

    private fun getMonthPaymentsMap() {
        val calendar =  Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1,0,0)
        var dateFrom = calendar.time
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, 1,0,0)
        var dateTo = calendar.time

        var xd = Shared.operationlist.filter { it.date.time.after(dateFrom)  }.filter { it.date.time.before( dateTo ) }
        val maxDay =  xd.maxBy { it.date }!!.date.get(Calendar.DAY_OF_MONTH)
        (1..maxDay).forEach() { day ->
            var cost = 0.0
            xd.filter { it.date.get(Calendar.DAY_OF_MONTH)  == day }.forEach{ operation -> cost+=operation.cost}
            Shared.currentMonthOperationMap[day] = cost
        }
    }

    fun clicked(view: View) {
        startActivityForResult(Intent(this, AddActivity::class.java), REQ)
    }

    fun summary(view: View) {
        getMonthPaymentsMap()
        startActivityForResult(Intent(this, SummaryActivity::class.java), REQ)
    }

    private fun seedData() {
        var calendar = Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),5)
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.spaghetti),"Nienawidze poniedzialkow",
                20.0, calendar.clone() as Calendar, Category.Health))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),13)
        Shared.operationlist.add(Outcome(ContextCompat.getDrawable(applicationContext, R.drawable.pierogi),"Wierzejki",
            11.0, calendar.clone() as Calendar, Category.Food))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1)
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.pumpkin),"Prąd",
            55.0, calendar.clone() as Calendar, Category.Bills))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),12)
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rice),"Salto",
            30.0, calendar.clone() as Calendar, Category.Entertainment))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),3)
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rosol),"Pasta",
            22.0, calendar.clone() as Calendar, Category.Food))
    }
}