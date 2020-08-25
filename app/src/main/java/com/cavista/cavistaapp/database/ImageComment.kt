package com.cavista.cavistaapp.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "ImageComment")
data class ImageComment(
        @PrimaryKey(autoGenerate = true)
        var uid: Int = 0,
        @field:SerializedName("imageID")
        var imageID: String? = null,
        @field:SerializedName("comment")
        var comment: String? = null,

) : Parcelable