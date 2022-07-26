package com.example.pagingexamplekotlin.paging

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexamplekotlin.R
import com.example.pagingexamplekotlin.models.Quote

// Instead of using RecyclerViewAdapter we use PagingDataAdapter while using Paging 3.
// Its almost exactly same as RecyclerViewAdapter
class QuotePagingAdapter :
        PagingDataAdapter<Quote, QuotePagingAdapter.QuoteViewHolder>(COMPARATOR) { // OBSERVE: We are passing our 'COMPARATOR' companion object to the constructor

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quote: TextView = itemView.findViewById<TextView>(R.id.quoteTV)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)  //This getItem() is a builtIn Function Of
        // Paging 3 Library. See the type of the variable 'item' its of type 'Quote'.
        // See in the top we mentioned 'Quote' which is the type of data that this recycler view will hold
        if (item != null) {
            // here we simply do normal stuff that we use to do in 'onBindViewHolder' of a normal recyclerViewAdapter
            holder.quote.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quote_list_item, parent, false)
        return QuoteViewHolder(view)
    }

    companion object { // This is necessary to do ,I Guess this helps paging library to differentiate new and old data
        private val COMPARATOR = object : DiffUtil.ItemCallback<Quote>() {
            override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem._id == newItem._id // this will check if the items are same or not ie. if their ids are same then this will return true
            }

            override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
                return oldItem == newItem // // this will check the contents ie. if 2 quotes are same or not.
                 // We are able to compare 2 objects because 'Quote' is a 'data' class and 'data' class provides
                 // this functionality that we can compare 2 data class objects by just comparing
                 // the variables that holds the objects otherwise if it was a normal
                 // class then oldItem == newItem would have simply checked if both 'oldItem' and 'newItem' are
                 // pointing to same object or not and would NOT have compared the content present in the
                 // objects which they are pointing
            }
        }
    }
}