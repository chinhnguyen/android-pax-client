package com.willbe.paxclient.adjust

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
import kotlinx.android.synthetic.main.fragment_adjust.*
import kotlinx.android.synthetic.main.fragment_configuration.*

class AdjustFragment : BaseFragment<AdjustPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_adjust
    }

    override fun initPresenter(): AdjustPresenter{
        return AdjustPresenter(this)
    }

    override fun setupViews() {
        RxTextView.textChanges(tipAmountEditText)
                .map { if (it.isEmpty()) 0.0 else it.toString().toDouble() }
                .subscribe(presenter.amount)

        RxTextView.textChanges(tipRefNumEditText)
                .map { it.toString() }
                .subscribe(presenter.origRefNum)

        Observables
                .combineLatest(Configuration.device, presenter.amount, presenter.origRefNum ) { device, amount, refNum ->
                    device.isValid && amount > 0 && refNum.isNotBlank()
                }
                .subscribe { adjustButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(adjustButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)
    }
}