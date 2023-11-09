package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListOstatokBinding
import com.example.familyfinance.models.Ostatok

class OstatokRCViewAdapter : RecyclerView.Adapter<OstatokRCViewAdapter.OstatokHolder>() {

    val ostatokList = ArrayList<Ostatok>()

    class OstatokHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = ListOstatokBinding.bind(item)
        fun bind(ostatok: Ostatok) = with(binding) {
            TextViewOstatok1.text = "Счет: " + ostatok.accName.toString()
            TextViewOstatok2.text = "Остаток: " + ostatok.totalSum.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OstatokRCViewAdapter.OstatokHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_ostatok, parent, false)
        return OstatokRCViewAdapter.OstatokHolder(view)
    }

    override fun onBindViewHolder(holder: OstatokRCViewAdapter.OstatokHolder, position: Int) {
        holder.bind(ostatokList[position])
    }

    override fun getItemCount(): Int {
        return ostatokList.size
    }

    fun addOstatok(ostatok: Ostatok) {
        ostatokList.add(ostatok)
        notifyDataSetChanged()
    }

    fun clear() {
        ostatokList.clear()
        notifyDataSetChanged()
    }

}