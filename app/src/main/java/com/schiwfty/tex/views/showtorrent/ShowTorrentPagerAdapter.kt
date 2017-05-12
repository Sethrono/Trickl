package com.schiwfty.tex.views.showtorrent

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.schiwfty.tex.views.torrentdetails.mvp.TorrentDetailsFragment
import com.schiwfty.tex.views.torrentfiles.mvp.TorrentFilesFragment

/**
 * Created by arran on 9/05/2017.
 */
class ShowTorrentPagerAdapter(fragmentManager: FragmentManager, private val torrentHash: String?) : FragmentStatePagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return TorrentFilesFragment.newInstance(torrentHash)
            else -> throw IllegalStateException("No more that 2 fregments required")
        }
    }

    override fun getCount(): Int {
        return 1
    }

    override fun getPageTitle(position: Int): CharSequence {
        when (position) {
            0 -> return "Torrent Files"
            else -> throw IllegalStateException("No more that 2 fregments required")
        }
    }
}