package com.siljan.istriaevents.ui.explore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.siljan.domain.models.Event
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.FragmentExploreBinding
import com.siljan.istriaevents.ui.home.EventsAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment(), ChipGroup.OnCheckedStateChangeListener, OnItemSelectedListener,
    EventsAdapter.EventItemClick {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentExploreBinding? = null
    private val binding: FragmentExploreBinding get() = _binding!!

    private val exploreEventsAdapter: EventsAdapter by lazy {
        EventsAdapter(this)
    }

    private var allEventsList: List<Event> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentExploreBinding.inflate(inflater, container, false)


        binding.exploreCitiesChipsFilterGroup.setOnCheckedStateChangeListener(this)
        binding.exploreCityFilterSpinner.onItemSelectedListener = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.explore_cities_input,
            R.layout.spinner_item
        )

        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item)

        binding.exploreCityFilterSpinner.adapter = adapter

        binding.exploreCitiesFilterClear.setOnClickListener {
            binding.exploreCityFilterSpinner.setSelection(0)
            binding.exploreCitiesChipsFilterGroup.clearCheck()
            //TODO remove this!! it is for testing purposes only
            CoroutineScope(Dispatchers.Main).launch {
                binding.exploreIndeterminateBar.visibility = View.VISIBLE
                delay(1500)
                binding.exploreIndeterminateBar.visibility = View.GONE
                exploreEventsAdapter.updateDataSet(allEventsList)
                binding.exploreCitiesFilterResultsLabel.text = "${allEventsList.size} events found"
            }

        }

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
            delay(2500)
            binding.exploreIndeterminateBar.visibility = View.GONE
            exploreEventsAdapter.updateDataSet(allEventsList)
            binding.exploreCitiesFilterResultsLabel.text = "${allEventsList.size} events found"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.exploreCitiesChipsFilterGroup.setOnCheckedStateChangeListener(null)
        binding.exploreCityFilterSpinner.onItemSelectedListener = null
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /**
     * ChipGroup.OnCheckedStateChangeListener
     */
    override fun onCheckedChanged(group: ChipGroup, checkedIds: MutableList<Int>) {
        //TODO call viewModel for query the events, if there is no both filters selected, then return INIT state
        val dateFilter = if (checkedIds.isNotEmpty()) getDateFilter(checkedIds[0]) else null

        if (dateFilter != null && !binding.exploreCityFilterSpinner.selectedItem.equals("--")) {
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
        val citySelected = binding.exploreCityFilterSpinner.selectedItem
        val dateSelected = getDateFilter(binding.exploreCitiesChipsFilterGroup.checkedChipId)

        Log.d(
            "TEST",
            "onItemSelected: Spinner selected item triggered: $citySelected - $dateSelected"
        )
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

    private fun getDateFilter(chipId: Int): String? =
        if (chipId != -1)
            binding.exploreCitiesChipsFilterGroup.findViewById<Chip>(chipId).text.toString()
        else null
}