package com.example.pagingexamplekotlin.retrofit

import com.example.pagingexamplekotlin.models.QuoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteAPI {
    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int): QuoteResponse
}