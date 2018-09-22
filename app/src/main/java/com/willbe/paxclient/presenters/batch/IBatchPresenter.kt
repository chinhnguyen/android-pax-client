package com.willbe.paxclient.presenters.batch

import com.willbe.paxclient.presenters.IBasePresenter
import com.willbe.paxclient.presenters.IBaseView

class IBatchPresenter {

    abstract class Presenter(view: IBaseView) : IBasePresenter<IBaseView>(view) {

        abstract fun callBatchClose()
    }

}