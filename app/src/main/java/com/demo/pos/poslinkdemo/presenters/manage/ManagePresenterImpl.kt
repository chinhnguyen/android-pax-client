package com.demo.pos.poslinkdemo.presenters.manage

import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.demo.pos.poslinkdemo.tasks.ManageTask
import com.pax.poslink.ManageRequest

class ManagePresenterImpl(view: IManagePresenter.IManageBaseView) : IManagePresenter.Presenter(view) {

    override fun callManageInitRequest() {
        ManageTask.createManageRequestObservable(PosLinkConfiguration.TRAN_TYPE_MANAGE_INIT)
    }

}