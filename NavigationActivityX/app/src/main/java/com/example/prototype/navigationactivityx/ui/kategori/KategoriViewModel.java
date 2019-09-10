package com.example.prototype.navigationactivityx.ui.kategori;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

public class KategoriViewModel extends ViewModel
{
    private MutableLiveData<String> mText;

    public KategoriViewModel()
    {
        mText = new MutableLiveData<>();
        mText.setValue("This is kategori fragment");
    }

    public LiveData<String> getText()
    {
        return mText;
    }
}
