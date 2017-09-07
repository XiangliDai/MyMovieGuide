package com.xdai.mymovieguide.ui.detail.tabs.trailers;

import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangli on 8/31/17.
 */

public class TrailersPresenter extends BasePresenter<ITrailerView> implements ITrailersPresenter {
    ITrailerView videoView;
    IMovieDetailRepository movieDetailRepository;
    int page;

    public TrailersPresenter(IMovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
        page = 1;
    }

    @Override
    public void setView(ITrailerView videoView) {
        this.videoView = videoView;
    }

    @Override
    public void getTrailers(int movie_id) {
        if(movieDetailRepository != null && videoView != null)
            movieDetailRepository.getMovieVideosById(movie_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(videos -> {
                videoView.bindTrailers(videos);
            }, throwable -> {
                if(videoView != null) {
                    videoView.hideProgress();
                    videoView.showToast(throwable.getMessage());
                }});

    }

}
