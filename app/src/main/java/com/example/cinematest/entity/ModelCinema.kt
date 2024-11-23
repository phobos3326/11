package com.example.cinematest.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ModelCinema(
    @Json(name = "films")
    var films: List<Film>
) {
    @JsonClass(generateAdapter = true)
    data class Film(
        @Json(name = "description")
        var description: String?,
        @Json(name = "genres")
        var genres: List<String?>?,
        @Json(name = "id")
        var id: Int?,
        @Json(name = "image_url")
        var imageUrl: String?,
        @Json(name = "localized_name")
        var localizedName: String?,
        @Json(name = "name")
        var name: String?,
        @Json(name = "rating")
        var rating: Double?,
        @Json(name = "year")
        var year: Int?
    )
}