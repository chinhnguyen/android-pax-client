package com.willbe.paxclient.tasks

import com.willbe.paxclient.configuration.PosLinkConfiguration
import com.pax.poslink.BatchRequest
import io.reactivex.Observable

object BatchTask : BaseTask() {

    fun createBatchCloseRequestObject(): BatchRequest {
        val batchRequest = BatchRequest()
        batchRequest.TransType = batchRequest.ParseTransType(PosLinkConfiguration.TRAN_TYPE_BATCH_CLOSE)
        batchRequest.EDCType = batchRequest.ParseEDCType(PosLinkConfiguration.EDC_TYPE_CREADIT)
        return batchRequest
    }

    fun createBatchCloseRequestObservable(): Observable<BatchRequest> {
        return Observable.create<BatchRequest> {
            it.onNext(createBatchCloseRequestObject())
            it.onComplete()
        }
    }
}