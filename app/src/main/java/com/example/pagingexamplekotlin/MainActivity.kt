package com.example.pagingexamplekotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingexamplekotlin.paging.QuotePagingAdapter
import com.example.pagingexamplekotlin.viewmodels.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Todo
    // This app is example from video = https://www.youtube.com/watch?v=bGRQ5nLVPhk
    // To see the Json that we are getting go to : https://quotable.io/quotes/?page={put any page number}

    lateinit var quoteViewModel: QuoteViewModel
    lateinit var adapter: QuotePagingAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.myQuotesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        adapter = QuotePagingAdapter()
        recyclerView.adapter = adapter

        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)
        quoteViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it) // I guess this submitData saves data in cache for faster access
        })
    }
}
