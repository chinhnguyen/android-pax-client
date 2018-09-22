//package com.willbe.paxclient.tasks
//
//import com.pax.poslink.ManageRequest
//import io.reactivex.Observable
//
//object ManageTask : BaseTask() {
//
//    fun createManageRequestObject(tranType: String): ManageRequest {
//        val manageRequest = ManageRequest()
//        manageRequest.TransType = manageRequest.ParseTransType(tranType)
//        return manageRequest
//    }
//
//    public fun createManageRequestObservable(tranType: String): Observable<ManageRequest> {
//        return Observable.create<ManageRequest> {
//            it.onNext(createManageRequestObject(tranType))
//            it.onComplete()
//        }
//    }
//}