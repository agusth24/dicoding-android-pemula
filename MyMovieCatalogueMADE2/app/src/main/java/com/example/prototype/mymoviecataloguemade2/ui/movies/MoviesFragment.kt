package com.example.prototype.mymoviecataloguemade2.ui.movies

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade2.R
import kotlinx.android.synthetic.main.fragment_movies.*

class MoviesFragment : Fragment() {

    private val list = ArrayList<Movies>()

    companion object {
        private const val STATE_LIST = "state_list"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(STATE_LIST, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            list.addAll(getListMovies())
        } else {
            val stateList = savedInstanceState.getParcelableArrayList<Movies>(STATE_LIST)
            if(stateList != null) {
                list.addAll(stateList)
            }
        }
    }

    private fun getListMovies(): ArrayList<Movies> {
        val dataTitle = resources.getStringArray(R.array.data_movie_title)
        val dataDescription = resources.getStringArray(R.array.data_movie_desc)
        val dataPhoto = resources.getStringArray(R.array.data_movie_photos)
        val dataRelease = resources.getStringArray(R.array.data_movie_release)
        val dataRating = resources.getIntArray(R.array.data_movie_rating)
        val listMovie = ArrayList<Movies>()
        for (position in dataTitle.indices) {
            val movie = Movies(
                dataPhoto[position],
                dataTitle[position],
                dataDescription[position],
                dataRelease[position],
                dataRating[position]
            )
            listMovie.add(movie)
        }
        return listMovie
    }

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_movies.setHasFixedSize(true)
        val listMoviesAdapter = MoviesAdapter(list)
        // RecyclerView node initialized here
        rv_movies.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = listMoviesAdapter
        }
        listMoviesAdapter.setOnItemClickCallback(object: MoviesAdapter.OnItemClickCallback{
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