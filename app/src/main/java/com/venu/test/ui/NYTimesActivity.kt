package com.venu.test.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.venu.test.R
import com.venu.test.R.layout
import com.venu.test.data.Book
import com.venu.test.ui.list.NYTimesViewModel
import com.venu.test.ui.list.NYTimesViewModelFactory
import com.venu.test.utils.Constants
import com.venu.test.utils.InfiniteScrollListener
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_nytimes.*
import java.util.*
import javax.inject.Inject

class NYTimesActivity : AppCompatActivity() {

  @Inject
  lateinit var nyTimesViewModelFactory: NYTimesViewModelFactory
  var nyTimesAdapter = NYTimesAdapter(ArrayList())
  lateinit var nyTimesViewModel: NYTimesViewModel
  var currentPage = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_nytimes)
    AndroidInjection.inject(this)

    initializeRecycler()

    nyTimesViewModel = ViewModelProviders.of(this, nyTimesViewModelFactory).get(
        NYTimesViewModel::class.java)

    progressBar.visibility = View.VISIBLE
    loadData()

    nyTimesViewModel.nyTimesBookResult().observe(this,
        Observer<List<Book>> {
          if (it != null) {
            val position = nyTimesAdapter.itemCount
            nyTimesAdapter.addNYTimesBookData(it)
            recycler.adapter = nyTimesAdapter
            recycler.scrollToPosition(position - Constants.LIST_SCROLLING)
          }
        })

    nyTimesViewModel.nyTimesBookError().observe(this, Observer<String> {
      if (it != null) {
        Toast.makeText(this, resources.getString(R.string.error_message) + it,
            Toast.LENGTH_SHORT).show()
      }
    })

    nyTimesViewModel.nyTimesBookLoader().observe(this, Observer<Boolean> {
      if (it == false) progressBar.visibility = View.GONE
    })
  }

  private fun initializeRecycler() {
    val gridLayoutManager = GridLayoutManager(this, 1)
    gridLayoutManager.orientation = LinearLayoutManager.VERTICAL
    recycler.apply {
      setHasFixedSize(true)
      layoutManager = gridLayoutManager
      addOnScrollListener(InfiniteScrollListener({ loadData() }, gridLayoutManager))
    }
  }

  fun loadData() {
    nyTimesViewModel.nyTimesBook(Constants.LIMIT, currentPage * Constants.OFFSET)
    currentPage++
  }

  override fun onDestroy() {
    nyTimesViewModel.disposeElements()
    super.onDestroy()
  }
}
