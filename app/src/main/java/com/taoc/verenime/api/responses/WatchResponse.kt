package com.taoc.verenime.api.responses

import com.google.gson.annotations.SerializedName

data class WatchResponse(

	@field:SerializedName("pagination")
	val pagination: Any? = null,

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("ok")
	val ok: Boolean? = null,

	@field:SerializedName("statusMessage")
	val statusMessage: String? = null,

	@field:SerializedName("statusCode")
	val statusCode: Int? = null
)

data class PrevEpisode(

	@field:SerializedName("otakudesuUrl")
	val otakudesuUrl: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("episodeId")
	val episodeId: String? = null
)

data class EpisodeListItem(

	@field:SerializedName("otakudesuUrl")
	val otakudesuUrl: String? = null,

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("title")
	val title: Int? = null,

	@field:SerializedName("episodeId")
	val episodeId: String? = null
)

data class QualitiesItem(

	@field:SerializedName("serverList")
	val serverList: List<Any?>? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("urls")
	val urls: List<UrlsItem?>? = null,

	@field:SerializedName("size")
	val size: String? = null
)

data class Server(

	@field:SerializedName("qualities")
	val qualities: List<QualitiesItem?>? = null
)

data class UrlsItem(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class ServerListItem(

	@field:SerializedName("href")
	val href: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("serverId")
	val serverId: String? = null
)

data class Info(

	@field:SerializedName("duration")
	val duration: String? = null,

	@field:SerializedName("episodeList")
	val episodeList: List<EpisodeListItem?>? = null,

	@field:SerializedName("credit")
	val credit: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("encoder")
	val encoder: String? = null
)

data class Data(

	@field:SerializedName("server")
	val server: Server? = null,

	@field:SerializedName("animeId")
	val animeId: String? = null,

	@field:SerializedName("releaseTime")
	val releaseTime: String? = null,

	@field:SerializedName("hasPrevEpisode")
	val hasPrevEpisode: Boolean? = null,

	@field:SerializedName("hasNextEpisode")
	val hasNextEpisode: Boolean? = null,

	@field:SerializedName("defaultStreamingUrl")
	val defaultStreamingUrl: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("nextEpisode")
	val nextEpisode: Any? = null,

	@field:SerializedName("prevEpisode")
	val prevEpisode: PrevEpisode? = null,

	@field:SerializedName("info")
	val info: Info? = null
)
