package com.demo.pos.poslinkdemo.presenters

import android.content.Context
import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.pax.poslink.PosLink
import com.pax.poslink.ProcessTransResult
import com.pax.poslink.poslink.POSLinkCreator
import io.reactivex.Observable

abstract class IBasePresenter<V>(protected var view: V) where V : IBaseView {
    /**
     * Return the context of View.
     */
    val context: Context?
        get() = view.getViewContext()

    /**
     * Return the post link object (return new one on each call) for calling pax.
     */
    val posLink: PosLink
        get() {
            val posLink = POSLinkCreator.createPoslink(context)
            posLink.appDataFolder = context?.applicationContext?.filesDir?.absolutePath
            posLink.SetCommSetting(PosLinkConfiguration.commSetting)
            return posLink
        }
//    /**
//     * Execute the postLink response object to indicate the request was success or error.
//     *
//     * The return Observable should take an object instead of String.
//     */
//    protected fun executeTransResult(result: ProcessTransResult): Observable<String> {
//        return Observable.create<String> {
//            when (result.Code) {
//                ProcessTransResult.ProcessTransResultCode.OK -> {
//                    it.onNext("Request success")
//                }
//                ProcessTransResult.ProcessTransResultCode.TimeOut -> {
//                    val exception = RuntimeException("Timed out")
//                    it.onError(exception)
//                }
//                ProcessTransResult.ProcessTransResultCode.ERROR -> {
//                    val exception = RuntimeException(result.Msg)
//                    it.onError(exception)
//                }
//            }
//        }
//    }
}