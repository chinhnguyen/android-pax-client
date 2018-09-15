package com.demo.pos.poslinkdemo.presenters

import android.content.Context

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun getContext(): Context
}