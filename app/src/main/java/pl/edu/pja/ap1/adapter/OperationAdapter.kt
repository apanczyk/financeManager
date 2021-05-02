package pl.edu.pja.ap1.adapter

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.ap1.AddActivity
import pl.edu.pja.ap1.REQ
import pl.edu.pja.ap1.Shared
import pl.edu.pja.ap1.databinding.ItemOperationBinding
import pl.edu.pja.ap1.model.Income
import pl.edu.pja.ap1.model.Operation
import java.text.SimpleDateFormat

class OperationItem(val binding: ItemOperationBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(operation: Operation) {
        binding.apply {
            name.text = operation.place
            cost.text = operation.cost.toString()
            date.text = SimpleDateFormat("dd/MM/yyyy").format(operation.date.time)
            category.text = operation.category.toString()
            if(operation is Income) cost.setTextColor(Color.GREEN)
            else cost.setTextColor(Color.RED)
            photo.setImageDrawable(operation.drawable)
        }
    }
}

class OperationAdapter(private val context: AppCompatActivity) : RecyclerView.Adapter<OperationItem>() {
    var selectedItem: Int? = null

    var operations: List<Operation> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationItem {
        val binding = ItemOperationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        )
        return OperationItem(binding).also { holder ->
            binding.root.setOnClickListener {
                changeSelection(holder.layoutPosition)
                val intent = Intent(context, AddActivity::class.java)
                intent.putExtra("operationId", holder.layoutPosition)
                context.startActivityForResult(intent,REQ)
            }}.also {holder ->

                binding.root.setOnLongClickListener {
                    val place = holder.layoutPosition
                    context.setResult(Activity.RESULT_CANCELED)
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(context)
                    alertDialog.setTitle("Czy usunąć tą operacje?")
                    alertDialog.setPositiveButton("Usuń") { dialog, id ->
                        Shared.operationlist.removeAt(place)
                        context.recreate()
                    }
                    alertDialog.setNegativeButton("Nie") { dialog, id ->
                    }
                    val alert = alertDialog.create()
                    alert.setCanceledOnTouchOutside(false)
                    alert.show()
                    return@setOnLongClickListener true;
                }
            }
        }

    override fun getItemCount(): Int = operations.size

    override fun onBindViewHolder(holder: OperationItem, position: Int) {
        holder.bind(operations[position])
    }

    fun changeSelection(position: Int) {
        val oldOne = selectedItem
        selectedItem = position
        oldOne?.let {
            notifyItemChanged(it)
        }
        notifyItemChanged(position)
    }
}