package com.willbe.paxclient.fragments

import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.R
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.presenters.sale.ISalePresenter
import com.willbe.paxclient.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_adjust.*

class AdjustFragment : BaseFragment<ISalePresenter.Presenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
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