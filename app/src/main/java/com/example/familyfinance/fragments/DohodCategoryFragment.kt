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
import com.example.familyfinance.CategoryAdapter
import com.example.familyfinance.OnLinkFragment
import com.example.familyfinance.R
import com.example.familyfinance.database.MainDb
import com.example.familyfinance.models.Category
import com.google.android.material.chip.Chip

private lateinit var adapter: CategoryAdapter  //перемернная для записи адаптера
private lateinit var rcview: RecyclerView  //перемернная для работы с rcview
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
        view.findViewById<RecyclerView>(R.id.rcviewDohod).setOnClickListener(this)
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
        } else {
            TODO("View element get null")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        rcview = view.findViewById(R.id.rcviewDohod)
        rcview.layoutManager = layoutManager
        rcview.setHasFixedSize(true)
        adapter = CategoryAdapter()
        rcview.adapter = adapter
        val db = MainDb.getDb(activity?.applicationContext!!)

        db.getDao().getAllCategoriesDohod(true).asLiveData().observe(requireActivity()) { list ->
            list.forEach {
                val test = Category(it.id, it.name, it.direction)
                adapter.addCategory(test)
            }
        }

    }

}