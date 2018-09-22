package com.willbe.paxclient.voidt

import android.app.Fragment
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
import kotlinx.android.synthetic.main.fragment_void.*

class VoidFragment : BaseFragment<VoidPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_void
    }

    override fun initPresenter(): VoidPresenter {
        return VoidPresenter(this)
    }

    override fun setupViews() {
        RxTextView.textChanges(voidRefNumEditText)
                .map { it.toString() }
                .subscribe(presenter.origRefNum)

        Observables
                .combineLatest(Configuration.device, presenter.origRefNum ) { device, refNum ->
                    device.isValid && refNum.isNotBlank()
                }
                .subscribe { voidButton.isEnabled = it }
                .addTo(disposable)

        RxView.clicks(voidButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)
    }

}