package com.example.prototype.catatanpohon.ui.kategori;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.prototype.catatanpohon.R;
import com.example.prototype.catatanpohon.model.Kategori;
import com.example.prototype.catatanpohon.rest.ApiClient;
import com.example.prototype.catatanpohon.rest.responses.KategoriResponse;
import com.example.prototype.catatanpohon.rest.services.KategoriService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriFragment extends Fragment
{
    private RecyclerView mRecyclerView;
    private KategoriAdapter mKategoriAdapter;
    private List<Kategori> mKategori = new ArrayList<>();
    private Call<KategoriResponse> mCall;
    private KategoriViewModel mViewModel;

    public KategoriFragment() {}

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState)
//    {
//        super.onCreate( savedInstanceState );
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate( R.layout.fragment_kategori, container, false );
        mRecyclerView = root.findViewById( R.id.item_kategori );
//        mViewModel = ViewModelProviders.of(this).get(KategoriViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_kategori, container, false);
//        final TextView textView = root.findViewById(R.id.text_kategori);
//        mViewModel.getText().observe(this, new Observer<String>()
//        {
//            @Override
//            public void onChanged(@Nullable String s)
//            {
//                textView.setText(s);
//            }
//        });

        FloatingActionButton fab = root.findViewById(R.id.fab_kategori);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent moveIntent = new Intent(getActivity(), KategoriForm.class);
                startActivity(moveIntent);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        setupRecyclerView(mRecyclerView);
        fetchInitialKategori();
    }

    private void fetchInitialKategori()
    {
        mKategori.clear();
        fetchKategori();
    }

    private void fetchKategori()
    {
        KategoriService kategoriService = ApiClient.getClient().create( KategoriService.class );
        mCall = kategoriService.getListDataKetegori();

        mCall.enqueue( new Callback<KategoriResponse>()
        {
            @Override
            public void onResponse(Call<KategoriResponse> call, Response<KategoriResponse> response)
            {
                if(response.isSuccessful())
                {
                    KategoriResponse kategoriResponse = response.body();
                    List<Kategori> kategoris = kategoriResponse.getListDataKetegori();
                    Toast.makeText( getActivity(), "onResponse(): Kategori Size = " + kategoris.size(), Toast.LENGTH_SHORT ).show();

                    mKategori.addAll( kategoris );
                } else
                {
                    int statusCode = response.code();
                    Toast.makeText( getActivity(), "onResponse(): Error Code = " + statusCode, Toast.LENGTH_SHORT ).show();
                }
            }

            @Override
            public void onFailure(Call<KategoriResponse> call, Throwable t)
            {
                Toast.makeText( getActivity(), t.getMessage(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView)
    {
        mKategoriAdapter = new KategoriAdapter(mKategori);
        LinearLayoutManager layoutManager = new LinearLayoutManager( getContext() );
        recyclerView.setLayoutManager( layoutManager );

    }
}
