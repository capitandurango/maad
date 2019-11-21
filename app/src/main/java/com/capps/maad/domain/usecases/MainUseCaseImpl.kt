package com.capps.maad.domain.usecases

import com.capps.maad.data.network.APIResponse
import com.capps.maad.data.repositories.RemoteRepositoryImpl
import com.capps.maad.domain.responses.SearchDataResponse

/**
 * Class that manages the bussines rules.
 */
class MainUseCaseImpl {

    companion object {
        // Tag for this class
        val TAG = this::class.java.simpleName

        // Default message for incomplete parameters.
        val QUERY_REQUIRED = "Query is required"

    }

    // The current repository for retreive de data. It could also be an interface.
    private val repository by lazy {
        RemoteRepositoryImpl()
    }

    /**
     * Call the service for return information from the service.
     * @param [query] to perfom the action.
     */
    suspend fun search(query: String?) : APIResponse<SearchDataResponse> {
        query ?: return APIResponse.Error(QUERY_REQUIRED) // Safe cast for validating required values.

        if(query.isEmpty()) { return APIResponse.Error(QUERY_REQUIRED) }

        return repository.fetchData(
            query = query
        )
    }
}