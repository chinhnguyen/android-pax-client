package com.willbe.paxclient

import com.pax.poslink.CommSetting
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

object Configuration {
    /// The common IP settings
    val ipAddress = BehaviorSubject.create<String>()
}