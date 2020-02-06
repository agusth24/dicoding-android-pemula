package com.example.prototype.mymoviecataloguemade2.ui.setting


import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prototype.mymoviecataloguemade2.R
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * A simple [Fragment] subclass.
 */
class SettingFragment : Fragment() {
    private val list = ArrayList<String>()

    companion object {
        private const val STATE_LIST = "state_list"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putStringArrayList(STATE_LIST, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(savedInstanceState == null) {
            list.addAll(getListSettingMenu())
        } else {
            val stateList = savedInstanceState.getStringArrayList(STATE_LIST)
            if(stateList != null) {
                list.addAll(stateList)
            }
        }
    }

    private fun getListSettingMenu(): ArrayList<String> {
        val settingMenu = resources.getStringArray(R.array.setting_menu)
        val listSettingMenu = ArrayList<String>()
        for (position in settingMenu.indices) {
            val setMenu = settingMenu[position]
            listSettingMenu.add(setMenu)
        }
        return listSettingMenu
    }

    // populate the views now that the layout has been inflated
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_setting.setHasFixedSize(true)
        val listSettingAdapter = SettingAdapter(list)
        // RecyclerView node initialized here
        rv_setting.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(context)
            // set the custom adapter to the RecyclerView
            adapter = listSettingAdapter
        }
        listSettingAdapter.setOnItemClickCallback(object: SettingAdapter.OnItemClickCallback{
            override fun onItemClicked(data: String) {
                showSelectedItem(data)
            }
        })
    }

    private fun showSelectedItem(itemSelected: String) {
        val position = list.indexOf(itemSelected)
        if (position == 0) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }


}
