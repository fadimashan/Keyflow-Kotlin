package se.com.keyflow.entities

import kotlinx.serialization.Serializable

@Serializable
data class Venues(
    val venueId: Int,
    val venueName: String
)
