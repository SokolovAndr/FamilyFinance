package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListRecordsBinding
import com.example.familyfinance.models.Recordnew

class RecordRCViewAdapter : RecyclerView.Adapter<RecordRCViewAdapter.RecordnewHolder>() {

    val recordnewList = ArrayList<Recordnew>()

    class RecordnewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListRecordsBinding.bind(item)
        fun bind(recordnew: Recordnew) = with(binding) {
            TextViewRecord1.text = "№ записи: " + recordnew.id.toString()
            TextViewRecord2.text = "Категория: " + recordnew.cat.toString()
            TextViewRecord3.text = "Счет: " + recordnew.acc.toString()
            TextViewRecord4.text = "Сумма: " + recordnew.sum.toString()
            TextViewRecord5.text = "Дата: " + recordnew.date2.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecordRCViewAdapter.RecordnewHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_records, parent, false)
        return RecordRCViewAdapter.RecordnewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordRCViewAdapter.RecordnewHolder, position: Int) {
        holder.bind(recordnewList[position])
    }

    override fun getItemCount(): Int {
        return recordnewList.size
    }

    fun addRecordnew(recordnew: Recordnew) {
        recordnewList.add(recordnew)
        notifyDataSetChanged()
    }
}