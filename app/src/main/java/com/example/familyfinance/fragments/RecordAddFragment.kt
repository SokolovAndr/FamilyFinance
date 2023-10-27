package com.example.familyfinance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.familyfinance.R
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Account
import com.example.familyfinance.models.Category

class RecordAddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db = MainDb.getDb(activity?.applicationContext!!)
        val t = inflater.inflate(R.layout.fragment_record_add, container, false)
        val spinner1 = t.findViewById<Spinner>(R.id.spinner1)
        val spinner2 = t.findViewById<Spinner>(R.id.spinner2)

        db.getDao().getAllCategoriesNames().asLiveData().observe(requireActivity()) { array ->
            array.forEach {
                //val text = it.toString()
                spinner1?.adapter = ArrayAdapter(
                    activity?.applicationContext!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    array
                ) as SpinnerAdapter
            }
        }

        db.getDao().getAllAccountsNames().asLiveData().observe(requireActivity()) { array ->
            array.forEach {
                //val text = it.toString()
                spinner2?.adapter = ArrayAdapter(
                    activity?.applicationContext!!,
                    R.layout.support_simple_spinner_dropdown_item,
                    array
                ) as SpinnerAdapter
            }
        }

        spinner1?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, type, Toast.LENGTH_LONG).show()
                println(type)
            }
        }

        spinner2?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                println("error")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val type = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, type, Toast.LENGTH_LONG).show()
                println(type)
            }
        }
        return t
    }
}