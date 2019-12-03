package com.example.prototype.catatanpohon;

import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.example.prototype.catatanpohon.ui.home.HomeFragment;
import com.example.prototype.catatanpohon.ui.kategori.KategoriFragment;
import com.example.prototype.catatanpohon.ui.webview.WebViewFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private String title = "Home";

    private void setActionBarTitle(String title)
    {
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
        }
    }

    private void setupNavigation(Bundle savedInstanceState)
    {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(savedInstanceState == null)
        {
            Fragment fragment = new HomeFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.content_frame, fragment);
            ft.addToBackStack(null);
            ft.commit();
            setActionBarTitle(title);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigation(savedInstanceState);
    }

    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
    {
        int id = menuItem.getItemId();
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        String title = "Home";
        switch (id)
        {
            case R.id.home:
                fragment = new HomeFragment();
                title = "Home";
            break;
            case R.id.kategori:
                fragment = new KategoriFragment();
                title = "Kategori";
            break;
            case R.id.fb:
                title = "Facebook";
                bundle.putString("url", "https://www.facebook.com/agus.tri.haryono");
                fragment = new WebViewFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.instagram:
                title = "Instagram";
                bundle.putString("url", "https://www.instagram.com/agustharyono24");
                fragment = new WebViewFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.twitter:
                title = "Twitter";
                bundle.putString("url", "https://twitter.com/agusTharyono");
                fragment = new WebViewFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.github:
                title = "Github";
                bundle.putString("url", "https://github.com/agusth24");
                fragment = new WebViewFragment();
                fragment.setArguments(bundle);
                break;
            case R.id.youtube:
                title = "Youtube";
                bundle.putString("url", "https://www.youtube.com/channel/UC-v3hTp95JlbbvfWsM0XYmQ");
                fragment = new WebViewFragment();
                fragment.setArguments(bundle);
                break;
        }

        if(fragment!=null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
            setActionBarTitle(title);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
