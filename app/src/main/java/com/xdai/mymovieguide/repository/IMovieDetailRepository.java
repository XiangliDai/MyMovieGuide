package com.xdai.mymovieguide.repository;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Reviews;
import com.mymovieguide.xdai.network.response.Videos;

import io.reactivex.Observable;

/**
 * Created by xiangli on 8/30/17.
 */

public interface IMovieDetailRepository {


    Observable<MovieDetail> getMovieById(int movie_id);

    Observable<Credits> getMovieCreditsById(int movie_id);

    Observable<Videos> getMovieVideosById(int movie_id);

    Observable<Movies> getMovieRecommendationsById(int movie_id, int page);

    Observable<Movies> getMovieSimilarsById(int movie_id, int page);

    Observable<Reviews> getMovieReviewsById(int movie_id, int page);

}
