package com.demo.pos.poslinkdemo.presenters.sale

import com.demo.pos.poslinkdemo.presenters.IBasePresenter
import com.demo.pos.poslinkdemo.presenters.IBaseView

interface ISalePresenter {

    interface ISaleView: IBaseView {

        fun onSaleSuccess()
    }

    abstract class Presenter(view: ISaleView): IBasePresenter<ISaleView>(view) {

        abstract fun callPaymentSale(amount: String)
    }
}