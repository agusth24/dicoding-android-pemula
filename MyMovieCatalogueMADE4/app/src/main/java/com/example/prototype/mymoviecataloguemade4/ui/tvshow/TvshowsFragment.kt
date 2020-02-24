package com.example.prototype.mymoviecataloguemade4.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade4.MainViewModel
import com.example.prototype.mymoviecataloguemade4.R
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TvshowsFragment : Fragment() {
    private lateinit var adapter: TvshowsAdapter
    private lateinit var mainViewModel: MainViewModel

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showNoData(state: Boolean) {
        if (state) {
            tv_nodata_tvshow.visibility = View.VISIBLE
        } else {
            tv_nodata_tvshow.visibility = View.GONE
        }
    }

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_tvshows.setHasFixedSize(true)
        adapter = TvshowsAdapter()
        adapter.notifyDataSetChanged()

        rv_tvshows.layoutManager = LinearLayoutManager(context)
        rv_tvshows.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        mainViewModel.setDataTvshows()
        showNoData(false)
        showLoading(true)

        mainViewModel.getTvshow().observe(viewLifecycleOwner, Observer { tvshowItems ->
            if (tvshowItems != null) {
                adapter.setData(tvshowItems)
            } else
                showNoData(true)
            showLoading(false)
        })

        adapter.setOnItemClickCallback(object : TvshowsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Tvshows) {
                showSelectedItem(data)
            }
        })
    }

    private fun showSelectedItem(tvshow: Tvshows) {
        val moveActivity = Intent(context, TvshowsDetailActivity::class.java)
        moveActivity.putExtra(TvshowsDetailActivity.OBJECT_TVSHOW, tvshow)
        startActivity(moveActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }
}