package com.arc.dilip.remote.model

import com.google.gson.annotations.SerializedName

class Project(val id: String, val name: String,
              @SerializedName("full_name") val fullName: String,
              @SerializedName("stargazers_count") val starCount: Int,
              @SerializedName("created_at") val dateCreated: String,
              val owner: Owner)