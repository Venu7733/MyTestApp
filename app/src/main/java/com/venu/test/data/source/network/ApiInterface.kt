package com.venu.test.data.source.network

import com.venu.test.data.ReviewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

  @GET("reviews.json?author=Stephen+King&api-key=n4SnOGxdhdx0OMSjiGqDriLdkyG4GkI7")
  fun getNYTimes(@Query("start") start: String): Observable<ReviewsResponse>
}