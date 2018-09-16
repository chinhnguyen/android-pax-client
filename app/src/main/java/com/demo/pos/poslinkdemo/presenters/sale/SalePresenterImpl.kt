package com.demo.pos.poslinkdemo.presenters.sale

import com.demo.pos.poslinkdemo.tasks.PaymentTask
import com.pax.poslink.PaymentRequest
import com.pax.poslink.PosLink
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class SalePresenterImpl(view: ISalePresenter.ISaleView) : ISalePresenter.Presenter(view) {


    override fun callPaymentSale(amount: String) {
        val disposable = createPosLinkObservable()
                .zipWith(PaymentTask.createPaymentSaleRequestObservable(amount), BiFunction { postLink: PosLink, paymentRequest: PaymentRequest ->
                    postLink.PaymentRequest = paymentRequest
                    executePosLink(postLink)
                })
                .flatMap { executeTransResult(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view.onSaleSuccess() }, { view.onError(it.message) })
    }
}