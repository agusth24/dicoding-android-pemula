package com.example.prototype.mymoviecataloguemade5.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade5.MainActivity
import com.example.prototype.mymoviecataloguemade5.MainViewModel
import com.example.prototype.mymoviecataloguemade5.R
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

        callDataTvshow()

        adapter.setOnItemClickCallback(object : TvshowsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Tvshows) {
                showSelectedItem(data)
            }
        })
    }

    private fun callDataTvshow(key: String? = null) {
        mainViewModel.setDataTvshows(key)
        showNoData(false)
        showLoading(true)

        mainViewModel.getTvshow().observe(viewLifecycleOwner, Observer { tvshowItems ->
            if (tvshowItems != null) {
                showNoData(false)
                adapter.setData(tvshowItems)
            } else
                showNoData(true)
            showLoading(false)
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_options_menu, menu)
        val item = menu.findItem(R.id.search_action)
        val searchView =
            SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        item.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                callDataTvshow(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                callDataTvshow(newText)
                return false
            }
        })

        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                callDataTvshow()
                return false
            }
        })
    }
}