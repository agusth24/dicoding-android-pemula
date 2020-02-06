package com.example.prototype.mymoviecataloguemade2.ui.movies

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.mymoviecataloguemade2.R
import kotlinx.android.synthetic.main.item_row_movies.view.*
import java.math.RoundingMode


class MoviesAdapter(private val listMovies: ArrayList<Movies>) : RecyclerView.Adapter<MoviesAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movies) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/" + movie.photo))
                    .apply(RequestOptions().transform(RoundedCorners(16)))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(img_movie_item_photo)
                tv_movie_item_title.text = movie.title
                tv_movie_item_description.text = movie.description
                val rating = movie.rating.toDouble() / 100 * 5
                val rounded = rating.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
                tv_movie_item_information.text = rounded.toString() + " | " + movie.release

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(movie)}
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_movies, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movies)
    }
}

