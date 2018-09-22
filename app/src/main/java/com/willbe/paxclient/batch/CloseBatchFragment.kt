package com.willbe.paxclient.batch

import com.jakewharton.rxbinding2.view.RxView
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.R
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.fragment_adjust.*
import kotlinx.android.synthetic.main.fragment_close_batch.*
import kotlinx.android.synthetic.main.fragment_configuration.*

class CloseBatchFragment : BaseFragment<CloseBatchPresenter, IBaseView>(), IBaseView {

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_close_batch
    }

    override fun initPresenter(): CloseBatchPresenter {
        return CloseBatchPresenter(this)
    }

    override fun setupViews() {
        Configuration.device
                .subscribe { closeBatchButton.isEnabled = it.isValid }
                .addTo(disposable)

        RxView.clicks(closeBatchButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)
    }
}