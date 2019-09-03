package com.dicoding.basicfree.myrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.basicfree.myrecyclerview.adapter.CardViewHeroAdapter;
import com.dicoding.basicfree.myrecyclerview.adapter.GridHeroAdapter;
import com.dicoding.basicfree.myrecyclerview.adapter.ListHeroAdapter;
import com.dicoding.basicfree.myrecyclerview.model.Hero;
import com.dicoding.basicfree.myrecyclerview.model.HeroesData;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvHeroes;
    private ArrayList<Hero> list = new ArrayList<>();
    private String title = "Menu List";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvHeroes = findViewById(R.id.rv_hero);
        rvHeroes.setHasFixedSize(true);

        list.addAll(HeroesData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList()
    {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        ListHeroAdapter listHeroAdapter = new ListHeroAdapter(list);
        rvHeroes.setAdapter(listHeroAdapter);
    }

    private  void  showRecyclerGrid()
    {
        rvHeroes.setLayoutManager((new GridLayoutManager(this,2)));
        GridHeroAdapter gridHeroAdapter = new GridHeroAdapter(list);
        rvHeroes.setAdapter(gridHeroAdapter);
    }

    private void showRecyclerCardView()
    {
        rvHeroes.setLayoutManager(new LinearLayoutManager(this));
        CardViewHeroAdapter cardViewHeroAdapter = new CardViewHeroAdapter(list);
        rvHeroes.setAdapter(cardViewHeroAdapter);
    }

    private void setActionBarTitle(String title)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle((title));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item)
    {
        setMode(item.getItemId());
        return  super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode)
    {
        switch (selectedMode)
        {
            case R.id.action_list:
                title = "Mode List";
                setActionBarTitle(title);
                showRecyclerList();
                break;

            case R.id.action_grid:
                title = "Mode Grid";
                setActionBarTitle(title);
                showRecyclerGrid();
                break;

            case R.id.action_cardview:
                title = "Mode Card";
                setActionBarTitle(title);
                showRecyclerCardView();
                break;

        }
    }
}
