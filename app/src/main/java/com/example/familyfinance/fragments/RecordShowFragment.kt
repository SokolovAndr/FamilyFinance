package com.example.familyfinance.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.adapters.RecordRCViewAdapter
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private lateinit var adapter: RecordRCViewAdapter  //перемернная для записи адаптера
private lateinit var rcview: RecyclerView  //перемернная для работы с rcview

class RecordShowFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_record_show) {
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = super.onCreateView(inflater, container, savedInstanceState)
        if (view != null)
            CreateInstanseFragment(view)
        return view;
    }

    fun CreateInstanseFragment(view: View) {
        view.findViewById<Button>(R.id.buttonAddRecord).setOnClickListener(this)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is OnLinkFragment) {
            context as OnLinkFragment
        } else {
            throw ClassCastException(
                context.toString()
                        + " must implement MyListFragment.OnLinkFragment"
            )
        }
    }

    override fun onClick(p0: View?) {
        if (p0 != null) {
            when (p0.id) {
                R.id.buttonAddRecord -> {
                    mListener?.onLinkFragment("Record")
                }
                else -> TODO("Not implementation click")
            }
        } else {
            TODO("View element get null")
        }
    }
    val formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rcview = view.findViewById(R.id.rcviewRecords)
        rcview.layoutManager = layoutManager
        rcview.setHasFixedSize(true)
        adapter = RecordRCViewAdapter()
        rcview.adapter = adapter
        val db = MainDb.getDb(activity?.applicationContext!!)

        db.getDao().getMyRecords().asLiveData().observe(requireActivity()) { list ->
            list.forEach {
                val test = Test(it.id, it.cat, it.acc, it.sum, it.date2)
                adapter.addTest(test)
            }
        }
    }
}