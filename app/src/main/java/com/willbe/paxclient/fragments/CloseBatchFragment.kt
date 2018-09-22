package com.willbe.paxclient.fragments

import com.willbe.paxclient.BaseFragment
import com.willbe.paxclient.R
import com.willbe.paxclient.IBaseView
import com.willbe.paxclient.presenters.batch.BatchPresenterImpl
import com.willbe.paxclient.presenters.batch.IBatchPresenter
import kotlinx.android.synthetic.main.fragment_close_batch.*

class CloseBatchFragment : BaseFragment<IBatchPresenter.Presenter, IBaseView>(), IBaseView {
    override fun initPresenter(): IBatchPresenter.Presenter {
        return BatchPresenterImpl(this)
    }

    override fun setupViews() {
        bt_close_batch.setOnClickListener {
            presenter.callBatchClose()
        }
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_close_batch
    }

}