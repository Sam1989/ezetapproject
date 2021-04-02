package com.ds.ezetapproject.webservice;

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.MutableLiveData
import com.blankj.utilcode.util.ToastUtils
import com.ds.ezetapproject.R

import com.ds.ezetapproject.base.AsyncViewController
import com.ds.ezetapproject.base.MainApplication
import com.ds.myapplication.model.NearByLocationRequest
import com.ds.myapplication.model.NearByRestResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.intuit.sdp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit


class RestClient {

    var requestNearByLocationRequest = NearByLocationRequest()
    private var apiInterface: ApiInterface?
    var asyncViewController: AsyncViewController? = null
    var apiResponseListener: ApiResponseListener? = null
    private val activeApiCalls = ArrayList<Call<*>>()
    private val CONNECTION_TIMEOUT = 300
    var gson: Gson


    init {
        apiInterface = getApiInterface()
        requestNearByLocationRequest = NearByLocationRequest()

        gson = Gson()

    }


    /**
     * provides retrofit client with proxy implemented api interface
     *
     * @return
     */
    private fun getApiInterface(): ApiInterface? {

        if (apiInterface == null) {
            val client = getOkHttpClient() ?: return null
            val gson = GsonBuilder().setLenient().create()
            val builder = Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
            builder.baseUrl(ApiRegister.BASE_URL)
            return builder.build().create(ApiInterface::class.java)
        } else {
            return apiInterface
        }
    }


    /**
     * get OkHttpClient
     *
     * @return OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient? {
        try {
            val okClientBuilder = OkHttpClient.Builder()

            okClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            okClientBuilder.readTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            okClientBuilder.writeTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                okClientBuilder.addInterceptor(httpLoggingInterceptor)
            }

            return okClientBuilder.build()
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }


    /**
     * format error string here
     *
     * @param rawError
     * @return
     */
    private fun getError(rawError: String?): String {
        if (rawError == null) {
            return "Error Occurred"
        }
        val formulatedError = MainApplication.get().getContext()
            .getString(R.string.bad_response)
        return if (rawError.contains("JsonReader.setLenient")) {
            formulatedError
        } else if (rawError.contains("Unable to resolve host")) {
            "Couldn't connect to server"
        } else
            rawError
    }


    /**
     * checks network connectivity
     *
     * @return
     */
    private fun isConnectedToNetwork(): Boolean {
        val cm =
            MainApplication.get().getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return ni != null
    }


    //showProgressDialog while api call is active
    private fun showProgressDialog() {
        asyncViewController?.showProgressDialog()
    }


    // hide progress after api calling
    private fun hideProgressDialog() {
        asyncViewController?.hideProgressDialog()
    }


    /**
     *  common checks before any api calling
     * @param String
     * @return
     */
    private fun passChecks(): Boolean {
        if (!isConnectedToNetwork()) {
            asyncViewController?.onNoInternet()
            return false
        }
        return apiInterface != null
    }

    fun getListener(): ApiResponseListener? {
        return apiResponseListener
    }


    fun callPclApi(
        type: String,
        requestPojo: Any?,
        dataCarrier: MutableLiveData<*>?,
        showProgressDialog: Boolean,
        callType: String
    ) {

        if (!passChecks()) {
            return
        }

        val apiRequestType = ApiRegister.getApiRequestType(type)

        var call: Call<ResponseBody>? = null

        when {
            apiRequestType.requestType === RequestType.GET -> {
                when {
                    apiRequestType.url.contains(ApiRegister.nearBySearchList) -> {

                        if (callType != "Search") {
                            requestNearByLocationRequest = requestPojo as NearByLocationRequest
                            var url =
                                apiRequestType.url + "?location=" + requestNearByLocationRequest.location + "&radius=" + requestNearByLocationRequest.radius + "&type=" +
                                        requestNearByLocationRequest.type + "&key=" + requestNearByLocationRequest.key
                            call = getApiInterface()!!.getApi(url)
                        } else {
                            requestNearByLocationRequest = requestPojo as NearByLocationRequest
                            var url =
                                apiRequestType.url + "?location=" + requestNearByLocationRequest.location + "&radius=" + requestNearByLocationRequest.radius + "&type=" +
                                        requestNearByLocationRequest.type + "&keyword=:" + requestNearByLocationRequest.keyword + "&key=" + requestNearByLocationRequest.key
                            call = getApiInterface()!!.getApi(url)
                        }
                    }
                }
            }
        }

        if (showProgressDialog) {
            showProgressDialog()
        }

        call!!.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                activeApiCalls.remove(call)
                val responseBody = response.body()
                val code = response.code()
                if (responseBody != null) {

                    val responseString: String
                    try {
                        responseString = responseBody.string()

                        var master = NearByRestResponse()
                        try {
                            master = gson.fromJson(responseString, apiRequestType.responseType)
                            dataCarrier?.value = master
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {

                    var wrapperApiError: WrapperApiError? = null
                    val errBody = response.errorBody()
                    if (errBody != null) {
                        wrapperApiError = try {
                            val errBodyStr = errBody.string()

                            val errInPojo: NearByRestResponse = //MasterResponse<Any>
                                gson.fromJson(errBodyStr, apiRequestType.responseType)

                            WrapperApiError(type, errInPojo.status.toString())

                        } catch (e: java.lang.Exception) {
                            e.printStackTrace()
                            WrapperApiError(
                                type,
                                "Not",
                                MainApplication.get().getString(R.string.something_went_wrong)
                            )
                        }
                    }
                    dispatchError(wrapperApiError!!)
                }
                hideProgressDialog()
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                activeApiCalls.remove(call)
                val err = getError(t.message)
                ToastUtils.showLong(err)
                hideProgressDialog()
            }
        })
    }


    private fun dispatchError(apiErr: WrapperApiError) {

        var result = apiErr.msg

        if (apiErr.msg.equals("validation error", true)) {
            result += ":\n"
            apiErr.validationErr.forEach {
                result += "\n\n $it"
            }
        }

        getListener()?.onApiCallFailed(apiErr.apiUrl, apiErr.status, result)

    }

}
