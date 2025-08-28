package ru.pk.neuronetestapp.data.dto

import com.google.gson.annotations.SerializedName

data class Data(
    val date: String,
    @SerializedName ( "name") val namesList: List<String>
)