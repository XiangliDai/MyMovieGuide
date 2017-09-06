package com.xdai.mymovieguide.ui.detail;

import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by xiangli on 7/12/17.
 */

public class MovieDetailPresenter  extends BasePresenter<IMovieDetailView> implements IMovieDetailPresenter {
    private static String TAG = MovieDetailPresenter.class.getCanonicalName();
    private IMovieDetailRepository movieDetailRepository;
    private IMovieDetailView mainView;
    private int page = 0;

    public MovieDetailPresenter(IMovieDetailRepository movieDetailRepository) {
        super();
        this.movieDetailRepository = movieDetailRepository;
    }

    @Override
    public void setView(IMovieDetailView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getMovie(int movie_id) {
        if(movieDetailRepository != null && mainView != null)
            movieDetailRepository.getMovieById(movie_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<MovieDetail>() {
                @Override
                public void accept(MovieDetail movieDetail) throws Exception {
                    mainView.bindMovieDetail(movieDetail);
                }
            },  throwable -> {
                if(mainView != null) {
                    mainView.hideProgress();
                    mainView.showToast(throwable.getMessage());
                }});
    }


}
