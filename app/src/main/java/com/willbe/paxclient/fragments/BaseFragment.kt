package com.willbe.paxclient.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.willbe.paxclient.presenters.IBasePresenter
import com.willbe.paxclient.presenters.IBaseView

abstract class BaseFragment<P, V> : Fragment(), IBaseView where V : IBaseView, P : IBasePresenter<V> {

    lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        presenter = initPresenter()
        return inflater.inflate(getLayoutResourceId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    abstract fun getLayoutResourceId(): Int

    abstract fun initPresenter(): P

    abstract fun setupViews()

    override fun showLoading() { }

    override fun hideLoading() { }

    override fun onSuccess() {
        showToastMessage("Success")
    }

    override fun onError(message: String?) {
        showToastMessage(message)
    }

    override fun getViewContext(): Context? {
        return context
    }

    protected fun showToastMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}