package com.willbe.paxclient.config

import android.util.Patterns
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.willbe.paxclient.R
//import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
//import com.kizitonwose.android.disposebag.disposedWith
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.services.CCDevice
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.withLatestFrom
import kotlinx.android.synthetic.main.fragment_configuration.*


class ConfigFragment : BaseFragment<ConfigPresenter, IBaseView>(), IBaseView {

    override fun initPresenter(): ConfigPresenter {
        return ConfigPresenter(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_configuration
    }

    override fun setupViews() {
        RxView.clicks(testConnectionButton)
                .withLatestFrom(Configuration.ipAddress) { _, ip -> ip }
                .map { CCDevice(it) }
                .subscribe(presenter.request)

        Configuration.ipAddress
                .map { Patterns.IP_ADDRESS.matcher(it).matches() }
                .subscribe {
                    testConnectionButton.isEnabled = it
                }

        Observables
                .combineLatest(
                        textChanges(ip1TextView),
                        textChanges(ip2TextView),
                        textChanges(ip3TextView),
                        textChanges(ip4TextView)) { ip1, ip2, ip3, ip4 -> String
                            arrayOf(ip1, ip2, ip3, ip4).joinToString(".")
                }
                .subscribe(Configuration.ipAddress)

        ip1TextView.setText("192")
        ip2TextView.setText("168")
        ip3TextView.setText("10")
        ip4TextView.setText("38")
    }

    private fun textChanges(textView: EditText): Observable<CharSequence> {
        return RxTextView.textChanges(textView)//.filter { it.isNotBlank() }//.distinctUntilChanged()
    }
}