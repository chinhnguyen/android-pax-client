package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.sale.ISalePresenter
import com.demo.pos.poslinkdemo.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_force.*

class ForceFragment : BaseFragment<ISalePresenter.Presenter, ISalePresenter.ISaleView>(), ISalePresenter.ISaleView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }

    override fun onSaleSuccess() {
        showToastMessage("Call ForceAuth success")
    }

    override fun setupViews() {
        bt_force_submit.setOnClickListener {
            presenter.callPaymentForce(ed_force_amount.text.toString(), ed_force_auth_code.text.toString())
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_force
    }

}