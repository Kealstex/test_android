package com.example.test.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.model.Profile

class ProfileViewModel:ViewModel(){
    var profileLiveData = MutableLiveData<Profile>()

    fun fetch(){
        profileLiveData.value = Profile("Имя", 1560f, 45f, 1000f, 2560f)
    }
}