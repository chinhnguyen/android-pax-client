package com.demo.pos.poslinkdemo.fragments

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.presenters.manage.IManagePresenter
import com.demo.pos.poslinkdemo.presenters.manage.ManagePresenterImpl

class CloseBatchFragment : BaseFragment<IManagePresenter.Presenter, IManagePresenter.IManageBaseView>(), IManagePresenter.IManageBaseView {
    override fun manageInitSuccess() {

    }

    override fun initPresenter(): IManagePresenter.Presenter {
        return ManagePresenterImpl(this)
    }

    override fun setupViews() {
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_close_batch
    }

}