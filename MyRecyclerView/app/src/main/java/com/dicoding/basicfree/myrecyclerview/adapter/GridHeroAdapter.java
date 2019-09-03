package com.dicoding.basicfree.myrecyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.basicfree.myrecyclerview.R;
import com.dicoding.basicfree.myrecyclerview.model.Hero;

import java.util.ArrayList;

public class GridHeroAdapter extends RecyclerView.Adapter<GridHeroAdapter.GridViewHolder>
{
    private ArrayList<Hero> listHero;

    public  GridHeroAdapter(ArrayList<Hero> list)
    {
        this.listHero = list;
    }

    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid_hero, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position)
    {
        Glide.with(holder.itemView.getContext())
                .load(listHero.get(position).getPhoto())
                .apply(new RequestOptions().override(350,350))
                .into(holder.imgPhoto);
    }

    @Override
    public int getItemCount()
    {
        return listHero.size();
    }

    class GridViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgPhoto;

        GridViewHolder(View itemView)
        {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
