package com.example.prototype.mymoviecataloguemade5.ui.movies

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
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment : Fragment() {
    private lateinit var adapter: MoviesAdapter
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

        callDataMovie()

        adapter.setOnItemClickCallback(object : MoviesAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movies) {
                showSelectedItem(data)
            }
        })
    }

    private fun callDataMovie(key: String? = null)
    {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        mainViewModel.setDataMovies(key)
        showNoData(false)
        showLoading(true)

        mainViewModel.getMovies().observe(viewLifecycleOwner, Observer { movieItems ->
            if (movieItems != null) {
                showNoData(false)
                adapter.setData(movieItems)
            } else
                showNoData(true)
            showLoading(false)
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.search_options_menu, menu)
        val item = menu.findItem(R.id.search_action)
        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        item.apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                callDataMovie(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                callDataMovie(newText)
                return false
            }
        })

        searchView.setOnCloseListener {
            callDataMovie()
            false
        }
    }
}