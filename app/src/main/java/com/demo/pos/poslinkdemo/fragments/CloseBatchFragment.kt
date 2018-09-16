package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.batch.BatchPresenterImpl
import com.demo.pos.poslinkdemo.presenters.batch.IBatchPresenter
import com.demo.pos.poslinkdemo.presenters.manage.IManagePresenter
import com.demo.pos.poslinkdemo.presenters.manage.ManagePresenterImpl
import kotlinx.android.synthetic.main.fragment_close_batch.*

class CloseBatchFragment : BaseFragment<IBatchPresenter.Presenter, IBatchPresenter.IBatchView>(), IBatchPresenter.IBatchView{
    override fun initPresenter(): IBatchPresenter.Presenter {
        return BatchPresenterImpl(this)
    }

    override fun onBatchSuccess() {
        showToastMessage("Close Batch success")
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