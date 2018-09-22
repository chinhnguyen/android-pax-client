package com.willbe.paxclient.refund

import android.app.Fragment
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.R
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.fragment_adjust.*
import kotlinx.android.synthetic.main.fragment_force.*
import kotlinx.android.synthetic.main.fragment_refund.*

class RefundFragment : BaseFragment<RefundPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_refund
    }

    override fun initPresenter(): RefundPresenter {
        return RefundPresenter(this)
    }

    override fun setupViews() {
        RxTextView.textChanges(refundAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        Observables
                .combineLatest(Configuration.device, presenter.amount ) { device, amount ->
                    device.isValid && amount > 0
                }
                .subscribe { refundButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(refundButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)
    }

}