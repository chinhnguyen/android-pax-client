package com.willbe.paxclient.sale

import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.R
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.presenters.sale.ISalePresenter
import com.willbe.paxclient.services.CCDevice
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.fragment_sale.*

class SaleFragment : BaseFragment<ISalePresenter.Presenter, IBaseView>(), IBaseView {


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

    override fun initPresenter(): ISalePresenter.Presenter {
        return SalePresenter(this)
    }

    override fun setupViews() {

        RxTextView.textChanges(amountEditText)
                .map { it.toString().toDouble() }
                .subscribe(presenter.amount)

        RxView.clicks(submitButton)
                .withLatestFrom(Configuration.ipAddress) { _, ip -> ip }
                .map { CCDevice(it) }
                .subscribe(presenter.request)
    }
}