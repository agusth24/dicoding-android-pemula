package com.example.prototype.mymoviecataloguemade2.ui.tvshow

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.prototype.mymoviecataloguemade2.R
import kotlinx.android.synthetic.main.item_row_tvshows.view.*
import java.math.RoundingMode


class TvshowsAdapter(private val listTvshows: ArrayList<Tvshows>) : RecyclerView.Adapter<TvshowsAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvshow: Tvshows) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(Uri.parse("file:///android_asset/" + tvshow.photo))
                    .apply(RequestOptions().override(99,99))
                    .into(img_tvshow_item_photo)
                tv_tvshow_item_title.text = tvshow.title
                tv_tvshow_item_description.text = tvshow.description
                val rating = tvshow.rating.toDouble() / 100 * 5
                val rounded = rating.toBigDecimal().setScale(1, RoundingMode.UP).toDouble()
                tv_tvshow_item_information.text = rounded.toString() + " | " + tvshow.release

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvshow)}
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_tvshows, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listTvshows.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listTvshows[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Tvshows)
    }
}