package com.example.familyfinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.familyfinance.fragments.RegisterFragment
import com.example.familyfinance.fragments.LoginFragment

class AuthActivity : AppCompatActivity(), OnLinkFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }

    override fun onLinkFragment(link: String?) {
        when (link) {

            "Register" -> supportFragmentManager.commit {
                replace<RegisterFragment>(R.id.Auth_view_fragment)
                setReorderingAllowed(true)
            }

            "Login" -> supportFragmentManager.commit {
                replace<LoginFragment>(R.id.Auth_view_fragment)
                setReorderingAllowed(true)
            }

            else -> {
                TODO("Not implementation click")
            }
        }
    }
}