package com.taoc.verenime.api.responses

import com.google.gson.annotations.SerializedName

data class DetailAnimeResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: AnimeDetailData?
)

data class AnimeDetailData(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("poster") val poster: String,
    @SerializedName("type") val type: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("score") val score: String?,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("synopsis") val synopsis: Synopsis?,
    @SerializedName("episodes") val episodes: Int?,
    @SerializedName("episodeList") val episodeList: List<EpisodeResponse>?
)

data class Synopsis(
    @SerializedName("paragraphs") val paragraphs: List<String>?,
    @SerializedName("connections") val connections: List<Any>?
)

data class EpisodeResponse(
    @SerializedName("episodeId") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("date") val date: String? = null
)