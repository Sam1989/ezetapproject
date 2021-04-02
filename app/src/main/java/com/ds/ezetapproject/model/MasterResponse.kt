package com.ds.myapplication.model

import com.google.gson.annotations.SerializedName


data class MasterResponse<Any>(

    @field:SerializedName("html_attributions")
    var html_attributions: Any? = null,

    @field:SerializedName("next_page_token")
    var next_page_token: String = "",

    @field:SerializedName("results")
    var results: Any? = null,

    @field:SerializedName("status")
    var status: String = ""


)