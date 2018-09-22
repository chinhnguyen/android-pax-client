package com.willbe.paxclient.batch

import com.willbe.paxclient.BasePresenter
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import com.willbe.paxclient.services.CCService
import com.willbe.paxclient.services.CCStatus
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class CloseBatchPresenter(view: IBaseView) : BasePresenter<IBaseView>(view)  {
    override fun doRequest(device: CCDevice): Observable<CCStatus> = CCService.closeBatch(device)
}