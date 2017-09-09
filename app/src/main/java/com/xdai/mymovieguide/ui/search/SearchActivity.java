package com.xdai.mymovieguide.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mymovieguide.xdai.network.response.SearchResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.EndlessRecyclerViewScrollListener;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.NavigateService;
import com.xdai.mymovieguide.ui.BaseActivity;
import com.xdai.mymovieguide.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 9/5/17.
 */

public class SearchActivity  extends BaseActivity<ISearchPresenter> implements ISearchView {
    @Inject
    protected ISearchPresenter searchPresenter;

    @Inject
    protected IImageLoader imageLoader;

    @Bind(R.id.recycler_list)
    protected RecyclerView search_list;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout swipe_refresh_layout;

    @Bind(R.id.toolbar)
    protected Toolbar toolbar;

    protected SearchAdapter searchAdapter;
    protected ArrayList<SearchResult> searchResultArrayList;

    private EndlessRecyclerViewScrollListener scrollListener;
    String query;
    SearchActivityComponent component;

    SearchActivityComponent component() {
        if (component == null) {
            component = DaggerSearchActivityComponent.builder()
                    .searchActivityModule(new SearchActivityModule(this))
                    .build();
        }
        return component;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_recycler_view);
        component().inject(this);

        ButterKnife.bind(this, this.findViewById(android.R.id.content));

        initStateManager();
        toolbar.setTitle(" ");

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(getIntent().getExtras() != null){
            query = getIntent().getStringExtra("query");
        }
        if(savedInstanceState != null){
            query = savedInstanceState.getString("query");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        search_list.setLayoutManager(linearLayoutManager);
        search_list.setHasFixedSize(false);
            // Retain an instance so that you can call `resetState()` for fresh searches
            scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    // Triggered only when new data needs to be appended to the list
                    // Add whatever code is needed to append new items to the bottom of the list
                    searchPresenter.loadSearchResultByQuery(query, false);
                }
            };
            // Adds the scroll listener to RecyclerView
        search_list.addOnScrollListener(scrollListener);


        searchResultArrayList = new ArrayList<>();
        searchAdapter = new SearchAdapter(this, searchResultArrayList, imageLoader);
        search_list.setAdapter(searchAdapter);

        swipe_refresh_layout.setOnRefreshListener(() -> {
            swipe_refresh_layout.setRefreshing(true);
            searchResultArrayList.clear();
            //movieListAdapter.reset();
            searchPresenter.loadSearchResultByQuery(query, true);
        });
        searchPresenter.loadSearchResultByQuery(query, true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", query);
    }

    @Override
    protected void initializeStore() {
        searchPresenter.setView(this);
        getStateMaintainer().put(searchPresenter.getClass().getCanonicalName(), searchPresenter);
    }

    @Override
    protected void reinitializeStore() {
        searchPresenter = getStateMaintainer().get(searchPresenter.getClass().getCanonicalName());
        if (searchPresenter != null) {
            searchPresenter.setView(this);
        }
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
                   searchPresenter.loadSearchResultByQuery(query, true);
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

    @Override
    public void showProgress() {
        swipe_refresh_layout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh_layout.setRefreshing(false);
    }

    @Override
    public void bindSearchResults(List<SearchResult> searchResults) {
        swipe_refresh_layout.setRefreshing(false);
        searchResultArrayList.addAll(searchResults);
        searchAdapter.notifyDataSetChanged();
    }
}
