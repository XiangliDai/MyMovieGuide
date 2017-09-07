package com.xdai.mymovieguide.ui.detail.tabs.trailers;

/**
 * Created by xiangli on 8/31/17.
 */

public interface ITrailersPresenter {
        void getTrailers(int movie_id);
        void setView(ITrailerView videoView);
}
