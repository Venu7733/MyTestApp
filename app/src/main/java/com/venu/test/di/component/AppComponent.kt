package com.venu.test.di.component

import com.venu.test.NYApplication
import com.venu.test.di.modules.AppModule
import com.venu.test.di.modules.BuildersModule
import com.venu.test.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class, AppModule::class,
        NetModule::class)
)
interface AppComponent {
  fun inject(app: NYApplication)
}