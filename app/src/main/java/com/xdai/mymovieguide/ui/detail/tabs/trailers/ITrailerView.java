package com.xdai.mymovieguide.ui.detail.tabs.trailers;

import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 9/1/17.
 */

public interface ITrailerView extends IView {
    void bindTrailers(Videos videos);

}
