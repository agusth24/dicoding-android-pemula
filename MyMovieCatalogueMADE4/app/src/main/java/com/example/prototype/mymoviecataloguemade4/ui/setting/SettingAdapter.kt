package com.example.prototype.mymoviecataloguemade4.ui.setting

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.prototype.mymoviecataloguemade4.R
import kotlinx.android.synthetic.main.item_row_setting.view.*

class SettingAdapter(private val listSetting: ArrayList<String>) :
    RecyclerView.Adapter<SettingAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(menuSetting: String) {
            with(itemView) {
                tv_setting_menu.text = menuSetting

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(menuSetting) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_setting, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listSetting.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listSetting[position])
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}

