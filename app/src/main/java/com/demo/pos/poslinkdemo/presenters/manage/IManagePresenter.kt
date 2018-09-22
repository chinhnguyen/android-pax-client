package com.demo.pos.poslinkdemo.presenters.manage

import com.demo.pos.poslinkdemo.presenters.IBasePresenter
import com.demo.pos.poslinkdemo.presenters.IBaseView

class IManagePresenter {

    abstract class Presenter(view: IBaseView) : IBasePresenter<IBaseView>(view) {

        /**
         * Call MANAGE INIT to test for connection
         */
        abstract fun testConnection()
    }
}