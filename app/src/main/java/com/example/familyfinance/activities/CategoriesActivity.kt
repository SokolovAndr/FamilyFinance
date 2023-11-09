package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ActivityCategoriesBinding
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.fragments.CategoryAddFragment
import com.example.familyfinance.fragments.DohodCategoryFragment
import com.example.familyfinance.fragments.RashodCategoryFragment

class CategoriesActivity : AppCompatActivity(), OnLinkFragment {

    lateinit var binding: ActivityCategoriesBinding

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

            "Back2" -> supportFragmentManager.commit {
                replace<RashodCategoryFragment>(R.id.Category_view_fragment)
                setReorderingAllowed(true)
            }

            else -> {
                TODO("Not implementation click")
            }
        }
    }
}