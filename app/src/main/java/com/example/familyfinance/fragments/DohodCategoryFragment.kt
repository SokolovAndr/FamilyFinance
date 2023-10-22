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
import com.google.android.material.chip.Chip

class DohodCategoryFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_dohod_category) {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View? = super.onCreateView(inflater, container, savedInstanceState)

        if (view != null)
            CreateInstanseFragment(view)

        return view;
    }

    fun CreateInstanseFragment(view: View) {
        //registration click move link
        view.findViewById<Chip>(R.id.chipRashod).setOnClickListener(this)
        view.findViewById<Chip>(R.id.chipDohod).setOnClickListener(this)
        view.findViewById<Button>(R.id.buttonAddCategory).setOnClickListener(this)
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
            when(p0.id) {
                R.id.chipDohod -> {

                }
                R.id.chipRashod -> {
                    mListener?.onLinkFragment("Rashod")
                }
                R.id.buttonAddCategory -> {
                    mListener?.onLinkFragment("Add")
                }
                else -> TODO("Not implementation click")
            }
        }else
        {
            TODO("View element get null")
        }
    }

}