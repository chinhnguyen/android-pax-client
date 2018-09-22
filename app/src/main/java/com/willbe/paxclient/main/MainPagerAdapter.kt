package com.willbe.paxclient.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.willbe.paxclient.config.ConfigFragment
import com.willbe.paxclient.fragments.*

class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val tabs: List<Pair<String, Fragment>> = listOf<Pair<String, Fragment>>(
            Pair("Config", ConfigFragment()),
            Pair("Sale", SaleFragment()),
            Pair("Void", VoidFragment()),
            Pair("Adjust", AdjustFragment()),
            Pair("Refund", RefundFragment()),
            Pair("Force", ForceFragment()),
            Pair("Close Batch", CloseBatchFragment())
    )

    override fun getItem(position: Int): Fragment {
        return tabs[position].second
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].first
    }

    override fun getCount(): Int {
        return tabs.count()
    }
}