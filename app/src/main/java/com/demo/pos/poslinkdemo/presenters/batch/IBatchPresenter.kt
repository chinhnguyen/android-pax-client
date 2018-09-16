package com.demo.pos.poslinkdemo.presenters.batch

import com.demo.pos.poslinkdemo.presenters.IBasePresenter
import com.demo.pos.poslinkdemo.presenters.IBaseView

class IBatchPresenter {

    interface IBatchView: IBaseView {
        fun onBatchSuccess()
    }

    abstract class Presenter(view: IBatchView) : IBasePresenter<IBatchView>(view) {

        abstract fun callBatchClose()
    }

}