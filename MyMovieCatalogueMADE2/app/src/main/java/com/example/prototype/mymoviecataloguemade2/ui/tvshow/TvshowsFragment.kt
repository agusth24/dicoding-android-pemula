package com.example.prototype.mymoviecataloguemade2.ui.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade2.R
import kotlinx.android.synthetic.main.fragment_tvshows.*

class TvshowsFragment : Fragment() {

    private val list = ArrayList<Tvshows>()

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
            list.addAll(getListTvshow())
        } else {
            val stateList = savedInstanceState.getParcelableArrayList<Tvshows>(STATE_LIST)
            if(stateList != null) {
                list.addAll(stateList)
            }
        }
    }

    private fun getListTvshow(): ArrayList<Tvshows> {
        val dataTitle = resources.getStringArray(R.array.data_tvshow_title)
        val dataDescription = resources.getStringArray(R.array.data_tvshow_desc)
        val dataPhoto = resources.getStringArray(R.array.data_tvshow_photos)
        val dataRelease = resources.getStringArray(R.array.data_tvshow_release)
        val dataRating = resources.getIntArray(R.array.data_tvshow_rating)
        val listMovie = ArrayList<Tvshows>()
        for (position in dataTitle.indices) {
            val movie = Tvshows(
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

        rv_tvshows.setHasFixedSize(true)
        val listTvshowAdapater = TvshowsAdapter(list)
        // RecyclerView node initialized here
        rv_tvshows.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = listTvshowAdapater
        }
        listTvshowAdapater.setOnItemClickCallback(object: TvshowsAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Tvshows) {
                showSelectedItem(data)
            }
        })
    }

    private fun showSelectedItem(tvshow: Tvshows) {
        val moveActivity = Intent(context, TvshowsDetailActivity::class.java)
        moveActivity.putExtra(TvshowsDetailActivity.OBJECT_TVSHOW, tvshow)
        startActivity(moveActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshows, container, false)
    }
}