package com.dicoding.basicfree.recipenotes;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.dicoding.basicfree.recipenotes.adapter.GridAdapter;
import com.dicoding.basicfree.recipenotes.adapter.ListAdapter;
import com.dicoding.basicfree.recipenotes.adapter.OnItemClickCallback;
import com.dicoding.basicfree.recipenotes.model.Datas;
import com.dicoding.basicfree.recipenotes.model.RecipeDatas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView rvDatas;
    private ArrayList<Datas> list = new ArrayList<>();
    private String title = "Recipe Notes";

    private void setActionBarTitle(String title)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
        }
    }

    private void showSelectedData(Datas datas) {
        Intent moveIntent = new Intent(this, DetailActivity.class);
        moveIntent.putExtra(DetailActivity.TV_JUDUL, datas.getJudul());
        moveIntent.putExtra(DetailActivity.TV_DETAIL, datas.getDetails());
        moveIntent.putExtra(DetailActivity.IMAGE_PHOTOS, datas.getPhoto());
        startActivity(moveIntent);
    }

    private void showAbout()
    {
        Intent moveIntent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(moveIntent);
    }

    private void showRecyclerList()
    {
        rvDatas.setLayoutManager(new LinearLayoutManager(this));
        ListAdapter listAdapter = new ListAdapter(this , list);
        rvDatas.setAdapter(listAdapter);

        listAdapter.setOnItemClickCallback(new OnItemClickCallback(){
            @Override
            public void onItemClicked(Datas data)
            {
                showSelectedData(data);
            }
        });
    }

    private void showRecyclerGrid()
    {
        rvDatas.setLayoutManager(new GridLayoutManager(this,2));
        GridAdapter gridAdapter = new GridAdapter(list);
        rvDatas.setAdapter(gridAdapter);

        gridAdapter.setOnItemClickCallback(new OnItemClickCallback(){
            @Override
            public void onItemClicked(Datas data)
            {
                showSelectedData(data);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        rvDatas = findViewById(R.id.rv_list);
        rvDatas.setHasFixedSize(true);

        list.addAll(RecipeDatas.getListData());
        showRecyclerList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int itemId)
    {
        switch (itemId)
        {
            case R.id.action_list:
                showRecyclerList();
                break;

            case R.id.action_grid:
                showRecyclerGrid();
                break;

            case R.id.action_about:
                showAbout();
                break;
        }
    }
}
