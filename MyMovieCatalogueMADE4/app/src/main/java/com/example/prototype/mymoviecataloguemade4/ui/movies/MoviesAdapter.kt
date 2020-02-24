package com.example.prototype.mymoviecataloguemade4.ui.movies

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.mymoviecataloguemade4.R
import kotlinx.android.synthetic.main.item_row_movies.view.*


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {
    private val mData = ArrayList<Movies>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun getData(): ArrayList<Movies> {
        return mData
    }

    fun setData(items: ArrayList<Movies>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        position: Int
    ): MovieViewHolder {
        val mView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_movies, viewGroup, false)
        return MovieViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(movieViewHolder: MovieViewHolder, position: Int) {
        movieViewHolder.bind(mData[position])
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movies) {
            with(itemView) {
                val overview = if (movie.description.toString().isEmpty()) {
                    resources.getString(R.string.no_desc)
                } else {
                    movie.description
                }

                Glide.with(itemView.context)
                    .load("https://image.tmdb.org/t/p/w185/" + movie.photo)
                    .apply(RequestOptions().transform(RoundedCorners(16)))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_movie_item_photo)
                tv_movie_item_title.text = movie.title
                tv_movie_item_description.text = overview
                tv_movie_item_information.text = movie.rating.toString() + " | " + movie.release

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movies)
    }
}

