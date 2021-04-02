package com.ds.ezetapproject.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.ds.ezetapproject.R
import com.ds.ezetapproject.base.BaseActivity
import com.ds.ezetapproject.databinding.ActivityMain2Binding
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MainActivity2 : BaseActivity() {

    lateinit var mBinding: ActivityMain2Binding

    var logo = ""
    var name = ""
    var phone = ""
    var city = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main2)

        name = intent.getStringExtra("Name").toString()
        phone = intent.getStringExtra("Phone").toString()
        city = intent.getStringExtra("City").toString()
        logo = intent.getStringExtra("Logo").toString()



        Picasso.get().invalidate(logo)

        Picasso.get().load(logo)
            .networkPolicy(NetworkPolicy.NO_CACHE).memoryPolicy(MemoryPolicy.NO_CACHE)
            .placeholder(R.mipmap.icon_noimage)
            .error(R.mipmap.icon_noimage)
            .into(mBinding.logo)

        mBinding.label1.text = "Name - $name"
        mBinding.label2.text = "Phone - $phone"
        mBinding.label3.text = "City - $city"

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}