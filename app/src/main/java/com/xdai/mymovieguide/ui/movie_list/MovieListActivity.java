package com.xdai.mymovieguide.ui.movie_list;

import android.os.Bundle;
import android.view.MenuItem;

import com.xdai.mymovieguide.R;

/**
 * Created by xiangli on 9/5/17.
 */

public class MovieListActivity extends MovieListBaseActivity {
    MovieListActivityComponent component;
    private MovieListActivityComponent component() {
        if (component == null) {
            component = DaggerMovieListActivityComponent.builder()
                    .movieListModule(new MovieListModule(this))
                    .build();
        }
        return component;
    }
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_with_recycler_view);
        component().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
