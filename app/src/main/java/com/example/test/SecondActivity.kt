package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    companion object{
        const val text = ""
    }

    lateinit var DBHandler : DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val str = intent.getStringExtra(text)
        val argExtra= intent.extras


        DBHandler = DataBaseHandler(this)

        val chose = mutableListOf<String>("Expense", "Revenue")

        var adapter :ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, chose)
        List.adapter = adapter
        List.setOnItemClickListener{
            parent, view, position, id ->
            tvChose.text = chose[position]
        }
    }

    fun insertData(view: View){
        val db = DBHandler.readAllMoney()
        val cash = this.tvCash.text.toString().toFloat()
        val revenue = this.tvRevenue.text.toString().toFloat()
        val id =  this.tvId.text.toString().toInt()
        val expense = this.tvExpense.text.toString().toFloat()

        DBHandler.insertData(Money(id, revenue, expense, cash))
        tvRevenue.text.clear()
        tvCash.text.clear()
        tvId.text.clear()
        tvExpense.text.clear()

    }


}