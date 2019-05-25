package com.venu.test.data


import com.squareup.moshi.Json

data class ReviewsResponse(@Json(name = "copyright")
                           val copyright: String = "",
                           @Json(name = "results")
                           val results: List<Book> = ArrayList(),
                           @Json(name = "num_results")
                           val numResults: Int = 0,
                           @Json(name = "status")
                           val status: String = "")


