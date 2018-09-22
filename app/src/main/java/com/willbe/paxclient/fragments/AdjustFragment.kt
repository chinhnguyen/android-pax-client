package com.willbe.paxclient.fragments

import com.demo.pos.poslinkdemo.R
import com.willbe.paxclient.presenters.IBaseView
import com.willbe.paxclient.presenters.sale.ISalePresenter
import com.willbe.paxclient.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_adjust.*

class AdjustFragment : BaseFragment<ISalePresenter.Presenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }

    override fun onSuccess() {
        showToastMessage("Call Refund success")
    }

    override fun setupViews() {
        bt_adjust_submit.setOnClickListener {
            presenter.callPaymentAdjust(ed_adjust_amount.text.toString(), ed_adjust_orig_ref_number.text.toString())
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_adjust
    }

}