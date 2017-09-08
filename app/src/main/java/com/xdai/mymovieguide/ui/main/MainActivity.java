package com.xdai.mymovieguide.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.View;

import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.ui.movie_list.MovieListActivity;
import com.xdai.mymovieguide.ui.movie_list.MovieListModule;

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
        toolbar.setVisibility(View.GONE);


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

}
