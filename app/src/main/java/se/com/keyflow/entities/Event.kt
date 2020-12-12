package se.com.keyflow.entities

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    var venueId: Int,
    var endTime: String,
    var startTime: String,
    val images: List<String?>,
    val name: String
)
