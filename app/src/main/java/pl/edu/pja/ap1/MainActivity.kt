package pl.edu.pja.ap1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.forEach
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.ap1.adapter.OperationAdapter
import pl.edu.pja.ap1.databinding.ActivityMainBinding
import pl.edu.pja.ap1.model.*
import java.util.*
import kotlin.math.cos


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


    fun clicked(view: View) {
        startActivityForResult(Intent(this, AddActivity::class.java), REQ)
    }

    fun summary(view: View) {
        startActivityForResult(Intent(this, AddActivity::class.java), REQ)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ) {
            if (resultCode == Activity.RESULT_OK) {
//                data?.getStringExtra("name").let {
//                    binding.button.text = it
//                }
                operationAdapter.operations = Shared.operationlist
                refreshSummary()
            } else {
//                binding.button.visibility = View.GONE
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

    private fun seedData() {
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.spaghetti),"TESTOWE ELO ELO",
                22.0, Calendar.getInstance().time, Category.Health))
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.pierogi),"Wierzejki",
            11.0, Date(), Category.Food))
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.pumpkin),"Prąd",
            55.0, Date(), Category.Bills))
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rice),"Salto",
            105.0, Date(), Category.Entertainment))
        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rosol),"Pasta",
            11.0, Date(), Category.Food))
    }
}