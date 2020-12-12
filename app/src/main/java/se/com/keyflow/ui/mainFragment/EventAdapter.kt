package se.com.keyflow.ui.mainFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.item_event.view.*
import se.com.keyflow.entities.Event
import se.com.keyflow.entities.Venues
import se.com.keyflow.util.AspectRatioImageView
import se.com.keyflow.util.load
import se.mobileinteraction.keyflow.R
import java.time.LocalDateTime


class EventAdapter :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    var list = mutableListOf<Event>()
    var venueList = mutableListOf<Venues>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_event, parent, false
            )
        )

    fun setData(listOfEvent: List<Event>) {
        list.clear()
        list.addAll(listOfEvent)
        notifyDataSetChanged()

    }

    fun setVenuesData(listOfVenues: List<Venues>) {
        venueList.clear()
        venueList.addAll(listOfVenues)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val item = list[position]

        holder.bind(item)

        // check the current item venue ID and fetch the name from the venues list
        venueList.map {
            if (it.venueId == item.venueId)
                holder.itemView.txt_venue_name.text = it.venueName
        }
    }

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val eventName = itemView.findViewById<TextView>(R.id.txt_event_name)
        private val eventWeekDay = itemView.findViewById<TextView>(R.id.txt_week_day)
        private val eventMonthDay = itemView.findViewById<TextView>(R.id.txt_month_day)
        private val eventMonth = itemView.findViewById<TextView>(R.id.txt_month)
        private val eventTime = itemView.findViewById<TextView>(R.id.txt_event_endtime)
        private val eventImage: AspectRatioImageView =
            itemView.findViewById<AspectRatioImageView>(R.id.event_image)
        private val ImageUrl =
            "https://res.cloudinary.com/keyflow/image/upload/venue/204/event/2573/Venue_with_Connected_Account_event_New_EVENT5ccdfa3d170d488ef3e30ebdbb9a0efc.png"

        fun bind(event: Event) {
            eventName.text = event.name

            // view the date + time in the require format.
            val mainDate = event.startTime.dropLast(6)
            val dateTime = LocalDateTime.parse(mainDate)
            val day = dateTime.dayOfWeek
            val month = dateTime.month.toString().substring(0, 3)
            val startTimeHour = dateTime.hour
            val dayOfMonth = dateTime.dayOfMonth
            val mainEndDate = event.endTime.dropLast(6)
            val endDateTime = LocalDateTime.parse(mainEndDate)
            val endTimeHour = endDateTime.hour
            val endTimeMinute = if (endDateTime.minute < 10) {
                "0${endDateTime.minute}"
            } else {
                endDateTime.minute.toString()
            }
            val startTimeMinute = if (dateTime.minute < 10) {
                "0${dateTime.minute}"
            } else {
                dateTime.minute.toString()
            }
            val startTime = ("$startTimeHour:$startTimeMinute")
            val endTime = ("$endTimeHour:$endTimeMinute")
            eventMonth.text = month
            eventWeekDay.text = day.toString().substring(0, 3)
            eventTime.text = ("$startTime - $endTime")
            eventMonthDay.text = dayOfMonth.toString()

            //this is the date + time from the server "endTime": "2020-12-31T05:00:00+01:00",  "startTime": "2020-10-29T22:00:00+01:00"
            eventImage.setImageDrawable(null)

            if (event.images.isNullOrEmpty()) {
                eventImage.apply {
                    load(ImageUrl)
                }.aspectRatio = eventImage.height.toDouble() / eventImage.width.toDouble()
            } else {
                event.images.let {
                    eventImage.apply {
                        load("https://res.cloudinary.com/keyflow/image/upload/${it[0]}.png")
                        DiskCacheStrategy.RESOURCE
                    }
                }
            }
        }
    }

    override fun getItemCount() = list.size
}

