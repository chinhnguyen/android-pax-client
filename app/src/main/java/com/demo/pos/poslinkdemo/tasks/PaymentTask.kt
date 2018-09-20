package com.demo.pos.poslinkdemo.tasks

import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.pax.poslink.PaymentRequest
import io.reactivex.Observable

object PaymentTask : BaseTask() {

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

    /**
     * Create PaymentRequest with TransType = Adjust
     */
    fun createPaymentAdjustRequestObject(tipAmount: String, origRefNum: String): PaymentRequest {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(PosLinkConfiguration.TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_PAYMENT_ADJUST)
        paymentRequest.ECRRefNum = PosLinkConfiguration.ERC_REF_PAYMENT
        paymentRequest.TipAmt = tipAmount
        paymentRequest.OrigRefNum = origRefNum
        return paymentRequest
    }

    fun createPaymentAdjustRequestObservable(tipAmount: String, origRefNum: String): Observable<PaymentRequest> {
        return Observable.create<PaymentRequest> {
            it.onNext(createPaymentAdjustRequestObject(tipAmount, origRefNum))
            it.onComplete()
        }
    }

    /**
     * Create PaymentRequest with TransType = Refund
     */
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

    /**
     * Create PaymentRequest with TransType = ForceAuth
     */
    fun createPaymentForceAuthRequestObject(amount: String, authCode: String): PaymentRequest {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(PosLinkConfiguration.TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_PAYMENT_FORCEAUTH)
        paymentRequest.ECRRefNum = PosLinkConfiguration.ERC_REF_PAYMENT
        paymentRequest.Amount = amount
        paymentRequest.AuthCode = authCode
        return paymentRequest
    }

    fun createPaymentForceAuthRequestObservable(amount: String, authCode: String): Observable<PaymentRequest> {
        return Observable.create<PaymentRequest> {
            it.onNext(createPaymentForceAuthRequestObject(amount, authCode))
            it.onComplete()
        }
    }

}