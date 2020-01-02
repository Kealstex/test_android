package com.example.test.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.test.R
import com.example.test.model.Money
import com.example.test.viewmodel.AddViewModel
import kotlinx.android.synthetic.main.fragment_dialog.*

class AddFragment: Fragment() {
    private lateinit var viewModel: AddViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(AddViewModel::class.java)
        add_money.isEnabled = false
        refresh(listOf(newTime, newName, newCost))

        add_money.setOnClickListener {
            addMoney(it)
        }
    }

    fun addMoney(view: View){
        val newName = newName.text.toString()

        val newFlow = if (tgCost.isChecked) 1 else 0
        val newTimeFlow = if (tglTime.isChecked) 1 else 0

        var newCost = newCost.text.toString().toFloat()
        if (newFlow == 0)
            newCost *= -1

        var newTime = newTime.text.toString().toFloat()
        if (newTimeFlow == 0)
            newTime *= -1

        val item = Money(newFlow, newCost, newTime, newName)

        viewModel.insertMoney(item)

        Toast.makeText(requireContext(),"Элемент добавлен", Toast.LENGTH_SHORT).show()

        val action = AddFragmentDirections.actionAddFragmentToListFragment()
        Navigation.findNavController(view).navigate(action)
    }

    fun refresh(list: List<EditText>){
    val watcher = EditTextWatcher(list, add_money)
        for (edittext in list){
            edittext.addTextChangedListener(watcher)
        }
    }
}