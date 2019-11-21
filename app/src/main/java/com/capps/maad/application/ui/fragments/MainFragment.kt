package com.capps.maad.application.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.R
import com.capps.maad.application.ui.adapters.CharactersAdapter
import com.capps.maad.application.viewmodels.MainViewModel
import com.capps.maad.application.viewmodels.factory.MainViewModelFactory
import com.capps.maad.data.network.APIResponse
import com.sample.BuildConfig
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * The Main Fragment in the app.
 */
class MainFragment : Fragment() {

    companion object {
        val TAG = this::class.java.simpleName
    }

    // View model attached to the activity.
    private val model by lazy {
        activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()

        swipe_container.setOnRefreshListener {
            fetchData()
        }
    }

    /**
     * Retreive the information for fill the recycler view.
     */
    private fun fetchData(){
        swipe_container.isRefreshing = true

        model.fetch(query = BuildConfig.QUERY) // We can change the query for another request easy.
        model.getItems().observe(this, Observer { response ->
            Log.d(TAG, "response success $response")
            swipe_container.isRefreshing = false

            Log.d(TAG, "Return Success")

            recycler_photos.apply {
                layoutManager = LinearLayoutManager(this@MainFragment.context)
                adapter = CharactersAdapter(response){ click ->
                    Log.d(TAG, "Click on item ${click}")
                    model.setSelectItem(click)
                }
            }
        })

    }
}