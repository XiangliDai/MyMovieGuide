package com.xdai.mymovieguide.ui.detail.tabs.overview;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface IOverviewView extends IView {
    void bindOverview(MovieDetail movieDetail);
}
