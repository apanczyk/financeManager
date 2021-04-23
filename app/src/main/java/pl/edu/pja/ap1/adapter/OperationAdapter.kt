package pl.edu.pja.ap1.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.ap1.AddActivity
import pl.edu.pja.ap1.REQ
import pl.edu.pja.ap1.Shared
import pl.edu.pja.ap1.databinding.ItemOperationBinding
import pl.edu.pja.ap1.model.Operation

class OperationItem(val binding: ItemOperationBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(operation: Operation) {
        binding.apply {
            name.text = operation.place
            ingredients.text = operation.category.toString()
//            photo.setImageDrawable(operation.drawable)
        }
    }
}

class OperationAdapter(val context: AppCompatActivity) : RecyclerView.Adapter<OperationItem>() {
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
                intent.putExtra("bodyguard", Shared.operationlist[holder.layoutPosition])
                context.startActivityForResult(intent,REQ)
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