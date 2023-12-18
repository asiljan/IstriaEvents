package com.siljan.istriaevents.ui.explore

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.common.formatDate
import com.siljan.istriaevents.databinding.FragmentExploreBinding
import com.siljan.istriaevents.ui.home.EventsAdapter
import com.siljan.istriaevents.ui.home.EventsIntent
import com.siljan.istriaevents.ui.home.EventsUIState
import com.siljan.istriaevents.ui.home.EventsViewModel
import com.siljan.istriaevents.ui.models.EventsFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(), EventsAdapter.EventItemClick,
    FilterEventsDialogFragment.EventsDialogListener, BaseView<EventsIntent, EventsUIState> {

    private var _binding: FragmentExploreBinding? = null
    private val binding: FragmentExploreBinding get() = _binding!!

    private val exploreEventsAdapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    private lateinit var viewModel: EventsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this)[EventsViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterEventsBtnFilter.setOnClickListener {
            FilterEventsDialogFragment().show(childFragmentManager, "filter_dialog")
        }

        binding.filterEventsClearFilters.setOnClickListener {
            binding.exploreEventsSelectedFiltersGroup.removeAllViews()
            binding.exploreCitiesHorizontalDivider.updateLayoutParams<ConstraintLayout.LayoutParams> {
                topToBottom = binding.filterEventsBtnFilter.id
            }
            viewModel.processIntent(EventsIntent.FetchAllEvents)
            binding.filterEventsBtnFilter.visibility = View.VISIBLE
            it.visibility = View.GONE
        }

        binding.exploreEventsList.adapter = exploreEventsAdapter

        viewModel.states().observe(viewLifecycleOwner) { render(it) }

        viewModel.processIntent(EventsIntent.FetchAllEvents)
    }

    override fun onConfirmClicked(filter: EventsFilter) {
        if (filter.cityName.isNotEmpty())
            binding.exploreEventsSelectedFiltersGroup.addView(
                createChip(
                    requireContext(),
                    filter.cityName
                )
            )

        if(!filter.includePaidEvents) {
            binding.exploreEventsSelectedFiltersGroup.addView(
                createChip(requireContext(), "Free events")
            )
        }

        val dateChipValue = if (filter.date.dateFilter == EventsFilter.DateFilter.DateRange)
            "${filter.date.range.first.formatDate()} - ${filter.date.range.second.formatDate()}"
        else
            filter.date.dateFilter.value

        binding.exploreEventsSelectedFiltersGroup.addView(createChip(requireContext(), dateChipValue))

        binding.exploreCitiesHorizontalDivider.updateLayoutParams<ConstraintLayout.LayoutParams> {
            topToBottom = binding.filterEventsClearFilters.id
        }

        binding.filterEventsBtnFilter.visibility = View.GONE
        binding.filterEventsClearFilters.visibility = View.VISIBLE

        viewModel.processIntent(EventsIntent.FetchEvents(filter))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(event: Event) {
        findNavController().navigate(R.id.action_nav_explore_to_eventDetailsFragment)
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
                with(binding) {
                    exploreIndeterminateBar.visibility = View.GONE
                    exploreCitiesFilterResultsLabel.text = String.format(getString(R.string.events_label_found_events), state.data.size)
                    exploreEventsAdapter.updateDataSet(state.data)
                }
            }

            EventsUIState.EventsFetching -> binding.exploreIndeterminateBar.visibility =
                View.VISIBLE

            EventsUIState.EventsFetchingError -> {
                with(binding) {
                    exploreCitiesFilterResultsLabel.text = "--"
                    exploreIndeterminateBar.visibility = View.GONE
                }
            }
        }
    }

    private fun createChip(context: Context, chipName: String): Chip = Chip(context).apply {
        text = chipName
        chipBackgroundColor =
            ColorStateList.valueOf(ContextCompat.getColor(context, R.color.green_secondary))
    }
}