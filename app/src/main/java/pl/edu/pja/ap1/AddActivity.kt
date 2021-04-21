package pl.edu.pja.ap1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import pl.edu.pja.ap1.adapter.ImageAdapter
import pl.edu.pja.ap1.databinding.ActivityAddBinding
import pl.edu.pja.ap1.model.Category
import pl.edu.pja.ap1.model.Income
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
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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

    fun onSave(view: View) {
        val name = binding.name.text.toString()
        val ingredients = binding.ingredients.text.toString().split("\n")
        val drawable = imageAdapter.selectedItem?.let {
            drawables[it]
        }

        if (drawable == null) {
            Toast.makeText(this, "Nie wybrałeś miniaturki", Toast.LENGTH_LONG).show()
            return
        }
        val operation = Income(drawable, name, 10.0, Date(), Category.Bills)
        Shared.operationlist.add(operation)

        val intent = Intent().apply {
            putExtra("name", name)
        }
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
