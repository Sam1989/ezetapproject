package com.ds.ezetapproject.webservice;

import com.ds.ezetapproject.model.DataResponse

import com.google.gson.reflect.TypeToken

object ApiRegister {

    private const val API_LIVE_URL = "https://demo.ezetap.com/"
    const val BASE_URL = API_LIVE_URL

    const val fetchCustomUI = "mobileapps/android_assignment.json"

    fun getApiRequestType(url: String): ApiRequestType {

        val result = ApiRequestType()

        when (url) {

            fetchCustomUI -> {
                result.responseType =
                    object : TypeToken<DataResponse>() {}.type
                result.url = BASE_URL + fetchCustomUI
                result.requestType = RequestType.GET
                return result
            }
        }
        throw IllegalStateException("API is not registered")
    }
}