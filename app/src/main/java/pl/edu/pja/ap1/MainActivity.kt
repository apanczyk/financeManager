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
import java.text.FieldPosition
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

    private fun setupOperationList() {
        binding.operationlist.apply {
            adapter = operationAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        operationAdapter.operations = Shared.operationlist
    }

    fun clicked(view: View) {
        println("twoja stara")
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
            } else {
//                binding.button.visibility = View.GONE
            }
        }
        else super.onActivityResult(requestCode, resultCode, data)
    }

    private fun seedData() {
        Shared.operationlist.add(Income("ContextCompat.getDrawable(applicationContext, R.drawable.pizza)","TESTOWE ELO ELO",
            105.0.toString(), Date(), Category.Health))
//        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.pierogi),"Wierzejki",
//            105.0.toString(), Date(), Category.Food))
//        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.pumpkin),"Prąd",
//            105.0.toString(), Date(), Category.Bills))
//        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rice),"Salto",
//            105.0.toString(), Date(), Category.Entertainment))
//        Shared.operationlist.add(Income(ContextCompat.getDrawable(applicationContext, R.drawable.rosol),"Pasta",
//            105.0.toString(), Date(), Category.Food))
    }
}