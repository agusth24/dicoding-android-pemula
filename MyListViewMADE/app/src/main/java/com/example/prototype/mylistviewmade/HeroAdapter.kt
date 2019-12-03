package com.example.prototype.mylistviewmade

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class HeroAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var heroes = arrayListOf<Hero>()

    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if(itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_hero, viewGroup, false)
        }
        val viewHolder = ViewHolder(itemView as View)
        val hero = getItem(position) as Hero
        viewHolder.bind(hero)
        return itemView
    }

    override fun getItem(p0: Int): Any {
        return heroes[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return heroes.size
    }

    private inner class ViewHolder internal constructor(view: View ) {
        private val textName: TextView = view.findViewById(R.id.txt_name)
        private val textDesc: TextView = view.findViewById(R.id.txt_desc)
        private val imgPhoto: ImageView = view.findViewById(R.id.img_photo)

        internal fun bind (hero: Hero) {
            textName.text = hero.name
            textDesc.text = hero.description
            imgPhoto.setImageResource(hero.photo)
        }
    }
}