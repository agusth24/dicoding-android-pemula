package com.example.prototype.mymoviecataloguemade1

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView
import com.example.prototype.mymoviecataloguemade1.movie.Movie
import com.example.prototype.mymoviecataloguemade1.movie.MovieAdapter
import com.example.prototype.mymoviecataloguemade1.movie.MovieDetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var dataTitle: Array<String>
    private lateinit var dataDesc: Array<String>
    private lateinit var dataRating: Array<String>
    private lateinit var dataRelease: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.lv_list)
        adapter = MovieAdapter(this)
        listView.adapter = adapter

        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            val moveActivity = Intent(this@MainActivity, MovieDetailActivity::class.java)
            moveActivity.putExtra(MovieDetailActivity.OBJECT_MOVIE, movies[position])
            startActivity(moveActivity)
        }
    }

    private fun prepare() {
        dataTitle = resources.getStringArray(R.array.data_title)
        dataDesc = resources.getStringArray(R.array.data_desc)
        dataRating = resources.getStringArray(R.array.data_rating)
        dataRelease = resources.getStringArray(R.array.data_release)
        dataPhoto = resources.obtainTypedArray(R.array.data_photos)
    }

    private fun addItem() {
        for (position in dataTitle.indices) {
            val movie = Movie(
                dataPhoto.getResourceId(position, -1),
                dataTitle[position],
                dataDesc[position],
                dataRelease[position],
                dataRating[position]
            )
            movies.add(movie)
        }
        adapter.movies = movies
        dataPhoto.recycle()
    }
}
