package se.com.keyflow.ui.mainFragment

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.serialization.UnstableDefault
import se.com.keyflow.data.EventsRepository
import se.com.keyflow.data.api.EventsApi
import se.com.keyflow.entities.Event
import se.com.keyflow.entities.Venues
import se.com.keyflow.util.applyNetworkSchedulers
import timber.log.Timber


class MainEventsListViewModel(val repo: EventsRepository, val api: EventsApi) : ViewModel() {

    private val _event: MutableLiveData<List<Event>> = MutableLiveData()
    var eventsDetails: MutableLiveData<List<Event>> = _event

    private val _venue: MutableLiveData<List<Venues>> = MutableLiveData()
    var venuesDetails: MutableLiveData<List<Venues>> = _venue


    @UnstableDefault
    @SuppressLint("CheckResult")
    fun getEventsDetail() {
        api.postDetail(
            18.07379500000002, 59.33539270000001,
            "ChIJywtkGTF2X0YRZnedZ9MnDag",
            20.0,
            1,
            50
        ).applyNetworkSchedulers()
            .subscribeBy(
                onSuccess = {
                    _event.postValue(it.data.events)
                    _venue.postValue(it.data.venues)
                    Timber.d((it.data.events).toString())
                },
                onError = { Timber.e(it) }
            )
    }

}
