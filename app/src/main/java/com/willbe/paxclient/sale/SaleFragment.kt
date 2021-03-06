package com.willbe.paxclient.sale

import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.R
import com.willbe.paxclient.services.CCDevice
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.fragment_sale.*

class SaleFragment : BaseFragment<SalePresenter, IBaseView>(), IBaseView {


    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_sale
    }

    override fun initPresenter(): SalePresenter {
        return SalePresenter(this)
    }

    override fun setupViews() {

        RxTextView.textChanges(saleAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        Observables
                .combineLatest(Configuration.device, presenter.amount ) { device, amount ->
                    device.isValid && amount > 0
                }
                .subscribe { saleButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(saleButton)
                .withLatestFrom(Configuration.ipAddress) { _, ip -> ip }
                .map { CCDevice(it) }
                .subscribe(presenter.request)
    }
}