package com.siljan.istriaevents.ui.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siljan.domain.models.Notification
import com.siljan.istriaevents.databinding.ItemNotificationBinding

class NotificationsAdapter (private val dataSet: Array<Notification>, val clickListener: NotificationClickListener) : RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    interface NotificationClickListener {
        fun onNotificationItemClicked(notification: Notification)
    }

    inner class ViewHolder(private val binding: ItemNotificationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataSet.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }
}