package com.taoc.verenime.api.responses

import com.google.gson.annotations.SerializedName

data class SearchAnimeResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: List<AnimeResponse>,
)
