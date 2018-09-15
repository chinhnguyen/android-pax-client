package com.demo.pos.poslinkdemo.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.demo.pos.poslinkdemo.fragments.*

class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val NUM_OF_PAGES = 7

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ConfigFragment()
            1 -> return SaleFragment()
            2 -> return VoidFragment()
            3 -> return AdjustFragment()
            4 -> return RefundFragment()
            5 -> return ForceFragment()
            6 -> return CloseBatchFragment()
        }
        return SaleFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Config"
            1 -> return "Sale"
            2 -> return "Void"
            3 -> return "Adjust"
            4 -> return "Refund"
            5 -> return "Force"
            6 -> return "Close Batch"
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return NUM_OF_PAGES
    }

}