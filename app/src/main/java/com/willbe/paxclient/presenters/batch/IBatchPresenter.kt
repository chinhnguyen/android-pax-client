package com.willbe.paxclient.presenters.batch

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView

class IBatchPresenter {

    abstract class Presenter(view: IBaseView) : BasePresenter<IBaseView>(view) {

        abstract fun callBatchClose()
    }

}