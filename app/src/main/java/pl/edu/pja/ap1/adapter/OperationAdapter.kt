package pl.edu.pja.ap1.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.edu.pja.ap1.databinding.ItemOperationBinding
import pl.edu.pja.ap1.model.Operation

class OperationItem(val binding: ItemOperationBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(operation: Operation) {
        binding.apply {
            name.text = operation.place
            ingredients.text = operation.category.toString()
            photo.setImageDrawable(operation.drawable)
        }
    }
}

class OperationAdapter() : RecyclerView.Adapter<OperationItem>() {
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
        return OperationItem(binding)
    }

    override fun getItemCount(): Int = operations.size

    override fun onBindViewHolder(holder: OperationItem, position: Int) {
        holder.bind(operations[position])
    }

}