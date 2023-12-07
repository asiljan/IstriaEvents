package com.siljan.istriaevents.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseView
import com.siljan.istriaevents.databinding.FragmentExploreBinding
import com.siljan.istriaevents.ui.home.EventsAdapter
import com.siljan.istriaevents.ui.home.EventsIntent
import com.siljan.istriaevents.ui.home.EventsUIState
import com.siljan.istriaevents.ui.home.EventsViewModel
import com.siljan.istriaevents.ui.models.EventsFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : Fragment(), OnItemSelectedListener, EventsAdapter.EventItemClick,
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

        binding.exploreCityFilterSpinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterEventsBtnFilter.setOnClickListener {
            FilterEventsDialogFragment().show(childFragmentManager, "filter_dialog")
        }

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.explore_cities_input,
            R.layout.spinner_item
        ).apply {
            setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.exploreCityFilterSpinner.adapter = this
        }

//            binding.exploreCityFilterSpinner.setSelection(0)
//            binding.exploreCitiesChipsFilterGroup.clearCheck()

        binding.exploreEventsList.adapter = exploreEventsAdapter

        viewModel.states().observe(viewLifecycleOwner) { render(it) }

        viewModel.processIntent(EventsIntent.FetchAllEvents)
    }

    override fun onConfirmClicked(filter: EventsFilter) {
        viewModel.processIntent(EventsIntent.FetchEvents(filter))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.exploreCityFilterSpinner.onItemSelectedListener = null
        _binding = null
    }

    /**
     * Spinner callback which triggers when one of the possible entries are selected
     */
    override fun onItemSelected(
        p0: AdapterView<*>?,
        selectedItemView: View?,
        position: Int,
        id: Long
    ) {
        //TODO move spinner to the DialogFragment
//        val citySelected = binding.exploreCityFilterSpinner.selectedItem
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

    override fun onItemClicked(event: Event) {
        Toast.makeText(
            requireContext(),
            "${event.eventName} - favorite?: ${event.isFavorite}",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun render(state: EventsUIState) {
        when (state) {
            is EventsUIState.EventsFetched -> {
                binding.exploreIndeterminateBar.visibility = View.GONE
                exploreEventsAdapter.updateDataSet(state.data)
            }

            EventsUIState.EventsFetching -> binding.exploreIndeterminateBar.visibility =
                View.VISIBLE

            EventsUIState.EventsFetchingError -> {
                binding.exploreIndeterminateBar.visibility = View.GONE
            }
        }
    }
}