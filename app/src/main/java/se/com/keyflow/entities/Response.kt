package se.com.keyflow.entities


import kotlinx.serialization.Serializable

@Serializable
data class Response(
    var status: Int,
    var data: Data
)
