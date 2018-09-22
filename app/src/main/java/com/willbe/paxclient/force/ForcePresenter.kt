package com.willbe.paxclient.force

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCService
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class ForcePresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault<Double>(0.0)
    val authCode = BehaviorSubject.createDefault("")

    override fun doRequest(device: CCDevice): Observable<CCStatus> {
        val amt = amount.value
        var code = authCode.value
        return if (amt != null && amt > 0 && code != null && code.isNotBlank()) {
            CCService.force(device, amt, code)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}