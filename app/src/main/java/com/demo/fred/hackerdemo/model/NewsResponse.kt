package com.demo.fred.hackerdemo.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Data Model class for items
 * Mapping with the Json response from remote repository
 */
data class NewsResponse(
    @SerializedName("by")
    @Expose
    val by: String?,
    @SerializedName("descendants")
    @Expose
    val descendants: Int?,
    @SerializedName("id")
    @Expose
    val id: Int?,
    @SerializedName("kids")
    @Expose
    val kids: List<Int>?,
    @SerializedName("score")
    @Expose
    val score: Int?,
    @SerializedName("time")
    @Expose
    val time: Long?,
    @SerializedName("title")
    @Expose
    val title: String?,
    @SerializedName("type")
    @Expose
    val type: String?,
    @SerializedName(value = "url", alternate = ["text"])
    val url: String?
) : Serializable