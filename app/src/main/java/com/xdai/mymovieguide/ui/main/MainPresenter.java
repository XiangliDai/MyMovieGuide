package com.xdai.mymovieguide.ui.main;

import android.util.Log;

import com.mymovieguide.xdai.network.response.Movies;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by xiangli on 7/12/17.
 */

public class MainPresenter extends BasePresenter<IMainView> implements IMainPresenter {
    private static String TAG = MainPresenter.class.getCanonicalName();
    private IMovieRepository movieRepository;
    private int page = 1;
    private int currentType;
    public MainPresenter(IMovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }

    @Override
    public void loadTopRatedMovies() {
        movieRepository.getTopRatedMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    @Override
    public void loadNowPlayingMovies() {
        movieRepository.getNowPlayingMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    @Override
    public void loadUpcomingMovies() {
        movieRepository.getUpcomingMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    @Override
    public void refresh(int currentType) {
        this.currentType = currentType;
        switch (currentType){
            case 0:
                loadPopularMovies();
                break;
            case 1:
                loadTopRatedMovies();
                break;
            case 2:
                loadNowPlayingMovies();
                break;
            case 3:
                loadUpcomingMovies();
                break;
        }
    }

    @Override
    public void loadPopularMovies() {
        if (movieRepository != null && getView() != null)
            movieRepository.getMostPopularMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    @Override
    public void setView(IMainView mainView) {
        super.setView(mainView);
    }

    private Consumer<Movies> onReceiveData = movieListResult -> {
        this.page = movieListResult.getPage();
        Log.d(TAG, "page= " + page);
        getView().bindMovieList(movieListResult.getMovieResults());
    };

    private Consumer<Throwable> throwableConsumer = throwable -> {
        if (getView() != null) {
            getView().hideProgress();
            getView().showToast(throwable.getMessage());
        }
    };
}
