package com.willbe.paxclient.presenters.sale

import com.willbe.paxclient.presenters.IBasePresenter
import com.willbe.paxclient.presenters.IBaseView

interface ISalePresenter {

    abstract class Presenter(view: IBaseView) : IBasePresenter<IBaseView>(view) {

        abstract fun callPaymentSale(amount: String)

        abstract fun callPaymentVoid(origRefNum: String)

        abstract fun callPaymentAdjust(tipAmount: String, origRefNum: String)

        abstract fun callPaymentRefund(amount: String)

        abstract fun callPaymentForce(amount: String, authCode: String)
    }
}