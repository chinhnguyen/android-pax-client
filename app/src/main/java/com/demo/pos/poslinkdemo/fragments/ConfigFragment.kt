package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.configuration.PosLinkConfiguration
import com.demo.pos.poslinkdemo.presenters.manage.IManagePresenter
import com.demo.pos.poslinkdemo.presenters.manage.ManagePresenterImpl
import kotlinx.android.synthetic.main.fragment_configuration.*

class ConfigFragment : BaseFragment<IManagePresenter.Presenter, IManagePresenter.IManageBaseView>(), IManagePresenter.IManageBaseView {

    override fun initPresenter(): IManagePresenter.Presenter {
        return ManagePresenterImpl(this)
    }

    override fun setupViews() {
        bt_test_connection.setOnClickListener({
            PosLinkConfiguration.ipAddress = getIpAddressFromEditText()
            presenter.callManageInitRequest()
        })
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_configuration
    }

    override fun manageInitSuccess() {
        showToastMessage("Connect success")
    }

    private fun getIpAddressFromEditText(): String {
        return et_config_ip_1.text.toString() + "." +
                et_config_ip_2.text.toString() + "." +
                et_config_ip_3.text.toString() + "." +
                et_config_ip_4.text.toString()
    }

}