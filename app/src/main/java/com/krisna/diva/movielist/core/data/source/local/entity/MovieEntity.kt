package com.krisna.diva.movielist.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Parcelize
@Entity(tableName = "favorite_movie")
data class MovieEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "title")
    var title: String? = null,

    @ColumnInfo(name = "rating")
    var rating: Double? = null,

    @ColumnInfo(name = "genres")
    var genres: List<String>? = null,

    @ColumnInfo(name = "overview")
    var overview: String? = null,

) : Parcelable

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return Gson().toJson(list)
    }
}