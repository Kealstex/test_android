package com.example.test
import android.content.Intent
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity:  AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View){
        val text = edittext.text.toString()
        textview.text = text
    }

    fun onSecondActivity(view: View){
        val secondIntent = Intent(this, SecondActivity::class.java)
        secondIntent.putExtra(SecondActivity.text,  textview.text)
        startActivity(secondIntent)
    }


}
