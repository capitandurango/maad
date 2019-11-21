package com.capps.maad.application.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.R
import com.capps.maad.application.extensions.character
import com.capps.maad.domain.responses.RelatedTopic

/**
 * Adapter that shows the Lists of Characters.
 */
class CharactersAdapter (val items: List<RelatedTopic>, val onClickListener: (RelatedTopic) -> Unit):
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_character_name, parent, false)
        return ViewHolder(view, onClickListener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCell(items[position])
    }

    class ViewHolder(val view: View, val onClickListener: (RelatedTopic) -> Unit) :
        RecyclerView.ViewHolder(view) {

        /**
         * Set the info in the cell
         */
        fun bindCell(model: RelatedTopic) {
            val txtCharacterName = view.findViewById<TextView>(R.id.txt_character_name)

            txtCharacterName.text = model.character()

            view.setOnClickListener {
                onClickListener(model)
            }
        }
    }
}