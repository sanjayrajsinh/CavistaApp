package com.cavista.cavistaapp.webservice

import com.google.gson.annotations.SerializedName

class ApiErrorModel {
        @SerializedName("status")
        var status: String? = null

        @SerializedName("message")
        var message: String = ""

        @SerializedName("error")
        var error: String? = null
}
