package com.example.familyfinance.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.familyfinance.R
import com.example.familyfinance.adapters.CustomAccountAdapter
import com.example.familyfinance.adapters.CustomCategoryAdapter
import com.example.familyfinance.database.MainDb
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
        //val sum = etSum.text.toString()  //его преобразуем в long
        val dateTime = LocalDateTime.now()

        db.getDao().getAllCategories().asLiveData().observe(requireActivity()) { array ->
            spinner1?.adapter = CustomCategoryAdapter(
                activity?.applicationContext!!,
                array
            ) as SpinnerAdapter
        }

        db.getDao().getAllAccounts().asLiveData().observe(requireActivity()) { array ->
            array.forEach {
                spinner2?.adapter = CustomAccountAdapter(
                    activity?.applicationContext!!,
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
                /*val cat = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, cat, Toast.LENGTH_LONG).show()
                println(cat)*/
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
                /*val acc = parent?.getItemAtPosition(position).toString()
                Toast.makeText(activity, acc, Toast.LENGTH_LONG).show()
                println(acc)*/
            }
        }

        but.setOnClickListener() {

            try {
                Thread {
                    val value1 = spinner1.selectedItem.toString()
                    val value2 = spinner2.selectedItem.toString()
                    val catId = db.getDao().getCategoryId(value1)
                    val accId = db.getDao().getAccountId(value2)

                    val record = Record(
                        null,
                        catId,
                        accId,
                        etSum.text.toString().toLong(),
                        dateTime.toString()
                    )
                    db.getDao().insertRecord(record)

                }.start()

                Toast.makeText(
                    activity?.applicationContext,
                    "Запись успешно добавлена",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    activity?.applicationContext,
                    "Ошибка записи",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return t
    }
}