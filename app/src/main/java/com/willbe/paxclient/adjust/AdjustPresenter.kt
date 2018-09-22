package com.willbe.paxclient.adjust

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCService
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class AdjustPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault<Double>(0.0)
    val origRefNum = BehaviorSubject.createDefault("")

    override fun doRequest(device: CCDevice): Observable<CCStatus> {
        val amt = amount.value
        var ref = origRefNum.value
        return if (amt != null && amt > 0 && ref != null && ref.isNotEmpty()) {
            CCService.adjust(device, amt, ref)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}