package com.ds.ezetapproject.base

import androidx.lifecycle.ViewModel

open class BaseViewModel(var controller: AsyncViewController?) : ViewModel() {
    var baseRepo: BaseRepository = BaseRepository(controller)
}