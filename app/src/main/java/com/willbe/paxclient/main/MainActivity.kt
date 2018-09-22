package com.willbe.paxclient.main

import com.willbe.paxclient.BaseActivity
import com.willbe.paxclient.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var pagerAdapter: MainPagerAdapter

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        mainPager.adapter = pagerAdapter
        mainTab.setupWithViewPager(mainPager)
    }
}
