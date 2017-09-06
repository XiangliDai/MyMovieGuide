package com.xdai.mymovieguide.ui.detail.tabs.reviews;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Reviews;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 8/31/17.
 */

public interface IReviewView extends IView {
    void bindReviews(Reviews reviews);
}
