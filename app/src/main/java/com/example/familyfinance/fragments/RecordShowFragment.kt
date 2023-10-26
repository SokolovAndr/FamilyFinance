package com.example.familyfinance.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R


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

}