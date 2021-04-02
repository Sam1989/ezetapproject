package com.ds.ezetapproject.base

import com.ds.ezetapproject.webservice.RestClient


open class BaseRepository(asyncViewController: AsyncViewController?) {

    val restClient: RestClient = RestClient()

    init {
        restClient.asyncViewController = asyncViewController
    }
}