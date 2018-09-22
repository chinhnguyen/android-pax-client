package com.willbe.paxclient

import android.content.Context
//import com.kizitonwose.android.disposebag.DisposeBag
//import com.kizitonwose.android.disposebag.disposedWith
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

@Suppress("LeakingThis")
abstract class BasePresenter<V>(protected var view: V) where V : IBaseView {

    /**
     * Entry point for requesting process.
     */
    val request = PublishSubject.create<CCDevice>()

    /**
     * The loading message to be shown to end user.
     */
    private val loadingMessage: String
        get() = "Contacting payment device ..."

    init {
        request.doOnNext { view.showLoading(loadingMessage) }
                .switchMap { doRequest(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    when (it) {
                        is CCStatus.Progress -> view.onProgress(it.detail)
                        is CCStatus.Error -> view.onError(it.error)
                        is CCStatus.Completed -> view.onSuccess(it.result)
                    }
                }, {
                    view.onError(it.localizedMessage)
                })
    }

    /**
     * Child class to provide the necessary method call.
     */
    protected open fun doRequest(device: CCDevice): Observable<CCStatus> {
        throw NotImplementedError()
    }
}