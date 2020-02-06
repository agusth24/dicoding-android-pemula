package com.example.prototype.mymoviecataloguemade3.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade3.MainViewModel
import com.example.prototype.mymoviecataloguemade3.R
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {
    private lateinit var adapter: MoviesAdapter
    private lateinit var mainViewModel: MainViewModel

    private fun showLoading(state: Boolean) {
        if(state) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    private fun showNoData(state: Boolean) {
        if(state) {
            tv_nodata_movies.visibility = View.VISIBLE
        } else {
            tv_nodata_movies.visibility = View.GONE
        }
    }

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movies.setHasFixedSize(true)
        adapter = MoviesAdapter()
        adapter.notifyDataSetChanged()

        rv_movies.layoutManager = LinearLayoutManager(context)
        rv_movies.adapter = adapter

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.setDataMovies()
        showNoData(false)
        showLoading(true)

        mainViewModel.getMovies().observe(viewLifecycleOwner, Observer { movieItems ->
            if (movieItems != null) {
                adapter.setData(movieItems)
            } else
                showNoData(true)
            showLoading(false)
        })

        adapter.setOnItemClickCallback(object: MoviesAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movies) {
                showSelectedItem(data)
            }
        })
    }

    private fun showSelectedItem(movie: Movies) {
        val moveActivity = Intent(context, MoviesDetailActivity::class.java)
        moveActivity.putExtra(MoviesDetailActivity.OBJECT_MOVIE, movie)
        startActivity(moveActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }
}