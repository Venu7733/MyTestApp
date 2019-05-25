package com.venu.test.data.source.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.venu.test.data.Book

@Database(entities = arrayOf(Book::class), version = 1)
abstract class NYTimesDatabase : RoomDatabase() {
  abstract fun nyTimesDao(): NYTimesDao
}