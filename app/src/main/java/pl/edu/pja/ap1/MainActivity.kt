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
            }
        }
        else super.onActivityResult(requestCode, resultCode, data)
    }

    fun clicked(view: View) {
        startActivityForResult(Intent(this, AddActivity::class.java), REQ)
    }

    private fun seedData() {
        var calendar = Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),5)
        Shared.operationlist.add(Income("Nienawidze poniedzialkow",
                20.0, calendar.clone() as Calendar, Category.Health))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),13)
        Shared.operationlist.add(Outcome("Wierzejki",
            11.0, calendar.clone() as Calendar, Category.Food))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1)
        Shared.operationlist.add(Income("Prąd",
            55.0, calendar.clone() as Calendar, Category.Bills))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),12)
        Shared.operationlist.add(Income("Salto",
            30.0, calendar.clone() as Calendar, Category.Entertainment))
        calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),3)
        Shared.operationlist.add(Income("Pasta",
            22.0, calendar.clone() as Calendar, Category.Food))
    }
}