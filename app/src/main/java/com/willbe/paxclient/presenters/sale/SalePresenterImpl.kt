package com.willbe.paxclient.presenters.sale

import com.willbe.paxclient.presenters.IBaseView

class SalePresenterImpl(view: IBaseView) : ISalePresenter.Presenter(view) {

    override fun callPaymentSale(amount: String) {
//        val disposable = createPosLinkObservable()
//                .zipWith(PaymentTask.createPaymentSaleRequestObservable(amount), BiFunction { postLink: PosLink, paymentRequest: PaymentRequest ->
//                    postLink.PaymentRequest = paymentRequest
//                    executePosLink(postLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({ view.onSaleSuccess() }, { view.onError(it.message) })
    }

    override fun callPaymentVoid(origRefNum: String) {
//        val disposable = createPosLinkObservable()
//                .zipWith(PaymentTask.createPaymentVoidRequestObservable(origRefNum), BiFunction { posLink: PosLink, paymentRequest: PaymentRequest ->
//                    posLink.PaymentRequest = paymentRequest
//                    executePosLink(posLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onSaleSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }

    override fun callPaymentAdjust(tipAmount: String, origRefNum: String) {
//        val subscribe = createPosLinkObservable()
//                .zipWith(PaymentTask.createPaymentAdjustRequestObservable(tipAmount, origRefNum), BiFunction { posLink: PosLink, paymentRequest: PaymentRequest ->
//                    posLink.PaymentRequest = paymentRequest
//                    executePosLink(posLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onSaleSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }

    override fun callPaymentRefund(amount: String) {
//        val disposable = createPosLinkObservable()
//                .zipWith(PaymentTask.createPaymentReturnRequestObservable(amount), BiFunction { posLink: PosLink, paymentRequets: PaymentRequest ->
//                    posLink.PaymentRequest = paymentRequets
//                    executePosLink(posLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onSaleSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }

    override fun callPaymentForce(amount: String, authCode: String) {
//        createPosLinkObservable()
//                .zipWith(PaymentTask.createPaymentForceAuthRequestObservable(amount, authCode), BiFunction { posLink: PosLink, paymentRequest: PaymentRequest ->
//                    posLink.PaymentRequest = paymentRequest
//                    executePosLink(posLink)
//                })
//                .flatMap { executeTransResult(it) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    view.onSaleSuccess()
//                }, {
//                    view.onError(it.message)
//                })
    }
}