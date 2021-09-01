package com.lesson.foodamy.model

import com.google.gson.annotations.SerializedName

data class ResponseUser (

    @SerializedName("token") var token : String,
    @SerializedName("user") var user : UserInfo

)