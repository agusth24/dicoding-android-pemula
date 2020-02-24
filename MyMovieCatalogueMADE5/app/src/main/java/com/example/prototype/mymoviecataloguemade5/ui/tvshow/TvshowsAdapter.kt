package com.example.prototype.mymoviecataloguemade5.ui.tvshow

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.mymoviecataloguemade5.R
import kotlinx.android.synthetic.main.item_row_tvshows.view.*


class TvshowsAdapter : RecyclerView.Adapter<TvshowsAdapter.ListViewHolder>() {
    private val mData = ArrayList<Tvshows>()
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setData(items: ArrayList<Tvshows>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Tvshows> {
        return mData
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(tvshow: Tvshows) {
            with(itemView) {
                val overview = if (tvshow.description.toString().isEmpty()) {
                    resources.getString(R.string.no_desc)
                } else {
                    tvshow.description
                }

                Glide.with(itemView.context)
                    .load(Uri.parse("https://image.tmdb.org/t/p/w185/" + tvshow.photo))
                    .apply(RequestOptions().override(99, 99))
                    .into(img_tvshow_item_photo)
                tv_tvshow_item_title.text = tvshow.title
                tv_tvshow_item_description.text = overview
                tv_tvshow_item_information.text = tvshow.rating.toString() + " | " + tvshow.release

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvshow) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_tvshows, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Tvshows)
    }
}