package com.xdai.mymovieguide.ui.detail.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xdai.mymovieguide.ui.detail.tabs.casts.CastsFragment;
import com.xdai.mymovieguide.ui.detail.tabs.overview.OverviewFragment;
import com.xdai.mymovieguide.ui.detail.tabs.reviews.ReviewsFragment;
import com.xdai.mymovieguide.ui.detail.tabs.trailers.TrailersFragment;

/**
 * Created by xiangli on 8/31/17.
 */

public class DetailPagerAdapter extends FragmentStatePagerAdapter {
    private OverviewFragment overviewFragment;
    private CastsFragment castsFragment;
    private ReviewsFragment reviewsFragment;
    private TrailersFragment trailersFragment;

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
               trailersFragment = new TrailersFragment();
               return trailersFragment;
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return 4;
    }

}
