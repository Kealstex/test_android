package com.example.test.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.model.Money
import kotlinx.android.synthetic.main.item.view.*

class MoneyListAdapter(val moneyList: ArrayList<Money>) : RecyclerView.Adapter<MoneyListAdapter.MoneyViewHolder>() {
    fun updateMoneyList(newMoneyList: List<Money>){
        moneyList.clear()
        moneyList.addAll(newMoneyList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item, parent, false)
        return MoneyViewHolder(view)
    }

    override fun getItemCount(): Int = moneyList.size
    override fun onBindViewHolder(holder: MoneyViewHolder, position: Int) {
        holder.view.name.text = moneyList[position].name
        holder.view.cost.text = moneyList[position].cost.toString()
        holder.view.time.text = moneyList[position].time.toString()

        if(moneyList[position].flow == 1)
            holder.view.cost.setTextColor(Color.GREEN)

        holder.view.setOnClickListener{
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }
    }

    class MoneyViewHolder(var view: View) : RecyclerView.ViewHolder(view)
}