package com.example.assignment.viewmodels

import com.example.assignment.repository.Repository
import com.example.assignment.response.CountryItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment.api.Service
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DisplayCountryListViewModel(
    private val repository: Repository = Repository(Service.create(), Dispatchers.IO)
) : ViewModel() {
    private val _uiState = MutableLiveData<Result<List<CountryItem>>>()
    val uiState: LiveData<Result<List<CountryItem>>>
        get() = _uiState

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        getCountryList()
    }

    private fun getCountryList() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val result = repository.getCountryList()
                _uiState.value = Result.success(result)
                _isLoading.value = false
            } catch (exception: Exception) {
                _uiState.value = Result.failure(exception)
                _isLoading.value = false
            }
        }
    }
}