package com.venu.test.di.modules

import com.venu.test.ui.NYTimesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

  @ContributesAndroidInjector
  abstract fun contributesNYTimesActivity(): NYTimesActivity
}