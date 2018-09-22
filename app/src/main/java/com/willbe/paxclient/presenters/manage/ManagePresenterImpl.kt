package com.willbe.paxclient.presenters.manage

import com.willbe.paxclient.presenters.IBaseView

class ManagePresenterImpl(view: IBaseView) : IManagePresenter.Presenter(view) {

    override fun testConnection() {
//        val disposable = ManageTask.createManageRequestObservable(PosLinkConfiguration.TRAN_TYPE_MANAGE_INIT)
//                .zipWith(createPosLinkObservable(), BiFunction { manageRequest: ManageRequest, posLink: PosLink ->
//                    posLink.ManageRequest = manageRequest
//                    executePosLink(posLink)
//                }).flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.manageInitSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }

}