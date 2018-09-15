package com.demo.pos.poslinkdemo.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentActivity
import android.util.Log

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())
        Log.d("BaseActivity", "Activity created")
        initView()
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initView()

}