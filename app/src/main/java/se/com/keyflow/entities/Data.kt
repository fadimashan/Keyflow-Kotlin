package se.com.keyflow.entities

import kotlinx.serialization.Serializable

@Serializable
data class Data(
        val events: List<Event>,
        val venues: List<Venues>

)
