package com.example.familyfinance.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.familyfinance.activities.MainActivity
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.User

class LoginFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_login) {
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
        view.findViewById<Button?>(R.id.RegisterLink).setOnClickListener(this)
        view.findViewById<Button?>(R.id.LoginLink).setOnClickListener(this)
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
                R.id.RegisterLink -> {
                    mListener?.onLinkFragment("Register")
                }

                R.id.LoginLink -> {

                    var etL = view?.findViewById<EditText>(R.id.etLogin)
                    var etP = view?.findViewById<EditText>(R.id.etPassword)

                    if (etL?.text?.isEmpty() == true || etP?.text?.isEmpty() == true) {
                        var toast = Toast.makeText(
                            activity?.applicationContext,
                            "Заполните все поля",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    } else {

                        var user: LiveData<List<User>>? = db.getDao().isExists(etL?.text.toString(), etP?.text.toString())

                        user?.observe(this) { list ->
                            ListCallBack(list)
                        }
                    }
                }

                else -> TODO("Not implementation click")
            }
        } else {
            TODO("View element get null")
        }
    }

    fun ListCallBack(userList: List<User>) {
        if (userList.count() == 0) {
            var toast = Toast.makeText(
                activity?.applicationContext,
                "Пользователь не найден",
                Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            val myIntent = Intent(activity?.applicationContext, MainActivity::class.java).apply {}
            startActivity(myIntent)
        }
    }
}