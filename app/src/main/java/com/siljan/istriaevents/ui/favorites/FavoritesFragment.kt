package com.siljan.istriaevents.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.databinding.FragmentFavoritesBinding
import com.siljan.istriaevents.ui.home.EventsAdapter
import com.siljan.istriaevents.ui.home.EventsIntent
import com.siljan.istriaevents.ui.home.EventsUIState
import com.siljan.istriaevents.ui.home.EventsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), EventsAdapter.EventItemClick, BaseView<EventsIntent, EventsUIState> {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val eventsAdapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoritesList.adapter = eventsAdapter

        viewModel.states().observe(viewLifecycleOwner) {
            render(it)
        }

        viewModel.processIntent(EventsIntent.FetchFavoritesEvents)
    }

    override fun onItemClicked(event: Event) {
        //TODO
    }

    override fun render(state: EventsUIState) {
        if (state is EventsUIState.EventsFetched) {
            eventsAdapter.updateDataSet(state.data)
        }
    }
}