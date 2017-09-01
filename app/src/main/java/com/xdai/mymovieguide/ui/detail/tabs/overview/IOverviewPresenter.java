package com.xdai.mymovieguide.ui.detail.tabs.overview;

import com.xdai.mymovieguide.ui.detail.IMovieDetailView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface IOverviewPresenter {
    void getOverview(int movie_id);
    void setView(IOverviewView overviewView);

}
