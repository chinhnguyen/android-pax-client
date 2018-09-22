package com.willbe.paxclient.config

//import com.jakewharton.rxbinding2.view.RxView
//import com.kizitonwose.android.disposebag.disposedWith
import android.widget.EditText
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.Configuration
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.R
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
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

        Configuration.device
                .subscribe {
                    testConnectionButton.isEnabled = it.isValid
                }
                .addTo(disposable)

        RxView.clicks(testConnectionButton)
                .withLatestFrom(Configuration.device) { _, device -> device }
                .filter { it.isValid }
                .subscribe(presenter.request)
    }

    private fun textChanges(textView: EditText): Observable<CharSequence> {
        return RxTextView.textChanges(textView)//.filter { it.isNotBlank() }//.distinctUntilChanged()
    }
}