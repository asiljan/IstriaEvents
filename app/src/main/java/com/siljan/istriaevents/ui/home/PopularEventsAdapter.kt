package com.siljan.istriaevents.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siljan.domain.models.Event
import com.siljan.istriaevents.databinding.ItemPopularEventCardBinding

class PopularEventsAdapter (private val dataSet: Array<Event>, private val clickListener: PopularEventItemClick)
    : RecyclerView.Adapter<PopularEventsAdapter.ViewHolder>() {

    interface PopularEventItemClick {
        fun onPopularEventItemClicked(event: Event)
    }

    inner class ViewHolder(private val binding: ItemPopularEventCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            //TODO
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPopularEventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}
