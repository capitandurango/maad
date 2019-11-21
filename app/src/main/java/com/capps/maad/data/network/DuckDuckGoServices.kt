package com.capps.maad.data.network

import com.sample.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The [DuckDuckGoServices] class is used to create a instance of
 * retrofit with services defined in [DuckDuckGoApi].
 */
object DuckDuckGoServices {

    /**
     * This method create and return a instance of [DuckDuckGoApi].
     * @return [DuckDuckGoApi].
     */
    fun getInstance(): DuckDuckGoApi {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = getLevel()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("${BuildConfig.URL_BASE}")
            .client(client)
            .build()
            .create(DuckDuckGoApi::class.java)
    }

    /**
     * This method return a level depending of [BuildConfig.DEBUG]
     * value.
     *
     * NOTE: If the application is in release mode, the services
     * logs are not displayed.
     *
     * @return [HttpLoggingInterceptor.Level].
     */
    private fun getLevel(): HttpLoggingInterceptor.Level = when {
        BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
        else -> HttpLoggingInterceptor.Level.NONE
    }

}