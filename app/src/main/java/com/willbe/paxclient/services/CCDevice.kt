package com.willbe.paxclient.services

import android.util.Patterns

class CCDevice(ip: String = "") {
    val ipAddress: String = ip

    var isValid: Boolean = false
        get() = Patterns.IP_ADDRESS.matcher(ipAddress).matches()
}