package com.xdai.mymovieguide.ui.detail.tabs.casts;

import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface ICastsPresenter {
    void getCredit(int movie_id);
    void setView(ICreditView creditView);
}
