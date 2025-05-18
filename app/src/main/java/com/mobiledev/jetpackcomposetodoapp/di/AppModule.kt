//package com.mobiledev.jetpackcomposetodoapp.di
//
//import android.app.Application
//import androidx.room.Room
//import com.mobiledev.jetpackcomposetodoapp.data.local.TodoDao
//import com.mobiledev.jetpackcomposetodoapp.data.model.TodoDatabase
//import com.mobiledev.jetpackcomposetodoapp.data.remote.TodoApiService
//import com.mobiledev.jetpackcomposetodoapp.data.remote.RetrofitInstance
//import com.mobiledev.jetpackcomposetodoapp.data.repository.TodoRepository
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Provides
//    @Singleton
//    fun provideApiService(): TodoApiService = RetrofitInstance.api
//
//    @Provides
//    @Singleton
//    fun provideDatabase(app: Application): TodoDatabase =
//        Room.databaseBuilder(app, TodoDatabase::class.java, "todo_db").build()
//
//    @Provides
//    fun provideTodoDao(db: TodoDatabase): TodoDao = db.todoDao()
//
//    @Provides
//    @Singleton
//    fun provideRepository(api: TodoApiService, dao: TodoDao): TodoRepository =
//        TodoRepository(api, dao)
//}
