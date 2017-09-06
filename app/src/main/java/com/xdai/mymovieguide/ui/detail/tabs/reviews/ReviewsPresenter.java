package com.xdai.mymovieguide.ui.detail.tabs.reviews;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Reviews;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;
import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangli on 8/31/17.
 */

public class ReviewsPresenter  extends BasePresenter<IReviewView> implements IReviewsPresenter {
    IReviewView reviewView;
    IMovieDetailRepository movieDetailRepository;
    int page;
    public ReviewsPresenter(IMovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
        page = 1;
    }

    @Override
    public void getReviews(int movie_id) {
        if(movieDetailRepository != null && reviewView != null)
            movieDetailRepository.getMovieReviewsById(movie_id, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(reviews -> {
                page ++;
                reviewView.bindReviews(reviews);
            }, throwable -> {
                if(reviewView != null) {
                    reviewView.hideProgress();
                    reviewView.showToast(throwable.getMessage());
                }});

    }

    @Override
    public void setView(IReviewView reviewView) {
        this.reviewView = reviewView;
    }


}
