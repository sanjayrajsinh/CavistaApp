package com.cavista.cavistaapp.webservice

import com.google.gson.annotations.SerializedName

/*
**  Created by Sanjay.Sisodiya on 5/11/18
**  Solution Analysts 
*/

data class Response(
        @field:SerializedName("message")
        val message: String) {
}