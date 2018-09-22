package com.willbe.paxclient

import android.os.Bundle
import android.support.v4.app.FragmentActivity

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        initView()
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initView()
}