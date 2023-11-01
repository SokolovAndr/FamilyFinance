package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListAccountsBinding
import com.example.familyfinance.models.Account

class AccountRCViewAdapter: RecyclerView.Adapter<AccountRCViewAdapter.AccountHolder>() {

    val accountList = ArrayList<Account>()

    class AccountHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListAccountsBinding.bind(item)
        fun bind(account: Account) = with(binding) {
            TextViewAccount1.text = account.id.toString()
            TextViewAccount2.text = account.name

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountRCViewAdapter.AccountHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_accounts, parent, false)
        return AccountRCViewAdapter.AccountHolder(view)
    }

    override fun onBindViewHolder(holder: AccountRCViewAdapter.AccountHolder, position: Int) {
        holder.bind(accountList[position])
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    fun addAccount(account: Account) {
        accountList.add(account)
        notifyDataSetChanged()
    }

}