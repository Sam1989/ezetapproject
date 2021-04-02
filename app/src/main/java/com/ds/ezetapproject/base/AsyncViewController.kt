package com.ds.ezetapproject.base

import android.content.DialogInterface
import com.ds.myapplication.model.MasterResponse
import com.ds.myapplication.model.NearByRestResponse


interface AsyncViewController {

    fun showProgressDialog()

    fun hideProgressDialog()

    fun showAlertDialog(msg: String, btnListener: DialogInterface.OnClickListener?)

    fun hideAlertDialog()

    fun hideKeyboard()

    fun showKeyboard()

    fun onNoInternet()

}