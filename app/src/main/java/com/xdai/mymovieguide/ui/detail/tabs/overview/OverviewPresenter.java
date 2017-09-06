package com.xdai.mymovieguide.ui.detail.tabs.overview;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;
import com.xdai.mymovieguide.ui.detail.IMovieDetailView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangli on 8/31/17.
 */

public class OverviewPresenter  extends BasePresenter<IOverviewView> implements IOverviewPresenter {
    IMovieDetailRepository movieDetailRepository;
    private IOverviewView overview;
    public OverviewPresenter(IMovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }

    @Override
    public void setView(IOverviewView overview) {
        this.overview = overview;
    }


    @Override
    public void getOverview(int movie_id) {
        if(movieDetailRepository != null && overview != null)
            movieDetailRepository.getMovieById(movie_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(movieDetail -> overview.bindOverview(movieDetail), throwable -> {
                if(overview != null) {
                    overview.hideProgress();
                    overview.showToast(throwable.getMessage());
                }});
    }
}
