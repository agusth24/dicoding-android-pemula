package com.dicoding.basicfree.recipenotes.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.basicfree.recipenotes.R;
import com.dicoding.basicfree.recipenotes.model.Datas;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
{
    private ArrayList<Datas> listDatas;
    private Context mContext;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback)
    {
        this.onItemClickCallback = onItemClickCallback;
    }

    public ListAdapter(Context context, ArrayList<Datas> list)
    {
        //super(context);
        this.listDatas = list;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ListAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ListViewHolder holder, int position)
    {
        Datas datas = listDatas.get(position);
        Glide.with(holder.itemView.getContext())
                .load(datas.getPhoto())
                .apply(new RequestOptions().override(55,55))
                .into(holder.imgPhoto);

        holder.tvJudul.setText(datas.getJudul());

        try {
            InputStream is = mContext.getAssets().open(datas.getDetails());

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            String text = new String(buffer);

            // Finally stick the string into the text view.
            // Replace with whatever you need to have the text into.
            text = text.replace("\n", "").replace("\r", "");
            holder.tvDetails.setText(text);

        } catch (IOException e) {
            // Should never happen!
            throw new RuntimeException(e);
        }
        //holder.tvDetails.setText(datas.getDetails());
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

    public class ListViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imgPhoto;
        TextView tvJudul, tvDetails;

        ListViewHolder(View itemView)
        {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvJudul = itemView.findViewById(R.id.tv_item_name);
            tvDetails = itemView.findViewById(R.id.tv_item_detail);

        }
    }
}
