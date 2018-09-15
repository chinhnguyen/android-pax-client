package com.demo.pos.poslinkdemo.presenters

import android.content.Context
import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.pax.poslink.PosLink
import com.pax.poslink.ProcessTransResult
import com.pax.poslink.poslink.POSLinkCreator
import io.reactivex.Observable

abstract class IBasePresenter<V>(protected var view: V) where V : IBaseView {

    protected fun createPosLinkObject(): PosLink {
        return POSLinkCreator.createPoslink(getContext())
    }

    protected fun createPosLinkObservable(): Observable<PosLink> {
        return Observable.create<PosLink> {
            val posLink = POSLinkCreator.createPoslink(getContext())
            posLink.appDataFolder = getAppDataFolder()
            posLink.SetCommSetting(PosLinkConfiguration.createComSetting())
            it.onNext(posLink)
        }
    }

    /**
     * Execute the posLink request and return result set in an observable
     */
    protected fun executePosLink(posLink: PosLink): ProcessTransResult {
        return posLink.ProcessTrans()
    }

    /**
     * Execute the postLink response object to indicate the request was success or error.
     *
     * The return Observable should take an object instead of String.
     */
    protected fun executeTransResult(result: ProcessTransResult): Observable<String> {
        return Observable.create<String> {
            when (result.Code) {
                ProcessTransResult.ProcessTransResultCode.OK -> {
                    it.onNext("Request success")
                }
                ProcessTransResult.ProcessTransResultCode.TimeOut -> {
                    val exception = RuntimeException("Timed out")
                    it.onError(exception)
                }
                ProcessTransResult.ProcessTransResultCode.ERROR -> {
                    val exception = RuntimeException(result.Msg)
                    it.onError(exception)
                }
            }
        }
    }

    private fun getAppDataFolder(): String? {
        return getContext()?.applicationContext?.filesDir?.absolutePath
    }

    private fun getContext(): Context? {
        return view.getViewContext()
    }
}