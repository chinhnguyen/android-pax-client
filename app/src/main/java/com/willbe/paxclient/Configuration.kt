package com.willbe.paxclient

import com.willbe.paxclient.services.CCDevice
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object Configuration {
    /// The common IP settings
    val ipAddress = BehaviorSubject.create<String>()
    /// Create device from ip address
    var device: Observable<CCDevice> = ipAddress
            .map { CCDevice(it) }

}