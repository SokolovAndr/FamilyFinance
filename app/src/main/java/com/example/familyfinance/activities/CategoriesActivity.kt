package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ActivityCategoriesBinding
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.fragments.DohodCategoryFragment
import com.example.familyfinance.fragments.RashodCategoryFragment

class CategoriesActivity : AppCompatActivity(), OnLinkFragment {

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

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

            else -> {
                TODO("Not implementation click")}
        }
    }
}