package com.example.familyfinance.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.adapters.CategoryAdapter
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.databinding.ActivityRecordsBinding
import com.example.familyfinance.fragments.AccountAddFragment
import com.example.familyfinance.fragments.RecordAddFragment
import com.example.familyfinance.models.Category

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