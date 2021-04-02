package com.ds.ezetapproject.activity

import androidx.lifecycle.MutableLiveData
import com.ds.ezetapproject.base.AsyncViewController
import com.ds.ezetapproject.base.BaseViewModel
import com.ds.ezetapproject.model.DataResponse
import com.ds.ezetapproject.webservice.ApiRegister

class MainActivityViewModel(controller: AsyncViewController) : BaseViewModel(controller) {

    val responseDataResponse = MutableLiveData<DataResponse>()

    fun callFetchCustomUIApi() {
        baseRepo.restClient.callPclApi(
            ApiRegister.fetchCustomUI,
            responseDataResponse,
            true
        )
    }
}