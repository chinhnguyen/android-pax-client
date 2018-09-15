package com.demo.pos.poslinkdemo.presenters

import android.content.Context
import com.pax.poslink.PosLink
import com.pax.poslink.poslink.POSLinkCreator

abstract class IBasePresenter<V>(protected var view: V) where V : IBaseView {

    protected fun createPosLinkObject(): PosLink {
        return POSLinkCreator.createPoslink(getContext())
    }

    private fun getContext(): Context {
        return view.getContext()
    }
}