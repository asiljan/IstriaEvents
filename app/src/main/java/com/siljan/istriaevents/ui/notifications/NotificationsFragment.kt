package com.siljan.istriaevents.ui.notifications

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.siljan.domain.models.Notification
import com.siljan.istriaevents.R
import com.siljan.istriaevents.databinding.FragmentNotificationsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

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

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NotificationsAdapter(
            arrayOf(
                Notification(
                    name = "Some happening notification info 1",
                    date = 112243254252
                ),
                Notification(
                    name = "Some happening notification info 2",
                    date = 112243254252
                ),
                Notification(
                    name = "Some happening notification info 3",
                    date = 112243254252
                ),
                Notification(
                    name = "Some happening notification info 4",
                    date = 112243254252
                ),
                Notification(
                    name = "Some happening notification info 5",
                    date = 112243254252
                ),
                Notification(
                    name = "Some happening notification info 6",
                    date = 112243254252
                )
            ),
            object : NotificationsAdapter.NotificationClickListener {
                override fun onNotificationItemClicked(notification: Notification) {
                    //TODO
                }
            }
        )

        binding.notificationsList.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotificationsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}