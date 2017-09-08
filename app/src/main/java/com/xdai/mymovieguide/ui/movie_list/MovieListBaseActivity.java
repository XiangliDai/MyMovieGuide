package com.xdai.mymovieguide.ui.movie_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.EndlessRecyclerViewScrollListener;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.ui.BaseActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class MovieListBaseActivity extends BaseActivity<IMovieListPresenter> implements IMovieListView {
    private final String TAG = getClass().getCanonicalName();
    public enum Type {
        POPULAR, TOPRATED, NOWPLAYING, UPCOMING, GENRE
    }
    @Inject
    protected IMovieListPresenter movieListPresenter;

    @Inject
    protected IImageLoader imageLoader;

    @Bind(R.id.movie_list)
    protected RecyclerView movie_list;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipe_refresh_layout;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected MovieListAdapter movieListAdapter;
    protected ArrayList<MovieResult> movieMovieResultList;
    protected Type currentType;
    private int id = 0;
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();


        ButterKnife.bind(this, this.findViewById(android.R.id.content));

        initStateManager();
        toolbar.setVisibility(View.VISIBLE);

        if(getIntent().getExtras()!= null) {
            currentType = (Type) getIntent().getExtras().get("list_type");
        }
        if(savedInstanceState != null) {
            currentType = Type.values()[savedInstanceState.getInt("current_type")];
        }

        if(currentType == null){
            currentType = Type.POPULAR;
        }

        if(currentType == Type.GENRE && getIntent().getExtras() != null){
            id = getIntent().getExtras().getInt("genre_id");
            toolbar.setTitle(getIntent().getExtras().getString("genre_name"));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_list.setLayoutManager(linearLayoutManager);
        if(currentType != Type.GENRE) {
            // Retain an instance so that you can call `resetState()` for fresh searches
            scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    movieListPresenter.loadMovieList(currentType, false, id);
                }
            };
            // Adds the scroll listener to RecyclerView
            movie_list.addOnScrollListener(scrollListener);
        }

        movieMovieResultList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(this, movieMovieResultList, imageLoader);
        movie_list.setAdapter(movieListAdapter);
        swipe_refresh_layout.setOnRefreshListener(() -> {
            swipe_refresh_layout.setRefreshing(true);
            movieMovieResultList.clear();
            //movieListAdapter.reset();
            movieListPresenter.loadMovieList(currentType, true, id);
        });
        movieListPresenter.loadMovieList(currentType, true, id);
    }

    protected abstract void setContentView();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("current_type", currentType.ordinal());
    }

    @Override
    protected void initializeStore() {
        movieListPresenter.setView(this);
        getStateMaintainer().put(movieListPresenter.getClass().getCanonicalName(), movieListPresenter);
    }

    @Override
    protected void reinitializeStore() {
        movieListPresenter = getStateMaintainer().get(movieListPresenter.getClass().getCanonicalName());
        if (movieListPresenter != null) {
            movieListPresenter.setView(this);
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
        swipe_refresh_layout.setRefreshing(false);
        movieMovieResultList.addAll(movieResults);
        movieListAdapter.notifyDataSetChanged();
    }
}
