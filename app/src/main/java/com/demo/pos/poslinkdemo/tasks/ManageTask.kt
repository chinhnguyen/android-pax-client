package com.demo.pos.poslinkdemo.tasks

import com.pax.poslink.ManageRequest
import io.reactivex.Observable

object ManageTask {

    public fun createManageRequestObservable(tranType: String): Observable<ManageRequest> {
        return Observable.create<ManageRequest> {
            val manageRequest = ManageRequest()
            manageRequest.TransType = manageRequest.ParseTransType(tranType)
            it.onNext(manageRequest)
            it.onComplete()
        }
    }
}