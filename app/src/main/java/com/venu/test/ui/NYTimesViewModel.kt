package com.venu.test.ui.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.venu.test.data.Book
import com.venu.test.data.source.NYTimesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject


class NYTimesViewModel @Inject constructor(
    private val nyTimesRepository: NYTimesRepository) : ViewModel() {

  var nyTimesBookResult: MutableLiveData<List<Book>> = MutableLiveData()
  var nyTimesBookError: MutableLiveData<String> = MutableLiveData()
  var nyTimesBookLoader: MutableLiveData<Boolean> = MutableLiveData()
  lateinit var disposableObserver: DisposableObserver<List<Book>>

  fun nyTimesBookResult(): LiveData<List<Book>> {
    return nyTimesBookResult
  }

  fun nyTimesBookError(): LiveData<String> {
    return nyTimesBookError
  }

  fun nyTimesBookLoader(): LiveData<Boolean> {
    return nyTimesBookLoader
  }

  fun nyTimesBook(limit: Int, offset: Int ) {

    disposableObserver = object : DisposableObserver<List<Book>>() {
      override fun onComplete() {

      }

      override fun onNext(books: List<Book>) {
        nyTimesBookResult.postValue(books)
        nyTimesBookLoader.postValue(false)
      }

      override fun onError(e: Throwable) {
        nyTimesBookError.postValue(e.message)
        nyTimesBookLoader.postValue(false)
      }
    }

    nyTimesRepository.getNYTimesData(limit, offset)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .debounce(400, MILLISECONDS)
        .subscribe(disposableObserver)
  }

  fun disposeElements(){
    if(!disposableObserver.isDisposed) disposableObserver.dispose()
  }

}