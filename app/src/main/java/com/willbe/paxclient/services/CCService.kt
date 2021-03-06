package com.willbe.paxclient.services

import com.pax.poslink.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object CCService {


    private const val TRAN_TYPE_MANAGE_INIT = "INIT"
    private const val TRAN_TYPE_PAYMENT_SALE = "SALE"
    private const val TRAN_TYPE_PAYMENT_VOID = "VOID"
    private const val TRAN_TYPE_PAYMENT_REFUND = "RETURN"
    private const val TRAN_TYPE_PAYMENT_FORCEAUTH = "FORCEAUTH"
    private const val TRAN_TYPE_PAYMENT_ADJUST = "ADJUST"
    private const val TRAN_TYPE_BATCH_CLOSE = "BATCHCLOSE"

    private const val EDC_TYPE_CREADIT = "CREDIT"
    private const val TENDER_TYPE_PAYMENT = "CREDIT"


    private const val COM_SETTING_HOST = "UNKNOWN"
    private const val COM_SETTING_SERIAL_PORT = "COM1"
    private const val COM_SETTING_TYPE = "TCP"
    private const val COM_SETTING_TIME_OUT = "60000"
    private const val COM_SETTING_BAUD_RATE = "9600"
    private const val COM_SETTING_DEST_PORT = "10009"

    private val defaultCommSetting: CommSetting
        get() {
            val comSetting = CommSetting()
            comSetting.timeOut = COM_SETTING_TIME_OUT
            comSetting.type = COM_SETTING_TYPE
            comSetting.serialPort = COM_SETTING_SERIAL_PORT
            comSetting.baudRate = COM_SETTING_BAUD_RATE
            comSetting.destPort = COM_SETTING_DEST_PORT
            comSetting.host = COM_SETTING_HOST
            comSetting.isEnableProxy = true
            return comSetting
        }

    private fun send(device: CCDevice, request: Any, message: String = "Contacting payment device ..."): Observable<CCStatus> {
        return Observable
                .create<CCStatus> {
                    try {
                        it.onNext(CCStatus.Progress(message))

                        val commSetting = defaultCommSetting
                        commSetting.destIP = device.ipAddress

                        val link = PosLink()
                        link.SetCommSetting(commSetting)

                        when (request) {
                            is PaymentRequest -> link.PaymentRequest = request
                            is BatchRequest -> link.BatchRequest = request
                            is ManageRequest -> link.ManageRequest = request
                        }
                        // Send the message
                        val res = link.ProcessTrans()
                        // Get the result
                        val ccResult = CCResult()
                        var resultCode = ""
                        var resultText = ""

                        when (res.Code) {
                            ProcessTransResult.ProcessTransResultCode.OK -> {
                                when (request) {
                                    is PaymentRequest -> {
                                        ccResult.response = link.PaymentResponse
                                        resultCode = link.PaymentResponse.ResultCode
                                        resultText = link.PaymentResponse.ResultTxt
                                    }

                                    is BatchRequest -> {
                                        ccResult.response = link.BatchResponse
                                        resultCode = link.BatchResponse.ResultCode
                                        resultText = link.BatchResponse.ResultTxt
                                    }

                                    is ManageRequest -> {
                                        ccResult.response = link.ManageResponse
                                        resultCode = link.ManageResponse.ResultCode
                                        resultText = link.ManageResponse.ResultTxt
                                    }
                                }
                                ccResult.message = "[$resultCode] $resultText"
                                it.onNext(CCStatus.Completed(ccResult))
                            }

                            ProcessTransResult.ProcessTransResultCode.TimeOut -> {
                                it.onNext(CCStatus.Error("Timeout"))
                            }

                            ProcessTransResult.ProcessTransResultCode.ERROR -> {
                                it.onNext(CCStatus.Error(res.Msg))
                            }
                        }
                    } catch (e: Throwable) {
                        it.onNext(CCStatus.Error(e.localizedMessage))
                    } finally {
                        it.onComplete()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * Send manage-init command to PAX as a mean of testing connection status.
     */
    fun testConnection(device: CCDevice): Observable<CCStatus> {
        val manageRequest = ManageRequest()
        manageRequest.TransType = manageRequest.ParseTransType(TRAN_TYPE_MANAGE_INIT)
        return send(device, manageRequest)
    }

    /**
     * Send SALE command to PAX.
     */
    fun sale(device: CCDevice, amount: Double): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_SALE)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.Amount = (amount * 100).toInt().toString()
        return send(device, paymentRequest)
    }

    /**
     * Send VOID command to PAX.
     */
    fun void(device: CCDevice, origRefNum: String): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_VOID)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.OrigRefNum = origRefNum
        return send(device, paymentRequest)
    }

    /**
     * Send ADJUST command to PAX.
     */
    fun adjust(device: CCDevice, tipAmount: Double, origRefNum: String): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_ADJUST)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.Amount = (tipAmount * 100).toInt().toString()
        paymentRequest.OrigRefNum = origRefNum
        return send(device, paymentRequest)
    }

    /**
     * Send RETURN command to PAX.
     */
    fun refund(device: CCDevice, amount: Double): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_REFUND)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.Amount = (amount * 100).toInt().toString()
        return send(device, paymentRequest)
    }

    /**
     * Send FORCEAUTH command to PAX.
     */
    fun force(device: CCDevice, amount: Double, authCode: String): Observable<CCStatus> {
        val paymentRequest = PaymentRequest()
        paymentRequest.TenderType = paymentRequest.ParseTenderType(TENDER_TYPE_PAYMENT)
        paymentRequest.TransType = paymentRequest.ParseTransType(TRAN_TYPE_PAYMENT_FORCEAUTH)
        paymentRequest.ECRRefNum = "1"
        paymentRequest.Amount = (amount * 100).toInt().toString()
        paymentRequest.AuthCode = authCode
        return send(device, paymentRequest)
    }

    /**
     * Send CLOSEBATCH command to PAX.
     */
    fun closeBatch(device: CCDevice): Observable<CCStatus> {
        val batchRequest = BatchRequest()
        batchRequest.TransType = batchRequest.ParseTransType(TRAN_TYPE_BATCH_CLOSE)
        batchRequest.EDCType = batchRequest.ParseEDCType(EDC_TYPE_CREADIT)
        return send(device, batchRequest)
    }
}