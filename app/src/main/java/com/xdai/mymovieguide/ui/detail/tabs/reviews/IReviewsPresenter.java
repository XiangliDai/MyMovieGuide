package com.xdai.mymovieguide.ui.detail.tabs.reviews;

import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface IReviewsPresenter {

    void getReviews(int movie_id);
    void setView(IReviewView reviewView);
}
