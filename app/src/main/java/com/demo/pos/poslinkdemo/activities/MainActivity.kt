package com.demo.pos.poslinkdemo.activities

import com.demo.pos.poslinkdemo.R
import com.demo.pos.poslinkdemo.adapters.MainPagerAdapter
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
