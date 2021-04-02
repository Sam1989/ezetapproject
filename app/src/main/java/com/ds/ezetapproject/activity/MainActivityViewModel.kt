package com.ds.ezetapproject.activity

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.ds.ezetapproject.base.AsyncViewController
import com.ds.ezetapproject.base.BaseViewModel
import com.ds.myapplication.model.MasterResponse
import com.ds.myapplication.model.NearByLocationRequest
import com.ds.myapplication.model.NearByRestResponse


class MainActivityViewModel(controller: AsyncViewController) : BaseViewModel(controller) {

    val requestNearByLocationRequest = ObservableField<NearByLocationRequest>()

    val responseNearByRestaurantResponse = MutableLiveData<NearByRestResponse>() //MasterResponse<Any>

    init {
        requestNearByLocationRequest.set(NearByLocationRequest())
    }


    fun callGetNearByRestaurantsListApi() {

    }

    fun callGetNearByRestaurantKeywordApi() {

    }

}