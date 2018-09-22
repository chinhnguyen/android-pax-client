package com.willbe.paxclient.presenters.sale

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView

interface ISalePresenter {

    abstract class Presenter(view: IBaseView) : BasePresenter<IBaseView>(view) {

        abstract fun callPaymentSale(amount: String)

        abstract fun callPaymentVoid(origRefNum: String)

        abstract fun callPaymentAdjust(tipAmount: String, origRefNum: String)

        abstract fun callPaymentRefund(amount: String)

        abstract fun callPaymentForce(amount: String, authCode: String)
    }
}