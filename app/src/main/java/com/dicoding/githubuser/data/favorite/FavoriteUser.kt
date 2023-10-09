package com.dicoding.githubuser.data.favorite

import androidx.room.Entity
import androidx.room.PrimaryKey
import retrofit2.http.Url
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class FavoriteUser(
    val login:String,
    @PrimaryKey
    val id:Int,
    val avatarUrl: String
):Serializable
