package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.sale.ISalePresenter
import com.demo.pos.poslinkdemo.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_void.*

class VoidFragment : BaseFragment<ISalePresenter.Presenter, ISalePresenter.ISaleView>(), ISalePresenter.ISaleView {

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }

    override fun onSaleSuccess() {
        showToastMessage("Call Void success")
    }

    override fun setupViews() {
        bt_void.setOnClickListener {
            presenter.callPaymentVoid()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_void
    }

}