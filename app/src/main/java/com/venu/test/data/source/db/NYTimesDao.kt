package com.venu.test.data.source.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.venu.test.data.Book
import io.reactivex.Single

@Dao
interface NYTimesDao {

  @Query("SELECT * FROM books limit :limit offset :offset")
  fun queryNYTimesData(limit:Int, offset:Int): Single<List<Book>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertNYTimesData(book: Book)

  @Insert(
      onConflict = OnConflictStrategy.REPLACE
  )
  fun insertAllNYTimesBookData(books: List<Book>)
}