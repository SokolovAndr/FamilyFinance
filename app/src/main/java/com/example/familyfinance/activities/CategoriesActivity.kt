package com.example.familyfinance.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyfinance.CategoryAdapter
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ActivityCategoriesBinding
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.fragments.CategoryAddFragment
import com.example.familyfinance.fragments.DohodCategoryFragment
import com.example.familyfinance.fragments.RashodCategoryFragment
import com.example.familyfinance.models.Category

class CategoriesActivity : AppCompatActivity(), OnLinkFragment {

    lateinit var binding: ActivityCategoriesBinding
    //lateinit var adapter: CategoryAdapter  //перемернная для записи адаптера

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityCategoriesBinding.inflate(layoutInflater)
            setContentView(binding.root)


    }



    override fun onLinkFragment(link: String?) {
        when (link) {

            "Rashod" -> supportFragmentManager.commit {
                replace<RashodCategoryFragment>(R.id.Category_view_fragment)
                setReorderingAllowed(true)
            }

            "Dohod" -> supportFragmentManager.commit {
                replace<DohodCategoryFragment>(R.id.Category_view_fragment)
                setReorderingAllowed(true)
            }

            "Add" -> supportFragmentManager.commit {
                replace<CategoryAddFragment>(R.id.Category_view_fragment)
                setReorderingAllowed(true)
            }

            else -> {
                TODO("Not implementation click")}
        }
    }




}