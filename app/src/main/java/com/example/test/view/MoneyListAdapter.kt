package com.example.test.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.ItemBinding
import com.example.test.model.Money
import kotlinx.android.synthetic.main.item.view.*

class MoneyListAdapter(val moneyList: ArrayList<Money>) :
    RecyclerView.Adapter<MoneyListAdapter.MoneyViewHolder>(),
    MoneyClickListener {
    fun updateMoneyList(newMoneyList: List<Money>) {
        moneyList.clear()
        moneyList.addAll(newMoneyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<ItemBinding>(inflater, R.layout.item, parent, false)
        return MoneyViewHolder(view)
    }

    override fun getItemCount(): Int = moneyList.size
    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.view.money = moneyList[position]
        holder.view.listener = this
    }

    override fun onMoneyClicked(view: View) {
        val uuid = view.moneyId.text.toString().toInt()
        val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            .setMoneyUuid(uuid)
        Navigation.findNavController(view).navigate(action)
    }

    class MoneyViewHolder(var view: ItemBinding) : RecyclerView.ViewHolder(view.root)
}