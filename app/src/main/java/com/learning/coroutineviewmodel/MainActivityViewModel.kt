package com.learning.coroutineviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    // when we use viewModelScope ktx extension then no need to call onCleared() , Job(), CoroutineScope() so do comment all
//    private val myJob = Job()
//    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        viewModelScope.launch {
            // your code is here
        }
    }



    // To avoid memory leaks and unwanted behaviour of application...
//    override fun onCleared() {
//        super.onCleared()
//        myJob.cancel()
//    }

    /**
     * In a scenario of if we have 20 view model classes in that case what to do, is it suitable to call all
     * view models onCleared() override function , in that case we have use viewModelScope it is a coroutine
     * tied to a viewModel, we can easily use this scope from an extension function available on the viewmodel-ktx library
     * so we need to add viewmodel.ktx dependency in the app level build file
     */
}