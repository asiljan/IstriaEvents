package com.siljan.istriaevents.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.siljan.domain.models.Event
import com.siljan.istriaevents.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = EventsAdapter(arrayOf(
            Event(
                eventName = "Some Crazy Event 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Some Crazy Event 2",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Some Crazy Event 3",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Some Crazy Event 4",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
        ), object : EventsAdapter.EventItemClick {
            override fun onItemClicked(event: Event) {
                Toast.makeText(requireContext(), "${event.eventName} - favorite?: ${event.isFavorite}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.eventsList.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}