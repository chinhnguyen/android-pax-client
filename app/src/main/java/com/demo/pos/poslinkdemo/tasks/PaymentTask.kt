package com.demo.pos.poslinkdemo.tasks

import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.pax.poslink.PaymentRequest
import io.reactivex.Observable

object PaymentTask {

    /**
     * Create PaymentRequest with TransType = Sale
     */
    fun createPaymentSaleRequestObject(amount: String): PaymentRequest {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(PosLinkConfiguration.TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_PAYMENT_SALE)
        paymentRequest.ECRRefNum = PosLinkConfiguration.ERC_REF_PAYMENT
        paymentRequest.Amount = amount
        return paymentRequest
    }

    fun createPaymentSaleRequestObservable(amount: String): Observable<PaymentRequest> {
        return Observable.create<PaymentRequest> {
            it.onNext(createPaymentSaleRequestObject(amount))
            it.onComplete()
        }
    }

    /**
     * Create PaymentRequest with TransType = Void
     */
    fun createPaymentVoidRequestObject(origRefNum: String): PaymentRequest {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(PosLinkConfiguration.TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_PAYMENT_VOID)
        paymentRequest.ECRRefNum = PosLinkConfiguration.ERC_REF_PAYMENT
        paymentRequest.OrigRefNum = origRefNum
        return paymentRequest
    }

    fun createPaymentVoidRequestObservable(origRefNum: String): Observable<PaymentRequest> {
        return Observable.create<PaymentRequest> {
            it.onNext(createPaymentVoidRequestObject(origRefNum))
            it.onComplete()
        }
    }

    fun createPaymentReturnRequestObject(amount: String): PaymentRequest {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(PosLinkConfiguration.TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_PAYMENT_REFUND)
        paymentRequest.ECRRefNum = PosLinkConfiguration.ERC_REF_PAYMENT
        paymentRequest.Amount = amount
        return paymentRequest
    }

    fun createPaymentReturnRequestObservable(amount: String): Observable<PaymentRequest> {
        return Observable.create<PaymentRequest> {
            it.onNext(createPaymentReturnRequestObject(amount))
            it.onComplete()
        }
    }

}