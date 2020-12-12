package se.com.keyflow.data.api

import io.reactivex.Single
import retrofit2.http.*
import se.com.keyflow.entities.Response


interface EventsApi {
    @GET("events")
    fun postDetail(
        @Query("long") long: Double,
        @Query("lat") lat: Double,
        @Query("placeId") placeId: String,
        @Query("locationRadius") locationRadius: Double,
        @Query("pageNumber") pageNumber: Int,
        @Query("pageSize") pageSize: Int

    ): Single<Response>
}
