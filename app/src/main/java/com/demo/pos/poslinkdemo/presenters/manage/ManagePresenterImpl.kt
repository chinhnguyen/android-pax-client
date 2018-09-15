package com.demo.pos.poslinkdemo.presenters.manage

import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.demo.pos.poslinkdemo.tasks.ManageTask
import com.pax.poslink.ManageRequest
import com.pax.poslink.PosLink
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class ManagePresenterImpl(view: IManagePresenter.IManageBaseView) : IManagePresenter.Presenter(view) {

    override fun callManageInitRequest() {
        val disposable = ManageTask.createManageRequestObservable(PosLinkConfiguration.TRAN_TYPE_MANAGE_INIT)
                .zipWith(createPosLinkObservable(), BiFunction { manageRequest: ManageRequest, posLink: PosLink ->
                    posLink.ManageRequest = manageRequest
                    executePosLink(posLink)
                }).flatMap { executeTransResult(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view.manageInitSuccess()
                }, {
                    view.onError(it.message)
                })
    }

}