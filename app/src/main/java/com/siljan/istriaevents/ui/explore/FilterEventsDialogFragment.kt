package com.siljan.istriaevents.ui.explore

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.DialogFilterEventsBinding
import com.siljan.istriaevents.ui.models.EventsFilter

class FilterEventsDialogFragment : DialogFragment(R.layout.dialog_filter_events) {

    internal interface EventsDialogListener {
        fun onConfirmClicked(filter: EventsFilter)
    }

    private var _binding: DialogFilterEventsBinding? = null
    private var listener: EventsDialogListener? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogFilterEventsBinding.inflate(inflater, container, false)

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.now())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.filterEventsApplyButton.setOnClickListener {
            val chipDate = getSelectedDateChip(binding.filterEventsChipsFilterGroup.checkedChipId)

            val dateFilter = if (chipDate == null)
                EventsFilter.DateFilter.DateRange(323232323, 23242334232)
            else
                EventsFilter.DateFilter.getFilter(chipDate)


            listener?.onConfirmClicked(
                EventsFilter(
                    cityId = "123",
                    includePaidEvents = false,
                    date = dateFilter
                )
            )
            this.dismiss()
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
        _binding = null
    }
}