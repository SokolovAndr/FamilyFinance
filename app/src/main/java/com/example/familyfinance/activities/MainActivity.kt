package com.example.familyfinance.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyfinance.R
import com.example.familyfinance.adapters.OstatokRCViewAdapter
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.databinding.ActivityMainBinding
import com.example.familyfinance.models.Ostatok

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: OstatokRCViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val myIntentRecordsActivity = Intent(this, RecordsActivity::class.java).apply{}
        val myIntentCategoriesActivity = Intent(this, CategoriesActivity::class.java).apply{}
        val myIntentAccountsActivity = Intent(this, AccountsActivity::class.java).apply {  }

        setContentView(binding.root)
        binding.apply {
            nvMenu.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.item1 -> {
                        startActivity(myIntentRecordsActivity)
                    }
                    R.id.item2 -> {
                        startActivity(myIntentCategoriesActivity)
                    }
                    R.id.item3 -> {
                        startActivity(myIntentAccountsActivity)
                    }
                    R.id.item4 -> {
                        Toast.makeText(applicationContext, "Отчеты", Toast.LENGTH_SHORT).show()
                    }
                    R.id.item5 -> {
                        Toast.makeText(applicationContext, "О нас", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            openButton.setOnClickListener {
                drawer.openDrawer(GravityCompat.START)
            }
        }

        val db = MainDb.getDb(this)
        adapter = OstatokRCViewAdapter()
        binding.rcViewOstatok.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rcViewOstatok.adapter = adapter

        db.getDao().getOstatki().asLiveData().observe(this@MainActivity) {list ->
            list.forEach{
                val ostatok = Ostatok(it.accName, it.totalSum)
                adapter.addOstatok(ostatok)
            }
        }

    }
}