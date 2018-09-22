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
        manageRequest.TransType = manageRequest.ParseTransType("INIT")
        return send(device, manageRequest)
    }
}