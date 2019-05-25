package com.venu.test.di.modules

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Room
import android.arch.persistence.room.migration.Migration
import com.venu.test.data.source.db.NYTimesDao
import com.venu.test.data.source.db.NYTimesDatabase
import com.venu.test.ui.list.NYTimesViewModelFactory
import com.venu.test.utils.Constants
import com.venu.test.utils.Utils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(val app: Application) {

  companion object {
    val MIGRATION_1_2: Migration = object : Migration(1, 2){
      override fun migrate(database: SupportSQLiteDatabase) {
        // Change the table uuid to the correct one
        database.execSQL("ALTER TABLE nytimesbooks RENAME TO nytimesbookdata")
      }
    }
  }

  @Provides
  @Singleton
  fun provideApplication(): Application = app

  @Provides
  @Singleton
  fun provideNYTimesBookDatabase(app: Application): NYTimesDatabase = Room.databaseBuilder(app,
      NYTimesDatabase::class.java, Constants.DATABASE_NAME)
      /*.addMigrations(MIGRATION_1_2)*/
      .fallbackToDestructiveMigration()
      .build()

  @Provides
  @Singleton
  fun provideNYTimesDao(
      database: NYTimesDatabase): NYTimesDao = database.nyTimesDao()

  @Provides
  @Singleton
  fun provideNYTimesViewModelFactory(
      factory: NYTimesViewModelFactory): ViewModelProvider.Factory = factory

  @Provides
  @Singleton
  fun provideUtils(): Utils = Utils(app)
}