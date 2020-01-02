package com.example.test.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Money(

    @SerializedName("flow")
    @ColumnInfo(name = "flow")
    var flow: Int?,

    @SerializedName("cost")
    @ColumnInfo(name = "cost")
    var cost: Float?,

    @SerializedName("time")
    @ColumnInfo(name = "time")
    var time: Float?,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String?) {

    @PrimaryKey(autoGenerate = true)
    var _uuid: Int = 0
}
