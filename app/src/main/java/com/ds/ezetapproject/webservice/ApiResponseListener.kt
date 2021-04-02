package com.ds.ezetapproject.webservice;


import com.ds.ezetapproject.model.DataResponse



interface ApiResponseListener {

    fun onApiCallSuccess(apiUrl: String, body:DataResponse): Boolean // MasterResponse<*>

    fun onApiCallFailed(apiUrl: String, status: String, errorMessage: String): Boolean
}