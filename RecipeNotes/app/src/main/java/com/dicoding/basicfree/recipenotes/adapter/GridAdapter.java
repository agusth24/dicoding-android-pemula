package com.dicoding.basicfree.recipenotes.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.basicfree.recipenotes.R;
import com.dicoding.basicfree.recipenotes.model.Datas;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder>
{
    private ArrayList<Datas> listDatas;
    private OnItemClickCallback onItemClickCallback;

    public GridAdapter(ArrayList<Datas> list)
    {
        this.listDatas = list;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback)
    {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_grid, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.GridViewHolder holder, int position)
    {
        Glide.with(holder.itemView.getContext())
                .load(listDatas.get(position).getPhoto())
                .apply(new RequestOptions().override(350,350))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onItemClickCallback.onItemClicked(listDatas.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listDatas.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgPhoto;

        GridViewHolder(View itemView)
        {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
