package com.example.prototype.catatanpohon.app;

import android.app.Application;

import com.example.prototype.catatanpohon.rest.ApiClient;

public class App extends Application
{
    private static ApiClient apiClient;

    @Override
    public void onCreate()
    {
        super.onCreate();
        apiClient = new ApiClient();
    }
    public static ApiClient getApiClient()
    {
        return apiClient;
    }
}
