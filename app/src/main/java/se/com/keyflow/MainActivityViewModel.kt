package se.com.keyflow

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import se.com.keyflow.data.EventsRepository
import se.com.keyflow.data.api.EventsApi


class MainActivityViewModel(
    val context: Context, private val repo: EventsRepository, val api: EventsApi
) : ViewModel() {

}
