package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.fragments.AccountAddFragment
import com.example.familyfinance.fragments.AccountShowFragment
import com.example.familyfinance.fragments.CategoryAddFragment
import com.example.familyfinance.fragments.DohodCategoryFragment
import com.example.familyfinance.fragments.RashodCategoryFragment

class AccountsActivity : AppCompatActivity(), OnLinkFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accounts)
    }

    override fun onLinkFragment(link: String?) {
        when (link) {

            "Account" -> supportFragmentManager.commit {
                replace<AccountAddFragment>(R.id.Account_view_fragment)
                setReorderingAllowed(true)
            }

            "Back" -> supportFragmentManager.commit {
                replace<AccountShowFragment>(R.id.Account_view_fragment)
                setReorderingAllowed(true)
            }


            else -> {
                TODO("Not implementation click")
            }
        }
    }

}