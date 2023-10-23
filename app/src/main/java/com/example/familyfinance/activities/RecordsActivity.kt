package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyfinance.CategoryAdapter
import com.example.familyfinance.R
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.databinding.ActivityRecordsBinding
import com.example.familyfinance.models.Category

class RecordsActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecordsBinding
    lateinit var adapter: CategoryAdapter  //перемернная для записи адаптера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)

        adapter = CategoryAdapter()
        binding.rcViewTest.layoutManager = LinearLayoutManager(this@RecordsActivity)
        binding.rcViewTest.adapter = adapter

        db.getDao().getAllCategories().asLiveData().observe(this@RecordsActivity) { list ->
            list.forEach {
                val test = Category(it.id, it.name, it.direction)
                adapter.addCategory(test)
            }
        }
    }
}