package com.xdai.mymovieguide.ui.detail.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import com.xdai.mymovieguide.ui.detail.tabs.casts.CastsFragment;
import com.xdai.mymovieguide.ui.detail.tabs.overview.OverviewFragment;
import com.xdai.mymovieguide.ui.detail.tabs.reviews.ReviewsFragment;
import com.xdai.mymovieguide.ui.detail.tabs.videos.VideosFragment;

/**
 * Created by xiangli on 8/31/17.
 */

public class DetailPagerAdapter extends FragmentStatePagerAdapter {
    private OverviewFragment overviewFragment;
    private CastsFragment castsFragment;
    private ReviewsFragment reviewsFragment;
    private VideosFragment videosFragment;
    public DetailPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               overviewFragment = new OverviewFragment();
               return overviewFragment;
           case 1:
               castsFragment = new CastsFragment();
               return castsFragment;
           case 2:
               reviewsFragment = new ReviewsFragment();
               return reviewsFragment;
           case 3:
               videosFragment = new VideosFragment();
               return videosFragment;
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return 4;
    }

}