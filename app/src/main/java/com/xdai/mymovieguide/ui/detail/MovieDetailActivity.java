package com.xdai.mymovieguide.ui.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.StateMaintainer;
import com.xdai.mymovieguide.ui.BaseActivity;
import com.xdai.mymovieguide.ui.detail.tabs.DetailPagerAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieDetailActivity extends BaseActivity<IMovieDetailPresenter> implements IMovieDetailView {
    private final String TAG = getClass().getCanonicalName();
    @Inject
    IMovieDetailPresenter movieDetailPresenter;
    @Inject
    IImageLoader imageLoader;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.backdrop)
    ImageView backdrop;

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.detail_tabs)
    TabLayout detail_tabs;
    private MovieDetailActivityComponent component;

    public int getMovie_id() {
        return movie_id;
    }

    private int movie_id;

    public MovieDetailActivityComponent getComponent() {
        return component;
    }

    MovieDetailActivityComponent component() {
        if (component == null) {
            component = DaggerMovieDetailActivityComponent.builder()
                    .movieDetailActivityModule(new MovieDetailActivityModule(this))
                    .build();
        }
        return component;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        component().inject(this);

        ButterKnife.bind(this, this.findViewById(android.R.id.content));
        movie_id = getIntent().getIntExtra("movie_id", 0);

        initStateManager();

        toolbar.setVisibility(View.VISIBLE);

        setTitle(getIntent().getStringExtra("movie_name"));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detail_tabs.addTab(detail_tabs.newTab().setText("Overview"));
        detail_tabs.addTab(detail_tabs.newTab().setText("Casts"));
        detail_tabs.addTab(detail_tabs.newTab().setText("Reviews"));
        detail_tabs.addTab(detail_tabs.newTab().setText("Videos"));

        viewpager.setAdapter(new DetailPagerAdapter(getSupportFragmentManager()));
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(detail_tabs));
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        detail_tabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: " + tab.getPosition());
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    protected void initializeStore() {
        movieDetailPresenter.setView(this);
        getStateMaintainer().put(movieDetailPresenter.getClass().getCanonicalName(), movieDetailPresenter);
        movieDetailPresenter.getMovie(movie_id);

    }

    @Override
    protected  void reinitializeStore() {
        movieDetailPresenter = getStateMaintainer().get(movieDetailPresenter.getClass().getCanonicalName());
        if (movieDetailPresenter != null) {
            movieDetailPresenter.setView(this);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bindMovieDetail(MovieDetail movieDetail) {
        //movie_desc.setText(movieDetail.getOverview());
        //movie_name.setText(movieDetail.getTitle());
        imageLoader.loadImageIntoImageView(this.getString(R.string.image_url) + movieDetail.getBackdrop_path(), backdrop);

    }
}