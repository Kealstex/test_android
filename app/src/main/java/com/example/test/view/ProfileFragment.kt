package com.example.test.view

import android.content.Context
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.test.R
import com.example.test.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        viewModel.fetch()

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.profileLiveData.observe(this, Observer {profile ->
            profile?.let {

                lblExpense.text = profile.expense.toString()
                lblRevenue.text = profile.revenue.toString()
                lblCash.text = profile.flow.toString()
                lblTime.text = profile.time.toString()

                if (profile.flow <= 0) {
                    lblCash.setTextColor(Color.RED)
                }
                if (profile.time <= 0) {
                    lblTime.setTextColor(Color.RED)
                }
            }
        })
    }
}