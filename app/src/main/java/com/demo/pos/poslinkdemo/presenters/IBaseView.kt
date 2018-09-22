package com.demo.pos.poslinkdemo.presenters

import android.content.Context

/**
 * Common interface for all views to implement the MVP pattern.
 */
interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun onSuccess()
    fun onError(message: String?)
    fun getViewContext(): Context?
}