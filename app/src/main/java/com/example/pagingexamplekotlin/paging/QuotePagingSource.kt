package com.example.pagingexamplekotlin.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pagingexamplekotlin.Constants
import com.example.pagingexamplekotlin.models.Quote
import com.example.pagingexamplekotlin.retrofit.QuoteAPI

class QuotePagingSource(private val quoteAPI: QuoteAPI) : PagingSource<Int, Quote>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Quote> {
        return try {
            val position = params.key ?: 1 // this will give the current page
            // that we want to load, this will automatically be calculated
            // by the Paging 3 library based on I guess the amount of data that we want to
            // keep in a single page
            val response = quoteAPI.getQuotes(position)

            Log.d(Constants.my_tag, "Inside load() of 'QuotePagingSource' class and its a success.Current that is loaded page = ${response.page} ")

            // in below statement , we are wrapping the api response data in 'LoadResult'
            // wrapper class and provided the information of previous and next key , 'key' simply means page number
            // if (position == response.totalPages) null else position + 1  means
            // if we reached end ie. if we are on last page then set value as null for
            // nextKey else set value as current page + 1
            return LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            Log.d(Constants.my_tag, "Inside catch , error occurred = ${e.message} ")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Quote>): Int? {
        // I have doubt in this but I guess its a boiler plate code which I have
        // to simply copy paste when using Paging 3 in my app
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}