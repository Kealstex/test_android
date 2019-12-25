package com.example.test.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.test.R
import com.example.test.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val moneyListAdapter: MoneyListAdapter = MoneyListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        moneysList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moneyListAdapter
        }

        refreshLayout.setOnRefreshListener {
            moneysList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.moneys.observe(this, Observer {moneys ->
            moneys?.let {
            moneysList.visibility = View.VISIBLE
            moneyListAdapter.updateMoneyList(moneys)
        }
        })

        viewModel.moneyLoadError.observe(this, Observer{ isError ->
            isError?.let {
            listError.visibility = if(it) View.VISIBLE else View.GONE
        }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let{
            loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if(it){
                    listError.visibility = View.GONE
                    moneysList.visibility = View.GONE
        }
        }
        }
        )
    }
}
