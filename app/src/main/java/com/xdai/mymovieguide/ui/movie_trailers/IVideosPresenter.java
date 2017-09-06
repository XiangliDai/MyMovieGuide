package com.xdai.mymovieguide.ui.movie_trailers;

/**
 * Created by xiangli on 8/31/17.
 */

public interface IVideosPresenter{
        void getVideos(int movie_id);
        void setView(IVideoView videoView);
}
