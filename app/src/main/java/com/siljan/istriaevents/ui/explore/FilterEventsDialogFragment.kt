package com.siljan.istriaevents.ui.explore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.createCustomDateRangePicker
import com.siljan.istriaevents.databinding.DialogFilterEventsBinding
import com.siljan.istriaevents.ui.models.EventsFilter

class FilterEventsDialogFragment : DialogFragment(R.layout.dialog_filter_events),
    ChipGroup.OnCheckedStateChangeListener {

    internal interface EventsDialogListener {
        fun onConfirmClicked(filter: EventsFilter)
    }

    private var _binding: DialogFilterEventsBinding? = null
    private var listener: EventsDialogListener? = null
    private var dateRange: Pair<Long, Long> = Pair(0L, 0L)
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFilterEventsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterEventsChipsFilterGroup.setOnCheckedStateChangeListener(this)

        binding.filterEventsApplyButton.setOnClickListener {
            val chipDate = getSelectedDateChip(binding.filterEventsChipsFilterGroup.checkedChipId)

            val dateFilter = if (chipDate == null)
                EventsFilter.DateFilter.DateToday
            else EventsFilter.DateFilter.getFilter(chipDate)

            var selectedCity = binding.filterEventsCityFilterSpinner.selectedItem.toString()
            if (selectedCity == "--") selectedCity = ""

            listener?.onConfirmClicked(
                EventsFilter(
                    cityId = "123",
                    cityName = selectedCity,
                    includePaidEvents = binding.filterEventsFreePaidEventsSwitch.isChecked,
                    date = EventsFilter.CustomDate(dateFilter, dateRange)
                )
            )
            this.dismiss()
        }

        //TODO custom array adapter for data model class with city name and city ID fetched from the firebase
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.explore_cities_input,
            R.layout.spinner_item
        ).apply {
            setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.filterEventsCityFilterSpinner.adapter = this
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = parentFragment as? EventsDialogListener
    }

    private fun getSelectedDateChip(chipId: Int): String? {
        return if (chipId != -1)
            binding.filterEventsChipsFilterGroup.findViewById<Chip>(chipId).text.toString()
        else null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.filterEventsChipsFilterGroup.setOnCheckedStateChangeListener(null)
        _binding = null
    }

    override fun onCheckedChanged(group: ChipGroup, checkedIds: MutableList<Int>) {
        binding.filterEventsChipsFilterGroup.findViewById<Chip>(binding.filterEventsChipsFilterGroup.checkedChipId)
            ?.let {
                if (it.id == R.id.chipFilterCustom) {

                    val dialog = this.createCustomDateRangePicker(
                        "Select start and end date",
                        CalendarConstraints.Builder().setValidator(DateValidatorPointForward.now()).build()
                    )
                    dialog.addOnPositiveButtonClickListener { selection ->
                        dateRange = selection.first to selection.second
                    }
                    dialog.show(parentFragmentManager, "customDateRange")
                }
            }
    }
}