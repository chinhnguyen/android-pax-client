package com.demo.pos.poslinkdemo.presenters.manage

import com.demo.pos.poslinkdemo.presenters.IBasePresenter
import com.demo.pos.poslinkdemo.presenters.IBaseView

class IManagePresenter {

    interface IManageBaseView : IBaseView {
        fun manageInitSuccess()
    }

    abstract class Presenter(view: IManageBaseView) : IBasePresenter<IManageBaseView>(view) {

        /**
         * Temporary use Manage Init request to test connection
         */
        abstract fun callManageInitRequest()
    }
}