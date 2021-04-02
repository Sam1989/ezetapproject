package com.ds.ezetapproject.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.ds.ezetapproject.R
import com.ds.ezetapproject.base.BaseActivity
import com.ds.ezetapproject.base.AsyncViewController
import com.ds.ezetapproject.base.MyViewModelProvider
import com.ds.ezetapproject.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var mBinding: ActivityMainBinding


    private val mViewModel by viewModels<MainActivityViewModel> {
        MyViewModelProvider(
            this as AsyncViewController

        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)


    }
}