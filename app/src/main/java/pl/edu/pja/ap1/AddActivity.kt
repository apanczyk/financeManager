package pl.edu.pja.ap1

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pl.edu.pja.ap1.databinding.ActivityAddBinding
import pl.edu.pja.ap1.model.Category
import pl.edu.pja.ap1.model.Income
import pl.edu.pja.ap1.model.OperationDao
import java.text.SimpleDateFormat
import java.util.*

class AddActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        var myObject = intent.getSerializableExtra("operationId")
        val op = if(myObject != null) Shared.operationlist[myObject.toString().toInt()]
            else Income("Smyk", 105.0, Calendar.getInstance(), Category.Health)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.operation = OperationDao(op.place, op.cost.toString(), SimpleDateFormat("dd/MM/yyyy").format(op.date.time).toString(), op.category.toString())
        setResult(Activity.RESULT_CANCELED)
    }

    fun showTimePickerDialog(v: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, { _, year, _, dayOfMonth ->
            binding.editTextDate.setText("${dayOfMonth}/${month}/${year}")
        }, year, month, day)
        dpd.show()
    }

    fun onSave(view: View) {
        val myObject = intent.getSerializableExtra("operationId")
        if(myObject != null) {
            val operations = Shared.operationlist[myObject.toString().toInt()]
            operations.cost = binding.costEdit.text.toString().toDouble()
            operations.place = binding.name.text.toString()
        } else {
            val name = binding.name.text.toString()
            val cost = binding.costEdit.text.toString().toDouble()
            val date = binding.editTextDate.toString()

            val operation = Income(name,binding.costEdit.text.toString().toDouble(), Calendar.getInstance(), Category.Bills)
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
