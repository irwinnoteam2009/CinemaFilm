package ru.meteoctx.hellboys.cinemafilm.data.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieApiFactory {

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    private fun makeGson(): Gson {
        val gson = GsonBuilder()
                .setLenient()
                .create()
        return gson
    }

    private fun makeClient(interceptors: Array<Interceptor>): OkHttpClient {
        val client = OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
        interceptors.forEach { item -> client.addInterceptor(item) }
        return client.build()
    }

    fun makeMovieApi(): MovieApi {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://gist.githubusercontent.com/")
                .client(makeClient(arrayOf(makeLoggingInterceptor())))
                .addConverterFactory(GsonConverterFactory.create(makeGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        return retrofit.create(MovieApi::class.java)
    }
}