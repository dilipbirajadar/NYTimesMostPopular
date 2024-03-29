package com.nytimesmostpopular.activity;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.nytimesmostpopular.R;
import com.nytimesmostpopular.adapter.NytListAdapter;
import com.nytimesmostpopular.model.mostpopular.NycMostPopularRsponse;
import com.nytimesmostpopular.model.mostpopular.ResultResponse;
import com.nytimesmostpopular.mostviewdPresenter.MostviewdPresenterImpli;
import com.nytimesmostpopular.mostviewdPresenter.iMostPopularView;
import com.nytimesmostpopular.utilites.LogUtils;

import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;

import java.util.List;

/**
 * Created by Dilip Birajadar on 2019-12-14.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, iMostPopularView {

    private MostviewdPresenterImpli mostviewdPresenterImpli;
    private RecyclerView recyclerView;
    private List<ResultResponse> resultResponseList;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.nyt_list);
        mostviewdPresenterImpli = new MostviewdPresenterImpli(this);
         pd = new ProgressDialog(MainActivity.this);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        /*
        * call the mostPopular API
        * */
        callMostPopularApi();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void callMostPopularApi() {
        mostviewdPresenterImpli.mostPopularApiCall();

    }

    @Override
    public void mostPopularApiResponse(String response) {
        LogUtils.errorLog("Nyc api response: ",response);
        NycMostPopularRsponse nycMostPopularRsponse = new Gson().fromJson(response, NycMostPopularRsponse.class);

        resultResponseList = nycMostPopularRsponse.getResults();
        NytListAdapter adapter = new NytListAdapter(resultResponseList, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
        ViewCompat.setNestedScrollingEnabled(recyclerView, false);

    }

    @Override
    public void mostPopularApiErrorResponse(String response, int code) {
        LogUtils.errorLog("error response: ",response);
    }

    @Override
    public void showLoadingBar() {
        pd = new ProgressDialog(MainActivity.this);
        pd.setMessage("Loading Nyt News");
        pd.show();

    }

    @Override
    public void hideLoadingBar() {
        pd.cancel();
    }
}
