package com.ds.ezetapproject.webservice;

import com.ds.myapplication.model.NearByRestResponse
import com.google.gson.reflect.TypeToken

object ApiRegister {

    private const val API_LIVE_URL = "https://maps.googleapis.com/maps/"
    const val BASE_URL = API_LIVE_URL

    const val nearBySearchList = "api/place/nearbysearch/json"
    const val nearBySearchKeyword = "api/place/nearbysearch/json"

    fun getApiRequestType(url: String): ApiRequestType {

        val result = ApiRequestType()

        when (url) {

            nearBySearchList -> {
                result.responseType =
                    object : TypeToken<NearByRestResponse>() {}.type
                result.url = BASE_URL + nearBySearchList
                result.requestType = RequestType.GET
                return result
            }

            nearBySearchKeyword -> {
                result.responseType =
                    object : TypeToken<NearByRestResponse>() {}.type
                result.url = BASE_URL + nearBySearchKeyword
                result.requestType = RequestType.GET
                return result
            }

        }
        throw IllegalStateException("API is not registered")
    }
}