package com.demo.pos.poslinkdemo.presenters.manage

import com.demo.pos.poslinkdemo.presenters.IBasePresenter
import com.demo.pos.poslinkdemo.presenters.IBaseView

class IManagePresenter {

    interface IManageBaseView : IBaseView {

    }

    abstract class Presenter(view: IManageBaseView) : IBasePresenter<IManageBaseView>(view) {

        abstract fun callManageInitRequest()
    }
}