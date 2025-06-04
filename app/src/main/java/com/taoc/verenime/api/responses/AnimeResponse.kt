package com.taoc.verenime.api.responses

import com.google.gson.annotations.SerializedName

data class AnimeListResponse(
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("statusMessage") val statusMessage: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("ok") val ok: Boolean,
    @SerializedName("data") val data: AnimeListData?,
    @SerializedName("pagination") val pagination: PaginationResponse?
)

data class AnimeListData(
    @SerializedName("animeList") val animeList: List<AnimeResponse>?
)

data class AnimeResponse(
    @SerializedName("title") val title: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("releaseDay") val releaseDay: String?,
    @SerializedName("latestReleaseDate") val latestReleaseDate: String?,
    @SerializedName("animeId") val animeId: String,
    @SerializedName("href") val href: String?,
    @SerializedName("otakudesuUrl") val otakudesuUrl: String?,
)

data class PaginationResponse(
    @SerializedName("currentPage") val currentPage: Int,
    @SerializedName("hasPrevPage") val hasPrevPage: Boolean,
    @SerializedName("prevPage") val prevPage: Int?,
    @SerializedName("hasNextPage") val hasNextPage: Boolean,
    @SerializedName("nextPage") val nextPage: Int?,
    @SerializedName("totalPages") val totalPages: Int
)