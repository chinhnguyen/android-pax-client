package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.sale.ISalePresenter
import com.demo.pos.poslinkdemo.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_refund.*

class RefundFragment : BaseFragment<ISalePresenter.Presenter, ISalePresenter.ISaleView>(), ISalePresenter.ISaleView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }

    override fun onSaleSuccess() {
        showToastMessage("Call Return success")
    }

    override fun setupViews() {
        bt_refund_submit.setOnClickListener {
            presenter.callPaymentRefund(ed_refund_amount.text.toString())
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_refund
    }

}