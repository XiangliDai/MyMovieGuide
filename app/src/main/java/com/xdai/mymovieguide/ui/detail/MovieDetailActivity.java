package com.xdai.mymovieguide.ui.detail;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.ui.BaseActivity;
import com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter;
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
    @Bind(R.id.image_movie_backdrop)
    ImageView backdrop;

    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.detail_tabs)
    TabLayout detail_tabs;

    MovieDetailActivityComponent component;

    public int getMovie_id() {
        return movie_id;
    }

    private int movie_id;
    private String movie_name;
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
        movie_name = getIntent().getStringExtra("movie_name");
        initStateManager();

        toolbar.setVisibility(View.VISIBLE);

        setTitle(movie_name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        detail_tabs.addTab(detail_tabs.newTab().setText(R.string.overview));
        detail_tabs.addTab(detail_tabs.newTab().setText(R.string.casts));
        detail_tabs.addTab(detail_tabs.newTab().setText(R.string.reviews));
        detail_tabs.addTab(detail_tabs.newTab().setText(R.string.trailers));
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
        movieDetailPresenter.getMovie(movie_id);
    }


    @Override
    protected void initializeStore() {
        movieDetailPresenter.setView(this);
        getStateMaintainer().put(movieDetailPresenter.getClass().getCanonicalName(), movieDetailPresenter);

    }

    @Override
    protected void reinitializeStore() {
        movieDetailPresenter = getStateMaintainer().get(movieDetailPresenter.getClass().getCanonicalName());
        if (movieDetailPresenter != null) {
            movieDetailPresenter.setView(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.movie_detail_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                promptShareCampaignBottomSheet();
                break;
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

    public AppCompatDialog promptShareCampaignBottomSheet() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet);
        RecyclerView share_recycler =(RecyclerView) bottomSheetDialog.findViewById(R.id.share_recycler);


        share_recycler.setLayoutManager(new LinearLayoutManager(this));

                        ShareBottomSheetRecyclerAdapter fundBottomSheetRecyclerAdapter = new ShareBottomSheetRecyclerAdapter(this);
                        fundBottomSheetRecyclerAdapter.setItemClickListener(new ShareBottomSheetRecyclerAdapter.ItemClickListener() {
                            @Override
                            public void OnClicked() {
                                bottomSheetDialog.dismiss();

                                }
                            });
        share_recycler.setAdapter(fundBottomSheetRecyclerAdapter);
                        bottomSheetDialog.show();


        return bottomSheetDialog;
    }

}
