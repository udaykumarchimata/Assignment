package com.example.assignment

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.viewmodels.DisplayCountryListViewModel

class MainActivity : ComponentActivity() {
    private lateinit var loadingProgressBar: ProgressBar
    private lateinit var listCountryRecyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_country)
        loadingProgressBar = findViewById(R.id.loadingProgressBar)
        listCountryRecyclerView = findViewById(R.id.listCountryRecyclerView)
        recyclerViewAdapter = RecyclerViewAdapter()
        listCountryRecyclerView.adapter = recyclerViewAdapter
        listCountryRecyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val displayCountryListViewModel = ViewModelProvider(this).get(DisplayCountryListViewModel::class.java)
        displayCountryListViewModel.isLoading.observe(this) {
            loadingProgressBar.isVisible = false
        }
        displayCountryListViewModel.uiState.observe(this) { listOfCountries ->
            listOfCountries.onSuccess {
                recyclerViewAdapter.setCountryItemList(it)
                listCountryRecyclerView.isVisible = true
            }
            listOfCountries.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}