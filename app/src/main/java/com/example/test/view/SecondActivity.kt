package com.example.test.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.test.DataBaseHandler
import com.example.test.Money
import com.example.test.R
import com.example.test.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    companion object{
        const val text = ""
    }

    lateinit var DBHandler : DataBaseHandler
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DBHandler = DataBaseHandler(this)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_second
        )
        binding.name = "Polina"

        binding.money = Money(12, 12f, 12, "111")
        binding.lifecycleOwner = this
        binding.handler = DBHandler

//        val chose = mutableListOf<String>("Expense", "Revenue")

        /*var adapter :ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, chose)
        List.adapter = adapter
        List.setOnItemClickListener{
            parent, view, position, id ->
            tvChose.text = chose[position]
        }*/
    }

//    fun insertData(view: View){
//        val cost = binding.tvCost.toString().toFloat()
//        val revenue = binding.tvRevenue.toString().toInt()
//        val name = binding.Name.toString()
//        val time = binding.tvExpense.toString().toInt()
//
//        DBHandler.insertData(Money(revenue, cost, time, name ))
//
//        val db = DBHandler.readAllMoney()
////        tvRevenue.text.clear()
////        tvCost.text.clear()
////        Name.text.clear()
////        tvTime.text.clear()
//
//    }

//    fun deleteData(view: View){
//        DBHandler.clear();
//    }
}