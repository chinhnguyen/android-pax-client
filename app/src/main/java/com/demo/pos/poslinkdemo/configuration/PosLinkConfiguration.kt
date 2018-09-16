package com.demo.pos.poslinkdemo.configuration

import com.pax.poslink.CommSetting

object PosLinkConfiguration {

    const val DEFAULT_DEST_PORT = "10009"

    const val TRAN_TYPE_MANAGE_INIT = "INIT"
    const val TRAN_TYPE_PAYMENT_SALE = "SALE"

    const val TENDER_TYPE_PAYMENT = "CREDIT"

    const val ERC_REF_PAYMENT = "1"

    const val COM_SETTING_HOST = "UNKNOWN"
    const val COM_SETTING_SERIAL_PORT = "COM1"
    const val COM_SETTING_TYPE = "TCP"
    const val COM_SETTING_TIME_OUT = "60000"
    const val COM_SETTING_BAUD_RATE = "9600"
    const val COM_SETTING_DEST_PORT = DEFAULT_DEST_PORT

    lateinit var ipAddress: String

    fun createComSetting(ipAddress: String): CommSetting {
        val comSetting = CommSetting()
        comSetting.timeOut = COM_SETTING_TIME_OUT
        comSetting.type = COM_SETTING_TYPE
        comSetting.serialPort = COM_SETTING_SERIAL_PORT
        comSetting.baudRate = COM_SETTING_BAUD_RATE
        comSetting.destIP = ipAddress
        comSetting.destPort = COM_SETTING_DEST_PORT
        comSetting.host = COM_SETTING_HOST
        comSetting.isEnableProxy = true
        return comSetting
    }

    fun createComSetting(): CommSetting {
        return createComSetting(ipAddress)
    }

    fun getAddDataFolder() {

    }
}