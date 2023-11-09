package com.example.familyfinance.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Account


class AccountAddFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_account_add) {
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
        //registration click move link
        view.findViewById<Button?>(R.id.buttonSaveAccount).setOnClickListener(this)
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

        val db = MainDb.getDb(activity?.applicationContext!!)

        if (p0 != null) {
            when (p0.id) {
                R.id.buttonSaveAccount -> {

                    var etNewAcc = view?.findViewById<EditText>(R.id.etNewAccount)

                    if (etNewAcc?.text?.isEmpty() == true) {
                        var toast = Toast.makeText(
                            activity?.applicationContext,
                            "Заполните все поля",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    } else {
                        val account = Account(
                            null,
                            etNewAcc?.text.toString()
                        )
                        Thread {
                            db.getDao().insertAccount(account)
                        }.start()

                        var toast = Toast.makeText(
                            activity?.applicationContext,
                            "Счет успешно добавлен",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    }
                    mListener?.onLinkFragment("Back")
                }

                else -> TODO("Not implementation click")
            }
        } else {
            TODO("View element get null")
        }
    }
}