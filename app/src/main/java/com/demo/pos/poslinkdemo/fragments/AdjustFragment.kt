package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.sale.ISalePresenter
import com.demo.pos.poslinkdemo.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_adjust.*

class AdjustFragment : BaseFragment<ISalePresenter.Presenter, ISalePresenter.ISaleView>(), ISalePresenter.ISaleView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }

    override fun onSaleSuccess() {
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