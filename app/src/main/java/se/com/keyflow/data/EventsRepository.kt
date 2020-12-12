package se.com.keyflow.data

import android.content.Context
import se.com.keyflow.data.api.EventsApi

class EventsRepository(
    val application: Context,
    private val api: EventsApi
) {


//    fun postInfo(lat: Double, location: Double, long: Double, pageNumber: Int, placeId: String,num:Int) {
//        api.postDetail(long, lat, placeId, location, pageNumber,num)
//    }


}
