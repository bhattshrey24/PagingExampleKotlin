package com.example.pagingexamplekotlin.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.pagingexamplekotlin.paging.QuotePagingSource
import com.example.pagingexamplekotlin.retrofit.QuoteAPI
import javax.inject.Inject

class QuoteRepository @Inject constructor(private val quoteAPI: QuoteAPI) {
    //'pageSize' Defines the number of items loaded at once from the PagingSource.
    //'maxSize' Defines the maximum number of items that may be loaded into PagingData before pages should be dropped.
    fun getQuotes() = Pager(
        config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotePagingSource(quoteAPI) }
    ).liveData // this '.liveData' converts this Pager to liveData
}