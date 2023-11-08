package com.example.familyfinance.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.activities.MainActivity
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Category
import com.example.familyfinance.models.User

class CategoryAddFragment : Fragment, View.OnClickListener {

    private var mListener: OnLinkFragment? = null

    constructor() : super(R.layout.fragment_category_add) {
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View? = super.onCreateView(inflater, container, savedInstanceState)

        if (view != null)
            CreateInstanseFragment(view)

        return view;
    }

    fun CreateInstanseFragment(view: View) {
        //registration click move link
        view.findViewById<Button?>(R.id.buttonSaveCategory).setOnClickListener(this)
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
                R.id.buttonSaveCategory -> {

                    var etNewCat = view?.findViewById<EditText>(R.id.etNewCategory)
                    var isDohod = view?.findViewById<CheckBox>(R.id.checkBox)


                    if (etNewCat?.text?.isEmpty() == true) {
                        var toast = Toast.makeText(
                            activity?.applicationContext,
                            "Заполните все поля",
                            Toast.LENGTH_SHORT
                        )
                        toast.show()
                    } else {

                        if (isDohod?.isChecked() == true) {

                            val category = Category(
                                null,
                                etNewCat?.text.toString(),
                                true
                            )
                            Thread {
                                db.getDao().insertCategory(category)
                            }.start()


                            var toast = Toast.makeText(
                                activity?.applicationContext,
                                "Категория успешно добавлена",
                                Toast.LENGTH_SHORT
                            )
                            toast.show()

                        } else {
                            val category = Category(
                                null,
                                etNewCat?.text.toString(),
                                false

                            )
                            Thread {
                                db.getDao().insertCategory(category)
                            }.start()

                            var toast = Toast.makeText(
                                activity?.applicationContext,
                                "Категория успешно добавлена",
                                Toast.LENGTH_SHORT
                            )
                            toast.show()
                        }
                    }
                    mListener?.onLinkFragment("Back2")
                }

                else -> TODO("Not implementation click")
            }
        } else {
            TODO("View element get null")
        }
    }
}
