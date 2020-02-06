package com.example.prototype.myrecyclerviewmade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.DialogTitle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.myrecyclerviewmade.hero.CardViewHeroAdapter
import com.example.prototype.myrecyclerviewmade.hero.GridHeroAdapter
import com.example.prototype.myrecyclerviewmade.hero.Hero
import com.example.prototype.myrecyclerviewmade.hero.ListHeroAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Hero>()
    private var title = "Mode List"
    private var mode: Int = 0

    companion object {
        private const val STATE_TITLE = "state_string"
        private const val STATE_LIST = "state_list"
        private const val STATE_MODE = "state_mode"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_heroes.setHasFixedSize(true)

        if (savedInstanceState == null) {
            setActionBarTitle(title)
            list.addAll(getListHeroes())
            showRecyclerList()
            mode = R.id.action_list
        } else {
            title = savedInstanceState.getString(STATE_TITLE).toString()
            val stateList = savedInstanceState.getParcelableArrayList<Hero>(STATE_LIST)
            val stateMode = savedInstanceState.getInt(STATE_MODE)

            setActionBarTitle(title)
            if (stateList != null) {
                list.addAll(stateList)
            }
            setMode(stateMode)
        }

        /*list.addAll(getListHeroes())
        showRecyclerList()
        setActionBarTitle(title)*/
    }

    private fun setActionBarTitle(title: String?) {
        supportActionBar?.title = title
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_TITLE, title)
        outState.putParcelableArrayList(STATE_LIST, list)
        outState.putInt(STATE_MODE, mode)
    }

    fun getListHeroes(): ArrayList<Hero> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listHero = ArrayList<Hero>()
        for (position in dataName.indices) {
            val hero = Hero(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rv_heroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rv_heroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object: ListHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private  fun showRecyclerGrid() {
        rv_heroes.layoutManager = GridLayoutManager(this,2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rv_heroes.adapter = gridHeroAdapter

        gridHeroAdapter.setOnItemClickCallback(object: GridHeroAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data)
            }
        })
    }

    private fun showRecyclerCardView() {
        rv_heroes.layoutManager = LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rv_heroes.adapter = cardViewHeroAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun showSelectedHero(hero: Hero) {
        Toast.makeText(this, "Kamu memilih ${hero.name}", Toast.LENGTH_SHORT).show()
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                title = "Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title = "Mode Grid"
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                title = "Mode Card"
                showRecyclerCardView()
            }
        }
        mode = selectedMode
        setActionBarTitle(title)
    }
}
