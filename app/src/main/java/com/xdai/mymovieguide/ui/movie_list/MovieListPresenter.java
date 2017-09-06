package com.xdai.mymovieguide.ui.movie_list;

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

public class MovieListPresenter extends BasePresenter<IMovieListView> implements IMovieListPresenter {
    private static String TAG = MovieListPresenter.class.getCanonicalName();
    private IMovieRepository movieRepository;
    private int page = 1;
    private int currentType;
    public MovieListPresenter(IMovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }

    private void loadTopRatedMovies() {
        movieRepository.getTopRatedMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    private void loadNowPlayingMovies() {
        movieRepository.getNowPlayingMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    private void loadUpcomingMovies() {
        movieRepository.getUpcomingMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    @Override
    public void loadMovieList(MovieListBaseActivity.Type type, int id) {
        switch (type){
            case POPULAR:
                loadPopularMovies();
                break;
            case TOPRATED:
                loadTopRatedMovies();
                break;
            case NOWPLAYING:
                loadNowPlayingMovies();
                break;
            case UPCOMING:
                loadUpcomingMovies();
                break;
            case GENRE:
                loadGenreMovies(id);
                break;
        }
    }

    @Override
    public void loadMovieList(MovieListBaseActivity.Type type) {
        loadMovieList(type, 0);
    }


    @Override
    public void setView(IMovieListView movieListView) {
        super.setView(movieListView);
    }


    private void loadGenreMovies(int genre_id) {
        if (movieRepository != null && getView() != null)
            movieRepository.getMoviesByGenre(genre_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    private void loadPopularMovies() {
        if (movieRepository != null && getView() != null)
            movieRepository.getMostPopularMovies(page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
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
