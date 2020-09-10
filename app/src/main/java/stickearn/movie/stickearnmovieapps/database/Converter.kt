package stickearn.movie.stickearnmovieapps.database

import androidx.room.TypeConverter
import com.google.gson.Gson


class Converters {

    @TypeConverter
    fun arrayListToJson(value: ArrayList<Int>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToArrayList(value: String) =
        Gson().fromJson(value, Array<Int>::class.java).toList()

}