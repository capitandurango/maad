package com.capps.maad.data.network

import com.capps.maad.domain.responses.SearchDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Interface that defines the available services.
 */
interface DuckDuckGoApi {

    @GET("/")
    suspend fun search( @Query("q") query: String, @Query("format") format: String) :
            SearchDataResponse
}