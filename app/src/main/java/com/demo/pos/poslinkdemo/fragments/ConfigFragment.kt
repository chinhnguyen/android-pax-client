package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.demo.pos.poslinkdemo.presenters.manage.IManagePresenter
import com.demo.pos.poslinkdemo.presenters.manage.ManagePresenterImpl
import kotlinx.android.synthetic.main.fragment_configuration.*

class ConfigFragment : BaseFragment<IManagePresenter.Presenter, IManagePresenter.IManageBaseView>(), IManagePresenter.IManageBaseView {

    /**
     * Return the IP address from the 4 text fields.
     */
    val ipAddress: String
        get() {
            return arrayOf(ip1TextView.text, ip2TextView.text, ip3TextView.text, ip4TextView.text).joinToString { "." }
        }


    override fun initPresenter(): IManagePresenter.Presenter {
        return ManagePresenterImpl(this)
    }

    override fun setupViews() {
        testConnectionButton.setOnClickListener {
            PosLinkConfiguration.ipAddress = ipAddress
            presenter.callManageInitRequest()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_configuration
    }

    override fun manageInitSuccess() {
        showToastMessage("Connect success")
    }

}