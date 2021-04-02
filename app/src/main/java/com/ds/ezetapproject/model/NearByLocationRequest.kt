package com.ds.myapplication.model

import com.google.gson.annotations.SerializedName

data class NearByLocationRequest(

    @field:SerializedName("location")
    var location: String = "47.6204,-122.3491",

    @field:SerializedName("radius")
    var radius: String = "2500",

    @field:SerializedName("type")
    var type: String = "restaurant",

    @field:SerializedName("key")
    var key: String = "AIzaSyD0AQBJ_BwInY5Tv_0tqGPJIWL7FcllnH0",


    @field:SerializedName("keyword")
    var keyword: String = ""

)