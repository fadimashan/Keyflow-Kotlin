package se.com.keyflow.ui.mainFragment

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_event_list_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import se.mobileinteraction.keyflow.R


class MainEventsListFragment : Fragment(R.layout.main_event_list_fragment) {

    private val viewModel by viewModel<MainEventsListViewModel>()

    private val eventAdapter: EventAdapter = EventAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setup the recycler view with divider between the items
        main_events_list.apply {
            adapter = eventAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

            val divider =
                ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            val itemDecoration = DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
            itemDecoration.setDrawable(divider!!).apply {
                setPadding(2, 0, 2, 0)
            }
        }

        viewModel.getEventsDetail()
    }

    // observer for the changes in the list of event and the list of venues names
    private fun observerData() {
        viewModel.eventsDetails.observe(viewLifecycleOwner, Observer {
            eventAdapter.setData(it)
        })
        viewModel.venuesDetails.observe(viewLifecycleOwner, Observer {
            eventAdapter.setVenuesData(it)

        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observerData()
    }
}
