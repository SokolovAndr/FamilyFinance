package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListRecordsBinding
import com.example.familyfinance.models.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class RecordRCViewAdapter : RecyclerView.Adapter<RecordRCViewAdapter.TestHolder>() {

    val testList = ArrayList<Test>()

    class TestHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListRecordsBinding.bind(item)
        fun bind(test: Test) = with(binding) {
            TextViewRecord1.text = "№ записи: " + test.id.toString()
            TextViewRecord2.text = "Категория: " + test.cat.toString()
            TextViewRecord3.text = "Счет: " + test.acc.toString()
            TextViewRecord4.text = "Сумма: " + test.sum.toString()
            TextViewRecord5.text = "Дата: " + test.date2.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordRCViewAdapter.TestHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_records, parent, false)
        return RecordRCViewAdapter.TestHolder(view)
    }

    override fun onBindViewHolder(holder: RecordRCViewAdapter.TestHolder, position: Int) {
        holder.bind(testList[position])
    }

    override fun getItemCount(): Int {
        return testList.size
    }

    fun addTest(test: Test) {
        testList.add(test)
        notifyDataSetChanged()
    }

}