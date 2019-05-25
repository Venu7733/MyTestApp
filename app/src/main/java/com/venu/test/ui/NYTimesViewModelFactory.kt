package com.venu.test.ui.list

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject


class NYTimesViewModelFactory @Inject constructor(
    private val nyTimesDataViewModel: NYTimesViewModel) : ViewModelProvider.Factory {

  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(NYTimesViewModel::class.java)) {
      return nyTimesDataViewModel as T
    }
    throw IllegalArgumentException("Unknown class")
  }
}