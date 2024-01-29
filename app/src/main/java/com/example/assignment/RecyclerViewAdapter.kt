package com.example.assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.response.CountryItem


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private var countryItemList: List<CountryItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTextView.text = countryItemList[position].name.plus(" ").plus(",").plus(" ")
        holder.regionTextView.text = countryItemList[position].region
        holder.codeTextView.text = countryItemList[position].code
        holder.capitalTextView.text = countryItemList[position].capital
    }

    override fun getItemCount(): Int {
        return countryItemList.size
    }

    internal fun setCountryItemList(countryList: List<CountryItem>) {
        countryItemList = countryList
        notifyItemRangeChanged(0, countryItemList.size)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView
        val regionTextView: TextView
        val codeTextView: TextView
        val capitalTextView: TextView

        init {
            nameTextView = view.findViewById(R.id.nameTextView)
            regionTextView = view.findViewById(R.id.regionTextView)
            codeTextView = view.findViewById(R.id.codeTextView)
            capitalTextView = view.findViewById(R.id.capitalTextView)
        }
    }
}