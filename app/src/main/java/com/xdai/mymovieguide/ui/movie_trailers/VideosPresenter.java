package com.xdai.mymovieguide.ui.movie_trailers;

import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangli on 8/31/17.
 */

public class VideosPresenter extends BasePresenter<IVideoView> implements IVideosPresenter {
    IVideoView videoView;
    IMovieDetailRepository movieDetailRepository;
    int page;

    public VideosPresenter(IMovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
        page = 1;
    }

    @Override
    public void setView(IVideoView videoView) {
        this.videoView = videoView;
    }

    @Override
    public void getVideos(int movie_id) {
        if(movieDetailRepository != null && videoView != null)
            movieDetailRepository.getMovieVideosById(movie_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(videos -> {
                videoView.bindVideos(videos);
            }, throwable -> {
                if(videoView != null) {
                    videoView.hideProgress();
                    videoView.showToast(throwable.getMessage());
                }});

    }
}
