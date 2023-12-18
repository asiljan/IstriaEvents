package com.siljan.istriaevents.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.ItemEventCardBinding

class EventsAdapter(private val clickListener: EventItemClick) :
    RecyclerView.Adapter<EventsAdapter.ViewHolder>() {

    private var dataSet: List<Event> = emptyList()
    interface EventItemClick {
        fun onItemClicked(event: Event)
        fun onFavoriteIconClicked(eventId: String)
    }

    inner class ViewHolder(private val binding: ItemEventCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(event: Event, clickListener: EventItemClick) {

                binding.itemEventTitle.text = event.eventName
                binding.itemEventShortDescription.text = event.eventDescription

                if (event.isFavorite) {
                    binding.itemEventBtnFavorite.setImageResource(R.drawable.ic_favorite_checked)
                    binding.itemEventBtnFavorite.setColorFilter(itemView.context.resources.getColor(R.color.green_secondary))
                } else {
                    binding.itemEventBtnFavorite.setImageResource(R.drawable.ic_favorite_unchecked)
                    binding.itemEventBtnFavorite.setColorFilter(itemView.context.resources.getColor(R.color.dirt_white))
                }

                binding.itemEventBtnFavorite.setOnClickListener { clickListener.onFavoriteIconClicked(event.eventId) }
                binding.itemEventHolder.setOnClickListener { clickListener.onItemClicked(event) }
            }
    }

    fun updateDataSet(data: List<Event>) {
        dataSet = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEventCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.bind(dataSet[position], clickListener)

    }
}