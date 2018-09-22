package com.willbe.paxclient.force

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
import kotlinx.android.synthetic.main.fragment_configuration.*
import kotlinx.android.synthetic.main.fragment_force.*

class ForceFragment : BaseFragment<ForcePresenter, IBaseView>(), IBaseView {
    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_force
    }

    override fun initPresenter(): ForcePresenter {
        return ForcePresenter(this)
    }

    override fun setupViews() {
        RxTextView.textChanges(forceAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        RxTextView.textChanges(forceAuthCodeEditText)
                .map { it.toString() }
                .subscribe(presenter.authCode)

        Observables
                .combineLatest(Configuration.device, presenter.amount, presenter.authCode ) { device, amount, authCode ->
                    device.isValid && amount > 0 && authCode.isNotBlank()
                }
                .subscribe { forceButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(forceButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)

    }
}