package com.xdai.mymovieguide.ui.movie_list;

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
        setContentView(R.layout.activity_movie_list);
        component().inject(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
