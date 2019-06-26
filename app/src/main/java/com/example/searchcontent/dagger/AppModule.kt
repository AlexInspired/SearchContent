package com.example.searchcontent.dagger

import android.content.Context
import com.example.searchcontent.App
import com.example.searchcontent.data.executor.JobExecutor
import com.example.searchcontent.data.net.ApiConstants
import com.example.searchcontent.data.net.ApiInterface
import com.example.searchcontent.data.repository.FilmDataRepository
import com.example.searchcontent.data.repository.SearchDataRepository
import com.example.searchcontent.domain.executor.PostExecutionThread
import com.example.searchcontent.domain.executor.ThreadExecutor
import com.example.searchcontent.domain.repository.FilmRepository
import com.example.searchcontent.domain.repository.SearchRepository
import com.example.searchcontent.presentation.UIThread
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class AppModule(private val application: App) {

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context {
        return this.application
    }

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
        return uiThread
    }
}