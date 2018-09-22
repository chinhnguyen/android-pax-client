package com.willbe.paxclient.fragments

import com.demo.pos.poslinkdemo.R
import com.willbe.paxclient.configuration.PosLinkConfiguration
import com.willbe.paxclient.presenters.IBaseView
import com.willbe.paxclient.presenters.manage.IManagePresenter
import com.willbe.paxclient.presenters.manage.ManagePresenterImpl
import kotlinx.android.synthetic.main.fragment_configuration.*

class ConfigFragment : BaseFragment<IManagePresenter.Presenter, IBaseView>(), IBaseView {

    /**
     * Return the IP address from the 4 text fields.
     */
    private val ipAddress: String
        get() {
            return arrayOf(ip1TextView.text, ip2TextView.text, ip3TextView.text, ip4TextView.text).joinToString { "." }
        }


    override fun initPresenter(): IManagePresenter.Presenter {
        return ManagePresenterImpl(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_configuration
    }

    override fun setupViews() {
        testConnectionButton.setOnClickListener {
            PosLinkConfiguration.ipAddress = ipAddress
            presenter.testConnection()
        }
    }

}