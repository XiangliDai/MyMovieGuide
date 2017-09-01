package com.xdai.mymovieguide.ui.detail;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMovieDetailView extends IView {
    void bindMovieDetail(MovieDetail movieDetail);
}
