package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.sale.ISalePresenter
import com.demo.pos.poslinkdemo.presenters.sale.SalePresenterImpl
import kotlinx.android.synthetic.main.fragment_sale.*

class SaleFragment: BaseFragment<ISalePresenter.Presenter, ISalePresenter.ISaleView>(), ISalePresenter.ISaleView {

    override fun onSaleSuccess() {
        showToastMessage("Sale success")
    }

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenterImpl(this)
    }


    override fun setupViews() {
        bt_sale_submit.setOnClickListener {
            presenter.callPaymentSale(ed_sale_amount.text.toString())
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

}