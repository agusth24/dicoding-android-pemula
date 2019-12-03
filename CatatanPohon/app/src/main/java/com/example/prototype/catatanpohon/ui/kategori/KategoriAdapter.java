package com.example.prototype.catatanpohon.ui.kategori;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.prototype.catatanpohon.R;
import com.example.prototype.catatanpohon.model.Kategori;

import java.util.List;

public class KategoriAdapter extends RecyclerView.Adapter<KategoriAdapter.ViewHolder>
{
    private List<Kategori> mKategori;

    public KategoriAdapter(List<Kategori> kategoris)
    {
        mKategori = kategoris;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from( context );
        View view = inflater.inflate( R.layout.item_kategori, viewGroup, false );
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Kategori kategori = mKategori.get(position);
        holder.katNama.setText( kategori.getKatNama() );
    }

    @Override
    public int getItemCount()
    {
        return mKategori.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView katNama;
        public ViewHolder(@NonNull View itemView)
        {
            super( itemView );
            katNama = itemView.findViewById( R.id.text_kategori );
        }
    }
}
