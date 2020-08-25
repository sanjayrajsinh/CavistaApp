package com.cavista.cavistaapp.appview.imagelist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data(
    var id: String? = "",
    var title: String? = "",
    var link: String? = "",
    var comment_count: Int? = 0,
    var images_count: Int? = 0,
    var in_gallery: Boolean? = false,
    var images: List<Image>? = listOf(),
    var type: String? = "",
    var looping: Boolean? = false,
) : Parcelable