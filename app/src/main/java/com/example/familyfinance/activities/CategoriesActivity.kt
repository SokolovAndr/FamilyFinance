package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.chip1.setOnClickListener{
            Toast.makeText(applicationContext, "Рендер расходов", Toast.LENGTH_SHORT).show()
        }
        binding.chip2.setOnClickListener{
            Toast.makeText(applicationContext, "Рендер доходов", Toast.LENGTH_SHORT).show()
        }
    }
}