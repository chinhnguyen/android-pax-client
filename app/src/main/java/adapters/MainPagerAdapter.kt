package adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.View
import fragments.*

class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    private val NUM_OF_PAGES = 6

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return SaleFragment()
            1 -> return VoidFragment()
            2 -> return AdjustFragment()
            3 -> return RefundFragment()
            4 -> return ForceFragment()
            5 -> return CloseBatchFragment()
        }
        return SaleFragment()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return "Sale"
            1 -> return "Void"
            2 -> return "Adjust"
            3 -> return "Refund"
            4 -> return "Force"
            5 -> return "Close Batch"
        }
        return super.getPageTitle(position)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return NUM_OF_PAGES
    }

}