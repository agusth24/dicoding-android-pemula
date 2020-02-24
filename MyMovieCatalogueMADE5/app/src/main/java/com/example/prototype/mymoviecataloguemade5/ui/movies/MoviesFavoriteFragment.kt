package com.example.prototype.mymoviecataloguemade5.ui.movies


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade5.MainActivity
import com.example.prototype.mymoviecataloguemade5.R
import com.example.prototype.mymoviecataloguemade5.db.MappingMoviesHelper
import com.example.prototype.mymoviecataloguemade5.db.MoviesHelper
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 */
class MoviesFavoriteFragment : Fragment() {

    private lateinit var adapter: MoviesAdapter
    private lateinit var moviesHelper: MoviesHelper

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
            tv_nodata_movies.visibility = View.VISIBLE
        } else {
            tv_nodata_movies.visibility = View.GONE
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

        rv_movies.setHasFixedSize(true)
        adapter = MoviesAdapter()
        adapter.notifyDataSetChanged()

        rv_movies.layoutManager = LinearLayoutManager(context)
        rv_movies.adapter = adapter

        showNoData(false)

        moviesHelper = MoviesHelper.getInstance(this.requireContext())
        moviesHelper.open()

        isSavedInstanceState(savedInstanceState)

        adapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movies) {
                showSelectedItem(data)
            }
        })
    }

    private fun isSavedInstanceState(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            loadAsync()
        } else {
            val movies = savedInstanceState.getParcelableArrayList<Movies>(EXTRA_STATE)
            if (movies != null) {
                adapter.setData(movies)
            }
        }
    }

    private fun loadAsync() {
        showLoading(true)
        GlobalScope.launch(Dispatchers.Main) {
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = moviesHelper.queryAll()
                MappingMoviesHelper.mapCursorToArrayList(cursor)
            }
            val movies = deferredNotes.await()
            adapter.setData(movies)
            if (movies.size <= 0) {
                showNoData(true)
            }
        }
        showLoading(false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, adapter.getData())
    }

    private fun showSelectedItem(movie: Movies) {
        val moveActivity = Intent(context, MoviesDetailActivity::class.java)
        moveActivity.putExtra(MoviesDetailActivity.OBJECT_MOVIE, movie)
        startActivityForResult(moveActivity, MoviesDetailActivity.RESULT_DELETE)
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
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }


}
