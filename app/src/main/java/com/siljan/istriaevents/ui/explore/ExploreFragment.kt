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
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.FragmentExploreBinding
import com.siljan.istriaevents.ui.home.EventsAdapter
import com.siljan.istriaevents.ui.models.EventsFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExploreFragment : Fragment(), /*ChipGroup.OnCheckedStateChangeListener*/
    OnItemSelectedListener,
    EventsAdapter.EventItemClick, FilterEventsDialogFragment.EventsDialogListener {

    private var _binding: FragmentExploreBinding? = null
    private val binding: FragmentExploreBinding get() = _binding!!

    private val exploreEventsAdapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    private var allEventsList: List<Event> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExploreBinding.inflate(inflater, container, false)

        binding.exploreCityFilterSpinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterEventsBtnFilter.setOnClickListener {
            FilterEventsDialogFragment().show(childFragmentManager, "filter_dialog")
        }

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.explore_cities_input,
            R.layout.spinner_item
        )

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        binding.exploreCityFilterSpinner.adapter = adapter

//            binding.exploreCityFilterSpinner.setSelection(0)
//            binding.exploreCitiesChipsFilterGroup.clearCheck()

        //TODO update this - use data layer and repository to fetch real data
        allEventsList = listOf(
            Event(
                eventName = "Event name 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 2",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 3",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 4",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 5",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 6",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Event name 7",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            )
        )

        binding.exploreEventsList.adapter = exploreEventsAdapter

        CoroutineScope(Dispatchers.Main).launch {
            binding.exploreIndeterminateBar.visibility = View.VISIBLE
            delay(1000)
            binding.exploreIndeterminateBar.visibility = View.GONE
            exploreEventsAdapter.updateDataSet(allEventsList)
            binding.exploreCitiesFilterResultsLabel.text = "${allEventsList.size} events found"
        }
    }

    override fun onConfirmClicked(filter: EventsFilter) {
        //TODO fetch currently selected city and call viewModel in order to start fetching data
        tempLoadFilteredEvents()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.exploreCityFilterSpinner.onItemSelectedListener = null
        _binding = null
    }

    private fun tempLoadFilteredEvents() {
        val filteredEventsList = listOf(
            Event(
                eventName = "Filtered event",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Filtered event 2",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventName = "Filtered event 3",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            )
        )
        CoroutineScope(Dispatchers.Main).launch {
            binding.exploreIndeterminateBar.visibility = View.VISIBLE
            delay(1000)
            binding.exploreIndeterminateBar.visibility = View.GONE
            exploreEventsAdapter.updateDataSet(filteredEventsList)
            binding.exploreCitiesFilterResultsLabel.text = "${filteredEventsList.size} events found"
        }
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
        //TODO call viewModel for query the events, if there is no both filters selected, then return INIT state
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
}