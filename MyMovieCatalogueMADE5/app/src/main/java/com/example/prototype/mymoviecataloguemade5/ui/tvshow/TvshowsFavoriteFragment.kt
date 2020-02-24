package com.example.prototype.mymoviecataloguemade5.ui.tvshow


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade5.MainActivity
import com.example.prototype.mymoviecataloguemade5.R
import com.example.prototype.mymoviecataloguemade5.db.MappingTvshowsHelper
import com.example.prototype.mymoviecataloguemade5.db.TvshowsHelper
import com.example.prototype.mymoviecataloguemade5.ui.movies.MoviesDetailActivity
import kotlinx.android.synthetic.main.fragment_tvshows.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class TvshowsFavoriteFragment : Fragment() {

    private lateinit var adapter: TvshowsAdapter
    private lateinit var tvshowsHelper: TvshowsHelper

    companion object {
        private const val EXTRA_STATE = "EXTRA_STATE"
    }

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

        if ((activity as MainActivity).supportActionBar != null) {
            val actionBar = (activity as MainActivity).supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
            actionBar?.setHomeButtonEnabled(false)
        }

        rv_tvshows.setHasFixedSize(true)
        adapter = TvshowsAdapter()
        adapter.notifyDataSetChanged()

        rv_tvshows.layoutManager = LinearLayoutManager(context)
        rv_tvshows.adapter = adapter

        showNoData(false)

        tvshowsHelper = TvshowsHelper.getInstance(this.requireContext())
        tvshowsHelper.open()

        isSavedInstanceState(savedInstanceState)

        adapter.setOnItemClickCallback(object : TvshowsAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Tvshows) {
                showSelectedItem(data)
            }
        })
    }

    private fun isSavedInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            loadAsync()
        } else {
            val tvshows = savedInstanceState.getParcelableArrayList<Tvshows>(EXTRA_STATE)
            if (tvshows != null) {
                adapter.setData(tvshows)
            }
        }
    }

    private fun loadAsync() {
        showLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = tvshowsHelper.queryAll()
                MappingTvshowsHelper.mapCursorToArrayList(cursor)
            }
            val tvshows = deferredNotes.await()
            adapter.setData(tvshows)
            if (tvshows.size <= 0) {
                showNoData(true)
            }
        }
        showLoading(false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getData())
    }

    private fun showSelectedItem(tvshows: Tvshows) {
        val moveActivity = Intent(context, TvshowsDetailActivity::class.java)
        moveActivity.putExtra(TvshowsDetailActivity.OBJECT_TVSHOW, tvshows)
        startActivityForResult(moveActivity, TvshowsDetailActivity.RESULT_DELETE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (data != null) {
            when (requestCode) {
                MoviesDetailActivity.RESULT_DELETE -> {
                    isSavedInstanceState(null)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }


}
