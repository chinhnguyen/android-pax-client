package com.demo.pos.poslinkdemo.presenters

import android.content.Context
import com.pax.poslink.PosLink
import com.pax.poslink.ProcessTransResult
import com.pax.poslink.poslink.POSLinkCreator
import io.reactivex.Observable

abstract class IBasePresenter<V>(protected var view: V) where V : IBaseView {

    protected fun createPosLinkObject(): PosLink {
        return POSLinkCreator.createPoslink(getContext())
    }

    protected fun createPosLinkObservable(): Observable<PosLink> {
        return Observable.create<PosLink>{
            it.onNext(POSLinkCreator.createPoslink(getContext()))
        }
    }

    /**
     * Execute the posLink request and return result set in an observable
     */
    protected fun executePosLink(posLink: PosLink): ProcessTransResult {
        return posLink.ProcessTrans()
    }


    private fun getContext(): Context {
        return view.getContext()
    }
}