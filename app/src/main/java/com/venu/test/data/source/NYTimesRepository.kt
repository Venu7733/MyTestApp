package com.venu.test.data.source

import android.util.Log
import com.venu.test.data.Book
import com.venu.test.data.source.db.NYTimesDao
import com.venu.test.data.source.network.ApiInterface
import com.venu.test.utils.Constants
import com.venu.test.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class NYTimesRepository @Inject constructor(val apiInterface: ApiInterface,
                                            val nyTimesDao: NYTimesDao, val utils: Utils) {

  fun getNYTimesData(limit: Int, offset: Int): Observable<List<Book>> {
    val hasConnection = utils.isConnectedToInternet()
    var observableFromApi: Observable<List<Book>>? = null
    if (hasConnection){
      observableFromApi = getNYTimesDataFromApi()
    }
    val observableFromDb = getNYTimesDataFromDb(limit, offset)

    return if (hasConnection) Observable.concatArrayEager(observableFromApi, observableFromDb)
    else observableFromDb
  }

  fun getNYTimesDataFromApi(): Observable<List<Book>> {
      return apiInterface.getNYTimes(Constants.START_ZERO_VALUE)
              .map {
                  for (item in it.results) {
                      nyTimesDao.insertNYTimesData(item)
                  }
                  it.results
              }
  }

  fun getNYTimesDataFromDb(limit: Int, offset: Int): Observable<List<Book>> {
    return nyTimesDao.queryNYTimesData(limit, offset)
        .toObservable()
        .doOnNext {
          Log.e("REPO DB * ", it.size.toString())
        }
  }
}