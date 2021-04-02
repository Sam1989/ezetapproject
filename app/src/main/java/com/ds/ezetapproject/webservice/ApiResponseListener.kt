package com.ds.ezetapproject.webservice;

import com.ds.myapplication.model.MasterResponse
import com.ds.myapplication.model.NearByRestResponse


interface ApiResponseListener {

    fun onApiCallSuccess(apiUrl: String, body:NearByRestResponse): Boolean // MasterResponse<*>

    fun onApiCallFailed(apiUrl: String, status: String, errorMessage: String): Boolean
}