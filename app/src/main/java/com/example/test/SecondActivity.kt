package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object{
        const val text = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val str = intent.getStringExtra(text)
        val argExtra= intent.extras
        val money = argExtra?.getSerializable(Money::class.java.simpleName) as Money
        tvCash.text= money.cash.toString()
        tvExpense.text=money.expense.toString()
        tvId.text=money.Id.toString()
        tvRevenue.text=money.revenue.toString()
        textView.text = str

        val chose = mutableListOf<String>("Expense", "Revenue")

        var adapter :ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, chose)
        List.adapter = adapter
        List.setOnItemClickListener{
            parent, view, position, id ->
            tvChose.text = chose[position]
        }
    }
}