package com.example.photogalery.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class PhotoUrl(@SerializedName("full")  val full: String,
                    @SerializedName("regular")  val regular: String)
