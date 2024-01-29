package com.example.assignment.repository

import com.example.assignment.api.Service
import com.example.assignment.response.CountryItem
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class Repository(
    private val service: Service,
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getCountryList(): List<CountryItem> {
        return withContext(ioDispatcher) {
            service.getCountryList()
        }
    }
}