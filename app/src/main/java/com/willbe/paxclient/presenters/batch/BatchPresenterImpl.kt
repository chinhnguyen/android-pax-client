package com.willbe.paxclient.presenters.batch

import com.willbe.paxclient.IBaseView

class BatchPresenterImpl(view: IBaseView) : IBatchPresenter.Presenter(view) {

    override fun callBatchClose() {
//        val disposable = createPosLinkObservable()
//                .zipWith(BatchTask.createBatchCloseRequestObservable(), BiFunction { posLink: PosLink, batchRequest: BatchRequest ->
//                    posLink.BatchRequest = batchRequest
//                    executePosLink(posLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onBatchSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }
}