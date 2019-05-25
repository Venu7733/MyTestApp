package com.venu.test.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.squareup.moshi.Json
import java.io.Serializable

@Entity(
        tableName = "books"
)
data class Book(

        @Json(name = "book_title")
        @PrimaryKey
        @ColumnInfo(name = "book_title")
        val name: String,

        @Json(name = "uuid")
        @ColumnInfo(name = "uuid")
        val uuid: String?,

        @Json(name = "book_author")
        @ColumnInfo(name = "book_author")
        val author: String?,

        @Json(name = "summary")
        @ColumnInfo(name = "summary")
        val summary: String?,

        @Json(name = "uri")
        @ColumnInfo(name = "uri")
        val uri: String,

        @Json(name = "byline")
        @ColumnInfo(name = "byline")
        val byline: String?,

        @Json(name = "publication_dt")
        @ColumnInfo(name = "publication_dt")
        val publicationDate: String?,

        @Json(name = "url")
        @ColumnInfo(name = "url")
        val url: String?
) : Serializable