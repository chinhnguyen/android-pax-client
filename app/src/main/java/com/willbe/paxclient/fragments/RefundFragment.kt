package com.willbe.paxclient.fragments

import com.demo.pos.poslinkdemo.R
import com.willbe.paxclient.presenters.IBaseView
import com.willbe.paxclient.presenters.sale.ISalePresenter
import com.willbe.paxclient.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_refund.*

class RefundFragment : BaseFragment<ISalePresenter.Presenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
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