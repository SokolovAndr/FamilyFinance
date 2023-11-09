package com.example.familyfinance.adapters;

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.familyfinance.R
import com.example.familyfinance.models.Category

class CustomCategorySpinnerAdapter(var context: Context, var categories: List<Category>) :
    BaseAdapter() {


    internal var inflter: LayoutInflater

    init {
        inflter = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.support_simple_spinner_dropdown_item, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.textView.text = categories[position].toString()

        return view
    }

    private class ViewHolder(view: View) {
        val textView: TextView = view.findViewById(R.id.textView)
    }
}