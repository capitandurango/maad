package com.capps.maad.application.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.capps.maad.application.ui.Constants
import com.capps.maad.application.ui.fragments.DetailedFragment
import com.capps.maad.application.ui.fragments.MainFragment
import com.capps.maad.application.viewmodels.MainViewModel
import com.sample.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The only activity for the app.
 */
class MainActivity : AppCompatActivity() {

    companion object {
        // TAG for the logger
        private val TAG = this::class.java.simpleName
    }

    // View model attached to the activity.
    private val model by lazy {
        ViewModelProviders.of(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) return

        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, MainFragment())
        fragmentTransaction.commit()
    }

    override fun onResume() {
        super.onResume()
        addObservers()
    }

    /**
     * Observers for watch changes in the data
     */
    private fun addObservers(){
        model.getSelectItem().observe(this, Observer { item ->
            Log.d(TAG, "Change on the activity $item")

            val fragment = DetailedFragment()
            fragment.arguments = Bundle().apply {
                putParcelable(Constants.PARAM_ITEM, item)
            }

            val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()

            when(fragment_container_detail) { // Decide which fragment used
                null -> fragmentTransaction.replace(
                            R.id.fragment_container,
                            fragment
                        ).addToBackStack(null)
                else -> fragmentTransaction.replace(
                            R.id.fragment_container_detail,
                            fragment
                        ).addToBackStack(null)
            }

            fragmentTransaction.commit()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val actionSearch = menu?.findItem(R.id.action_search)
        val searchView = actionSearch?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                callSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                callSearch(newText)
                return true
            }

            fun callSearch(query: String?) { //Do searching
                Log.d(TAG, "Execute query on local")
                model.filterItems(query ?: return)
            }
        })

        return true
    }


}
