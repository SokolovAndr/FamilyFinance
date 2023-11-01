package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.fragments.RecordAddFragment

class RecordsActivity : AppCompatActivity(), OnLinkFragment {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_records)

    }

    override fun onLinkFragment(link: String?) {
        when (link) {

            "Record" -> supportFragmentManager.commit {
                replace<RecordAddFragment>(R.id.Record_view_fragment)
                setReorderingAllowed(true)
            }


            else -> {
                TODO("Not implementation click")
            }
        }
    }

}