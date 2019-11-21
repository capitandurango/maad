package com.capps.maad.data.repositories

import android.util.Log
import com.capps.maad.data.network.APIResponse
import com.capps.maad.data.network.DuckDuckGoServices
import com.capps.maad.domain.responses.SearchDataResponse

/**
 * The [RemoteRepositoryImpl] class implements all
 * the proccess for making request for the current pictures.
 */
class RemoteRepositoryImpl {

    companion object {
        // The tag name
        val TAG = this::class.java.simpleName

        // This can change to the reference to the string with R.string.....
        val SERVICE_ERROR = "Retreive data with error"

        // Format for retreive information from the server.
        val FORMAT = "json"

        /// We need to concatenate this value for retreive only characters.
        val CONST_CHARACTER = " characters"
    }

    // Instance connection to the service
    private val duckDuckGoApi by lazy {
        DuckDuckGoServices.getInstance()
    }

    /**
     * Retreives all information for characterers.
     *
     * [query] Query for retreive information.
     * [format] Available format type for retreive the information. json
     *
     * return [SearchDataResponse] with the response for the service.
     */
    suspend fun fetchData(query : String, format: String = FORMAT): APIResponse<SearchDataResponse> {
        Log.d(TAG, "Making request for the data ...")
        return try {
            val response = duckDuckGoApi.search(
                "$query$CONST_CHARACTER", format
            )

            APIResponse.Success(response)
        } catch (ex: Exception){
            Log.e(TAG, SERVICE_ERROR)
            APIResponse.Error(SERVICE_ERROR, ex)
        }
    }



}