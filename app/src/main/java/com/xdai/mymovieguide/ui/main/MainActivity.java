package com.xdai.mymovieguide.ui.main;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.NavigateService;
import com.xdai.mymovieguide.ui.movie_list.MovieListActivity;
import com.xdai.mymovieguide.ui.movie_list.MovieListModule;

import java.lang.reflect.Field;

import butterknife.Bind;

public class MainActivity extends MovieListActivity {
    private final String TAG = getClass().getCanonicalName();


    MainActivityComponent component;
    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    private MainActivityComponent component() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .movieListModule(new MovieListModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);

        component().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        toolbar.setTitle(" ");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    movieMovieResultList.clear();
                    switch (item.getItemId()) {
                        case R.id.action_popular:
                            currentType = Type.POPULAR;
                            break;
                        case R.id.action_top_rated:
                            currentType = Type.TOPRATED;
                            break;
                        case R.id.action_now_playing:
                            currentType = Type.NOWPLAYING;
                            break;
                        case R.id.action_upcoming:
                            currentType = Type.UPCOMING;
                            break;
                    }
                    movieListPresenter.loadMovieList(currentType, true);
                    return true;
                });
        Log.d(TAG, "current type: " + currentType);
        bottomNavigationView.getMenu().getItem(currentType.ordinal()).setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query != null) {
                    NavigateService.launchSearchActivity(MainActivity.this, query);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (newText != null) {
                }
                return false;
            }
        });
        return true;
    }

}
