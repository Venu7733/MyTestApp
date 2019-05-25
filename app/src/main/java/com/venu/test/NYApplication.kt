package com.venu.test

import android.app.Activity
import android.app.Application
import com.venu.test.di.component.DaggerAppComponent
import com.venu.test.di.modules.AppModule
import com.venu.test.di.modules.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class NYApplication: Application(), HasActivityInjector {

  @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder()
        .appModule(AppModule(this))
        .netModule(NetModule(BuildConfig.URL))
        .build().inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> = activityInjector

}