package com.willbe.paxclient.presenters.manage

import com.willbe.paxclient.presenters.IBasePresenter
import com.willbe.paxclient.presenters.IBaseView

class IManagePresenter {

    abstract class Presenter(view: IBaseView) : IBasePresenter<IBaseView>(view) {

        /**
         * Call MANAGE INIT to test for connection
         */
        abstract fun testConnection()
    }
}