package com.example.prototype.navigationactivityx.ui.webview;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.prototype.navigationactivityx.R;
import com.example.prototype.navigationactivityx.ui.kategori.KategoriViewModel;

public class WebViewFragment extends Fragment
{
    public WebView webView;
    String str_url;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        str_url = this.getArguments().getString("url");
        webView = view.findViewById(R.id.webView);
        callWebClient(str_url);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_web_view, container, false);
        return root;
    }

    private void callWebClient(String str_url)
    {
        webView.setWebViewClient(new myWebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(str_url);
    }

    public class myWebViewClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon)
        {

        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            Log.e("Url: ",url);
            view.loadUrl(url);
            return true;
        }
    }
}
