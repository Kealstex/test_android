package com.example.test.view
import android.content.Intent
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.test.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity:  AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        navController = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    /*fun onClick(view: View){
        val text = edittext.text.toString()
        textview.text = text
    }

    fun onSecondActivity(view: View){
        val secondIntent = Intent(this, SecondActivity::class.java)
        secondIntent.putExtra(SecondActivity.text,  textview.text)
        startActivity(secondIntent)
    }*/


}
