package com.willbe.paxclient.sale

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCService
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class SalePresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {

    val amount = BehaviorSubject.createDefault<Double>(0.0)

    override fun doRequest(device: CCDevice): Observable<CCStatus> {
        val amt = amount.value
        return if (amt != null && amt > 0) {
            CCService.sale(device, amt)
        } else {
            Observable.error(IllegalArgumentException("Missing inputs"))
        }
    }
}