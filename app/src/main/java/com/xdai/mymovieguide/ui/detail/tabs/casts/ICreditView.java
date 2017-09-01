package com.xdai.mymovieguide.ui.detail.tabs.casts;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface ICreditView extends IView {
    void bindCredit(Credits credits);
}
