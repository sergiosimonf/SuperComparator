package com.tfg.supercomparator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel: ViewModel() {
    private val _showDialog = MutableLiveData<Boolean>().apply { value = false }
    val showDialog: LiveData<Boolean> = _showDialog


    fun showDialog() {
        _showDialog.value = true
    }

    fun hiadeDialog() {
        _showDialog.value = false
    }
}