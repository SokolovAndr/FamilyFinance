package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListRecordsBinding
import com.example.familyfinance.models.Record

class RecordRCViewAdapter : RecyclerView.Adapter<RecordRCViewAdapter.RecordHolder>() {

    val recordList = ArrayList<Record>()

    class RecordHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListRecordsBinding.bind(item)
        fun bind(record: Record) = with(binding) {
            TextViewRecord1.text = "id: " + record.id.toString()
            TextViewRecord2.text = "categoryId: " + record.categoryId.toString()
            TextViewRecord3.text = "accountId: " + record.accountId.toString()
            TextViewRecord4.text = "Сумма: " + record.sum.toString()
            TextViewRecord5.text = "Дата: " + record.date.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordRCViewAdapter.RecordHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_records, parent, false)
        return RecordRCViewAdapter.RecordHolder(view)
    }

    override fun onBindViewHolder(holder: RecordRCViewAdapter.RecordHolder, position: Int) {
        holder.bind(recordList[position])
    }

    override fun getItemCount(): Int {
        return recordList.size
    }

    fun addRecord(record: Record) {
        recordList.add(record)
        notifyDataSetChanged()
    }

}