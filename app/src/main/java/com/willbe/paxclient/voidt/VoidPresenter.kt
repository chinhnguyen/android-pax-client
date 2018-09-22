package com.willbe.paxclient.voidt

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCService
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class VoidPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val origRefNum = BehaviorSubject.createDefault("")

    override fun doRequest(device: CCDevice): Observable<CCStatus> {
        val refNum = origRefNum.value
        return if (refNum != null && refNum.isNotBlank()) {
            CCService.void(device, refNum)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}