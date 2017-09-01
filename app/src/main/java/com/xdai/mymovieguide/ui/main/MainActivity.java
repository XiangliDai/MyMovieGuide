package com.xdai.mymovieguide.ui.main;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.StateMaintainer;
import com.xdai.mymovieguide.mvp.IPresenter;
import com.xdai.mymovieguide.ui.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<IMainPresenter> implements IMainView {
    private final String TAG = getClass().getCanonicalName();

    @Inject
    IMainPresenter mainPresenter;

    @Inject
    IImageLoader imageLoader;

    @Bind(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Bind(R.id.movie_list)
    RecyclerView movie_list;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipe_refresh_layout;

    private MovieListAdapter movieListAdapter;
    private MainActivityComponent component;
    private ArrayList<MovieResult> movieMovieResultList;
    private int currentType = 0;

    MainActivityComponent component() {
        if (component == null) {
            component = DaggerMainActivityComponent.builder()
                    .mainActivityModule(new MainActivityModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        component().inject(this);

        ButterKnife.bind(this, this.findViewById(android.R.id.content));
        if(savedInstanceState != null){
            currentType = savedInstanceState.getInt("current_type");
        }
        initStateManager();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_list.setLayoutManager(linearLayoutManager);
        movieMovieResultList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(this, movieMovieResultList, imageLoader);
        movie_list.setAdapter(movieListAdapter);
        swipe_refresh_layout.setOnRefreshListener(() -> {
            swipe_refresh_layout.setRefreshing(true);
            movieMovieResultList.clear();
            movieListAdapter.reset();
            mainPresenter.refresh(currentType);
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.action_popular:
                            currentType = 0;
                            mainPresenter.loadPopularMovies();
                            break;
                        case R.id.action_top_rated:
                            currentType = 1;
                            mainPresenter.loadTopRatedMovies();
                            break;
                        case R.id.action_now_playing:
                            currentType = 2;
                            mainPresenter.loadNowPlayingMovies();
                            break;
                        case R.id.action_upcoming:
                            currentType = 3;
                            mainPresenter.loadUpcomingMovies();
                            break;
                    }
                    return true;
                });
        Log.d(TAG, "current type: " + currentType);
        bottomNavigationView.getMenu().getItem(currentType).setChecked(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current_type", currentType);
    }

    @Override
    protected void initializeStore() {
        mainPresenter.setView(this);
        getStateMaintainer().put(mainPresenter.getClass().getCanonicalName(), mainPresenter);
        mainPresenter.loadPopularMovies();
    }

    @Override
    protected void reinitializeStore() {
        mainPresenter = getStateMaintainer().get(mainPresenter.getClass().getCanonicalName());
        if (mainPresenter != null) {
            mainPresenter.setView(this);
            mainPresenter.refresh(currentType);
        }
    }

    @Override
    public void showProgress() {
        swipe_refresh_layout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh_layout.setRefreshing(false);
    }

    @Override
    public void bindMovieList(ArrayList<MovieResult> movieResults) {
        movieMovieResultList.clear();
        swipe_refresh_layout.setRefreshing(false);
        movieMovieResultList.addAll(movieResults);
        movieListAdapter.notifyDataSetChanged();
        //movie_list.setAdapter(movieListAdapter);
    }
}
