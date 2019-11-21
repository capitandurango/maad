package com.capps.maad.application.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.sample.R
import com.capps.maad.application.extensions.character
import com.capps.maad.application.ui.Constants
import com.capps.maad.application.viewmodels.MainViewModel
import com.capps.maad.domain.responses.RelatedTopic
import kotlinx.android.synthetic.main.fragment_detailed.*

class DetailedFragment : Fragment () {

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
        return inflater.inflate(R.layout.fragment_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { arguments ->
            val item = arguments[Constants.PARAM_ITEM] as RelatedTopic
            Log.d(TAG, item.toString())

            val requestOption = RequestOptions().
                centerCrop().
                apply(
                    RequestOptions.bitmapTransform(RoundedCorners(16))
                )

            txt_title.text = item.character()
            txt_description.text = item.text

            Glide.with(view)
                .load(item.icon.url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .error(R.drawable.ic_no_image)
                .apply(requestOption)
                .into(character_img)
        }
    }
}