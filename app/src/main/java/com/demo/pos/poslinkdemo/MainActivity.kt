package com.demo.pos.poslinkdemo

import adapters.MainPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var pagerAdapter: MainPagerAdapter

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        pagerAdapter = MainPagerAdapter(supportFragmentManager)
        vp_main_pager.adapter = pagerAdapter
        main_tab.setupWithViewPager(vp_main_pager)
    }
}
