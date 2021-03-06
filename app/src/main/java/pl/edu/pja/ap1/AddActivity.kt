package pl.edu.pja.ap1

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.ap1.adapter.ImageAdapter
import pl.edu.pja.ap1.databinding.ActivityAddBinding
import pl.edu.pja.ap1.model.Category
import pl.edu.pja.ap1.model.Income
import pl.edu.pja.ap1.model.OperationDao
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val drawables by lazy {
        listOf<Int>(
            R.drawable.pierogi, R.drawable.pizza,
            R.drawable.rice, R.drawable.spaghetti,
            R.drawable.pumpkin, R.drawable.rosol
        ).map {
            ResourcesCompat.getDrawable(resources, it, theme)
        }.filterNotNull()
    }
    private val imageAdapter by lazy { ImageAdapter(drawables) }

    override fun onCreate(savedInstanceState: Bundle?) {
        var myObject = intent.getSerializableExtra("operationId")
        val op = if(myObject != null) Shared.operationlist.get(myObject.toString().toInt())
            else Income(ContextCompat.getDrawable(applicationContext, R.drawable.pizza),
            "Smyk", 105.0, Calendar.getInstance(), Category.Health)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.operation = OperationDao(op.drawable,op.place, op.cost.toString(), SimpleDateFormat("dd/MM/yyyy").format(op.date.time).toString(), op.category.toString())
        setupImages()
        setResult(Activity.RESULT_CANCELED)
    }

    private fun setupImages() {
        binding.images.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(
                this@AddActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
        }
    }
    fun showTimePickerDialog(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            binding.editTextDate.setText("${dayOfMonth}/${month}/${year}")
        }, year, month, day)
        dpd.show()
    }

    fun onSave(view: View) {
        val myObject = intent.getSerializableExtra("operationId")
        if(myObject != null) {
            val operations = Shared.operationlist.get(myObject.toString().toInt())
            operations.cost = binding.costEdit.text.toString().toDouble()
            operations.place = binding.name.text.toString()
//            twojaStara.date = binding.editTextDate.text
//                    operation.date = Date.(binding.editTextDate.toString(), "dd/MM/yyyy")

//            val operation = Shared.operationlist.find { it == myObject }
        } else {
            val name = binding.name.text.toString()
            val cost = binding.costEdit.text.toString().toDouble()
            val date = binding.editTextDate.toString()

            val drawable = imageAdapter.selectedItem?.let {
                drawables[it]
            }

            if (drawable == null) {
                Toast.makeText(this, "Nie wybra??e?? miniaturki", Toast.LENGTH_LONG).show()
                return
            }
            val operation = Income(drawable, name,binding.costEdit.text.toString().toDouble(), Calendar.getInstance(), Category.Bills)
            Shared.operationlist.add(operation)
        }
        val name = binding.name.text.toString()
        val intent = Intent().apply {
            putExtra("name", name)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}
