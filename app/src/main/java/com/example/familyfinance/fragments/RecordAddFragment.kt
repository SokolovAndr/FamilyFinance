package com.example.familyfinance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CursorAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.asLiveData
import com.example.familyfinance.R
import com.example.familyfinance.adapters.CustomSpinnerAdapter
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Account
import com.example.familyfinance.models.Category
import com.example.familyfinance.models.Record
import java.time.LocalDateTime

class RecordAddFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val db = MainDb.getDb(activity?.applicationContext!!)
        val t = inflater.inflate(R.layout.fragment_record_add, container, false)
        val spinner1 = t.findViewById<Spinner>(R.id.spinner1)
        val spinner2 = t.findViewById<Spinner>(R.id.spinner2)
        val but = t.findViewById<Button>(R.id.buttonSaveRecord)
        val etSum = t.findViewById<TextView>(R.id.etSum)
        val sum = etSum.text.toString()  //его преобразуем в long
        val dateTime = LocalDateTime.now()

        //val catId = db.getDao().
        //val accId = db.getDao().

        db.getDao().getAllCategories().asLiveData().observe(requireActivity()) { array ->
            spinner1?.adapter = CustomSpinnerAdapter(
                activity?.applicationContext!!,
                array
            ) as SpinnerAdapter
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
                val cat = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, cat, Toast.LENGTH_LONG).show()
                println(cat)
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
                val acc = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, acc, Toast.LENGTH_LONG).show()
                println(acc)
            }
        }
        but.setOnClickListener() {

            /*val record = Record (null, catId, accId, sum, dateTime.toString())
            Thread {
                db.getDao().insertRecord(record)
            }.start()*/

            Toast.makeText(
                activity?.applicationContext,
                "Запись успешно добавлена",
                Toast.LENGTH_SHORT
            ).show()

        }
        return t
    }
}