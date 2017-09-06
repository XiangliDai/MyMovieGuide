package com.xdai.mymovieguide.repository;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.Genres;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Videos;

import io.reactivex.Observable;

/**
 * Created by xiangli on 8/24/17.
 */

public interface IMovieRepository {

    Observable<Genres> getGenreList();

    Observable<Movies> getMostPopularMovies(int page);

    Observable<Movies> getTopRatedMovies(int page);

    Observable<Movies> getNowPlayingMovies(int page);

    Observable<Movies> getUpcomingMovies(int page);

    Observable<MovieDetail> getMovieById(int movie_id);

    Observable<Credits> getMovieCreditsById(int movie_id);

    Observable<Videos> getMovieVideosById(int movie_id);

    Observable<Movies> getMovieRecommendationsById(int movie_id, int page);

    Observable<Movies> getMovieSimilarsById(int movie_id, int page);

    Observable<Movies> getMoviesByGenre(int genre);
}
