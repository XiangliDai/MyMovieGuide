package com.xdai.mymovieguide.ui.movie_trailers;

import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 9/1/17.
 */

public interface IVideoView extends IView {
    void bindVideos(Videos videos);

}
