package com.example.prototype.mymoviecataloguemade1.movie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.prototype.mymoviecataloguemade1.R

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var movies = arrayListOf<Movie>()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView: View?
        val viewHolder: ViewHolder
        if (view == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.movie_item, viewGroup, false)
            viewHolder = ViewHolder(itemView as View)
            itemView.tag = viewHolder
        } else {
            itemView = view
            viewHolder = itemView.tag as ViewHolder
        }

        val movie = getItem(position) as Movie
        viewHolder.bind(movie)
        return itemView
    }

    override fun getItem(position: Int): Any {
        return movies[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return movies.size
    }

    private inner class ViewHolder internal constructor(view: View?) {
        private val textTitle: TextView? = view?.findViewById(R.id.txt_title)
        private val textDesc: TextView? = view?.findViewById(R.id.txt_desc)
        private val textInfo: TextView? = view?.findViewById(R.id.txt_info)
        private val imgPhoto: ImageView? = view?.findViewById(R.id.img_photo)

        internal fun bind(movie: Movie) {
            textTitle?.text = movie.title
            textDesc?.text = movie.description
            textInfo?.text = (movie.rating + " | " + movie.release)
            imgPhoto?.setImageResource(movie.photo)

        }
    }
}
