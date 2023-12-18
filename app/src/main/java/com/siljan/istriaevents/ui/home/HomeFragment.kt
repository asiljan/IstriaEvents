package com.siljan.istriaevents.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), EventsAdapter.EventItemClick,
    BaseView<EventsIntent, EventsUIState> {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    private lateinit var horizontalLayoutManager: LinearLayoutManager

    private val eventsAdapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]

        horizontalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val popularEventsAdapter = PopularEventsAdapter(arrayOf(
            Event(
                eventId = "123412121",
                eventName = "Craazy Popular Event 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventId = "123412121",
                eventName = "Craazy Popular Event 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventId = "123412121",
                eventName = "Craazy Popular Event 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            )
        ), object : PopularEventsAdapter.PopularEventItemClick {
            override fun onPopularEventItemClicked(event: Event) {
                TODO("Not yet implemented")
            }
        })

        with(binding) {
            homePopularEventsList.layoutManager = horizontalLayoutManager
            homePopularEventsList.adapter = popularEventsAdapter
            eventsList.adapter = eventsAdapter
        }

        viewModel.states().observe(viewLifecycleOwner) { render(it) }

        viewModel.processIntent(EventsIntent.FetchAllEvents)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(event: Event) {
        findNavController().navigate(R.id.action_nav_home_to_eventDetailsFragment)
    }

    override fun onFavoriteIconClicked(eventId: String) {
        Toast.makeText(
            requireContext(),
            "EVENT: $eventId - favorite toggled",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun render(state: EventsUIState) {
        when (state) {
            is EventsUIState.EventsFetched -> {
                binding.eventsIndeterminateBar.visibility = View.GONE
                eventsAdapter.updateDataSet(state.data)
            }

            EventsUIState.EventsFetching -> binding.eventsIndeterminateBar.visibility = View.VISIBLE
            EventsUIState.EventsFetchingError -> {
                binding.eventsIndeterminateBar.visibility = View.GONE
            }
        }
    }
}