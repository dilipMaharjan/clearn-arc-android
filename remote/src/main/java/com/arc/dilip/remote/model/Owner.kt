package com.arc.dilip.remote.model

import com.google.gson.annotations.SerializedName

class Owner(@SerializedName("login") val onwerName: String,
            @SerializedName("avatar_url") val ownerAvatar: String)