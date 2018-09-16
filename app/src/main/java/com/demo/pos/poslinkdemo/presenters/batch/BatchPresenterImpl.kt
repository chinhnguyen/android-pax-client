package com.demo.pos.poslinkdemo.presenters.batch

import com.demo.pos.poslinkdemo.tasks.BatchTask
import com.pax.poslink.BatchRequest
import com.pax.poslink.PosLink
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class BatchPresenterImpl(view: IBatchPresenter.IBatchView) : IBatchPresenter.Presenter(view) {

    override fun callBatchClose() {
        val disposable = createPosLinkObservable()
                .zipWith(BatchTask.createBatchCloseRequestObservable(), BiFunction { posLink: PosLink, batchRequest: BatchRequest ->
                    posLink.BatchRequest = batchRequest
                    executePosLink(posLink)
                })
                .flatMap { executeTransResult(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.onBatchSuccess()
                }, {
                    view.onError(it.message)
                })
    }
}