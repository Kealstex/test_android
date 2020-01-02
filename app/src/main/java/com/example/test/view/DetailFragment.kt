package com.example.test.view


import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.test.R
import com.example.test.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var moneyuuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        arguments?.let {
            moneyuuid = DetailFragmentArgs.fromBundle(it).moneyUuid
        }
        viewModel.fetch(moneyuuid)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.moneyLiveData.observe(this, Observer {money ->
            money?.let {
                moneyName.text = money.name
                moneyCost.text = money.cost.toString()
                if (money.flow == 1) {
                    moneyCost.setTextColor(Color.GREEN)
                } else {
                    moneyCost.setTextColor(Color.RED)
                }
                moneyTime.text = money.time.toString()
            }
        })
    }
}
