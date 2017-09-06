package com.xdai.mymovieguide.ui.movie_list;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.R;
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
    protected Type currentType = Type.GENRE;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();


        ButterKnife.bind(this, this.findViewById(android.R.id.content));

        initStateManager();
        toolbar.setVisibility(View.VISIBLE);
        currentType = Type.POPULAR;
        if(getIntent().getExtras()!= null) {
            currentType = (Type) getIntent().getExtras().get("list_type");
        }
        if(savedInstanceState != null) {
            currentType = Type.values()[savedInstanceState.getInt("current_type")];
        }


        if(currentType == Type.GENRE){
            id = getIntent().getExtras().getInt("genre_id");
            toolbar.setTitle( getIntent().getExtras().getString("genre_name"));
        }

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        movie_list.setLayoutManager(linearLayoutManager);
        movieMovieResultList = new ArrayList<>();
        movieListAdapter = new MovieListAdapter(this, movieMovieResultList, imageLoader);
        movie_list.setAdapter(movieListAdapter);
        swipe_refresh_layout.setOnRefreshListener(() -> {
            swipe_refresh_layout.setRefreshing(true);
            movieMovieResultList.clear();
            movieListAdapter.reset();
            movieListPresenter.loadMovieList(currentType, id);
        });
        movieListPresenter.loadMovieList(currentType, id);
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
        movieListPresenter.loadMovieList(Type.POPULAR, 0);
    }

    @Override
    protected void reinitializeStore() {
        movieListPresenter = getStateMaintainer().get(movieListPresenter.getClass().getCanonicalName());
        if (movieListPresenter != null) {
            movieListPresenter.setView(this);
            movieListPresenter.loadMovieList(currentType);
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
    }
}
