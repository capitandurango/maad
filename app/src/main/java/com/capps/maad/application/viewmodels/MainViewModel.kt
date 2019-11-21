package com.capps.maad.application.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capps.maad.application.extensions.character
import com.capps.maad.data.network.APIResponse
import com.capps.maad.domain.responses.RelatedTopic
import com.capps.maad.domain.usecases.MainUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * View Model for the MainActivity, also this viewmodels help us for sharing the data between
 * fragments.
 */
class MainViewModel : ViewModel() {

    /**
     * Item selected for the adapter.
     */
    private val itemSelected: MutableLiveData<RelatedTopic> = MutableLiveData()

    /**
     * Item selected for the adapter.
     */
    private val items: MutableLiveData<List<RelatedTopic>> = MutableLiveData()

    /**
     * Variable that keep the original response for the service.
     */
    private var copyItems: List<RelatedTopic>? = null

    /**
     * Use case responsible for manage the bussines rules.
     */
    private val mainUseCase by lazy {
        MainUseCaseImpl()
    }

    /**
     * Fetch all the photos provides by the server.
     * @param query: The query for search the photo.
     */
     fun fetch(query : String?) {
        GlobalScope.launch (Dispatchers.Main) {

            when (val response = mainUseCase.search(query = query)) {
                is APIResponse.Success -> {
                    items.postValue(response.value.topics)
                    copyItems = response.value.topics.toMutableList()
                }
            }
        }
    }

    /**
     * Disabled because we need to storage the data in the view model
    fun fetch(query : String?): LiveData<APIResponse<SearchDataResponse>> {
        return liveData {
            emit(
                mainUseCase.search(
                    query = query
                )
            )
        }
    }*/

    /**
     * Put a selected item in the live data, for uploada fragments.
     */
    fun setSelectItem(item: RelatedTopic){
        itemSelected.postValue(item)
    }

    /**
     * The item selected.
     */
    fun getSelectItem() = itemSelected

    /**
     * Return the list of available items.
     */
    fun getItems() = items

    /**
     * Function for filter items.
     * @param query: Query for search in the storage.
     */
    fun filterItems(query: String){
        when {
            query.isEmpty() -> {
                items.postValue(copyItems) // Restore original values.
            }
            query.isNotEmpty() -> {
                items.postValue(
                    copyItems?.filter { topic ->
                        topic.character()?.toUpperCase()?.contains(query.toUpperCase()) ?: false
                    }
                )
            }
        }
    }

}