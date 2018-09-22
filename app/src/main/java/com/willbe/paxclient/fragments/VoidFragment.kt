package com.willbe.paxclient.fragments

import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.R
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.presenters.sale.ISalePresenter
import com.willbe.paxclient.sale.SalePresenter
import kotlinx.android.synthetic.main.fragment_void.*

class VoidFragment : BaseFragment<ISalePresenter.Presenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenter(this)
    }

    override fun setupViews() {
        bt_void.setOnClickListener {
            presenter.callPaymentVoid(et_orig_ref_number.text.toString())
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_void
    }

}