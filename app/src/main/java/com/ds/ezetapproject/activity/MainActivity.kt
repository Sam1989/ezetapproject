package com.ds.ezetapproject.activity

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.blankj.utilcode.util.ToastUtils
import com.ds.ezetapproject.R
import com.ds.ezetapproject.base.AsyncViewController
import com.ds.ezetapproject.base.BaseActivity
import com.ds.ezetapproject.base.MyViewModelProvider
import com.ds.ezetapproject.databinding.ActivityMainBinding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding
    var logoUrl = ""

    private val mViewModel by viewModels<MainActivityViewModel> {
        MyViewModelProvider(
            this as AsyncViewController
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        mBinding.clickHandler = ClickHandler()

        mViewModel.callFetchCustomUIApi()

        mViewModel.responseDataResponse.observe(this, Observer {
            if (it != null) {

                Picasso.get().invalidate(it.logoUrl)

                logoUrl = it.logoUrl.toString()

                Picasso.get().load(it.logoUrl)
                    .networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
                    .placeholder(R.mipmap.icon_noimage)
                    .error(R.mipmap.icon_noimage)
                    .into(mBinding.logo)

                if (it.uidata != null) {

                    for (index in 0..it.uidata.size) {
                        if (index == 0) {
                            mBinding.label1.text = it.uidata[index]?.value
                        } else if (index == 1) {
                            mBinding.edittext1.hint = it.uidata[index]?.hint
                        } else if (index == 2) {
                            mBinding.label2.text = it.uidata[index]?.value
                        } else if (index == 3) {
                            mBinding.edittext2.hint = it.uidata[index]?.hint
                        } else if (index == 4) {
                            mBinding.label3.text = it.uidata[index]?.value
                        } else if (index == 5) {
                            mBinding.edittext3.hint = it.uidata[index]?.hint
                        } else if (index == 6) {
                            mBinding.submit.text = it.uidata[index]?.value
                        }
                    }
                }
            }
        })
    }

    inner class ClickHandler {

        fun onClickSubmit() {

            Log.e("Value", "value")

            if (TextUtils.isEmpty(mBinding.edittext1.text.toString().trim())) {
                ToastUtils.showLong(getString(R.string.text))
                return
            }
            if (TextUtils.isEmpty(mBinding.edittext2.text.toString().trim())) {
                ToastUtils.showLong(getString(R.string.text))
                return
            }
            if (TextUtils.isEmpty(mBinding.edittext3.text.toString().trim())) {
                ToastUtils.showLong(getString(R.string.text))
                return
            }

            var intent = Intent(this@MainActivity, MainActivity2::class.java)
            intent.putExtra("Name", mBinding.edittext1.text.toString())
            intent.putExtra("Phone", mBinding.edittext2.text.toString())
            intent.putExtra("City", mBinding.edittext3.text.toString())
            intent.putExtra("Logo", logoUrl)

            startActivity(intent)
            finish()
        }
    }
}