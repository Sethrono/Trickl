package com.schiwfty.tex.views.addtorrent

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.schiwfty.tex.R
import com.schiwfty.tex.views.addtorrent.tabs.AddTorrentPagerAdapter
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_add_torrent.*


/**
 * Created by arran on 7/05/2017.
 */
class AddTorrentActivity : AppCompatActivity(), AddTorrentContract.View {


    lateinit var presenter: AddTorrentContract.Presenter

    companion object {
        val ARG_TORRENT_HASH = "arg_torrent_hash"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_torrent)
        presenter = AddTorrentPresenter()
        presenter.setup(this, this, intent.extras)
        presenter.fetchTorrent(presenter.torrentHash)

        addTorrentFab.setOnClickListener {
            presenter.notifyAddTorrentClicked(presenter.torrentHash)
        }

        setSupportActionBar(addTorrentToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = getString(R.string.add_torrent_title)
        addTorrentToolbar.setNavigationOnClickListener{
            super.onBackPressed()
        }
    }


    override fun showError(stringId: Int) {
        Toasty.error(this, getString(stringId))
    }

    override fun showInfo(stringId: Int) {
        Toasty.info(this, getString(stringId))
    }

    override fun showSuccess(stringId: Int) {
        Toasty.success(this, getString(stringId))
    }


    override fun notifyTorrentAdded() {
        addTorrentProgressBar.visibility = View.GONE
        addTorrentViewPager.visibility = View.VISIBLE
        val adapter = AddTorrentPagerAdapter(supportFragmentManager, presenter.torrentHash)
        addTorrentViewPager.adapter = adapter
        addTorrentSmartTab.setViewPager(addTorrentViewPager)
    }
}