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
import com.example.familyfinance.adapters.AccountRCViewAdapter
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Account


private lateinit var adapter: AccountRCViewAdapter  //перемернная для записи адаптера
private lateinit var rcview: RecyclerView  //перемернная для работы с rcview

class AccountShowFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_account_show) {
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
        view.findViewById<Button>(R.id.buttonAddAccount).setOnClickListener(this)

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
                R.id.buttonAddAccount -> {
                    mListener?.onLinkFragment("Account")
                }
                else -> TODO("Not implementation click")
            }
        } else {
            TODO("View element get null")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rcview = view.findViewById(R.id.rcViewAccount)
        rcview.layoutManager = layoutManager
        rcview.setHasFixedSize(true)
        adapter = AccountRCViewAdapter()
        rcview.adapter = adapter
        val db = MainDb.getDb(activity?.applicationContext!!)

        db.getDao().getAllAccounts().asLiveData().observe(requireActivity()) { list ->
            list.forEach {
                val test = Account(it.id, it.name)
                adapter.addAccount(test)
            }
        }
    }

}