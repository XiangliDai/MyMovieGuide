package com.xdai.mymovieguide.repository;

import android.content.Context;

import com.mymovieguide.xdai.network.client.ApiRestClient;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.Genres;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.R;

import java.util.Locale;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xiangli on 8/24/17.
 */

public class MovieRepository implements IMovieRepository{
    private Context context;
    private IApiRetrofitProvider retrofitProvider;
    private ApiRestClient apiRestClient;
    private String apiKey;
    private String language;
    private String region;
    public MovieRepository(Context context, IApiRetrofitProvider retrofitProvider
                        ) {
        this.context = context;

        apiRestClient = retrofitProvider.getTMDBApiRetrofit().create(ApiRestClient.class);
        apiKey = context.getString(R.string.api_key);
        language = Locale.getDefault().toString();
        region = Locale.getDefault().getCountry();
    }

    @Override
    public Observable<Genres> getGenreList() {
        return apiRestClient.getGenres(apiKey);
    }

    @Override
    public Observable<Movies> getMostPopularMovies(int page) {
        return apiRestClient.getPopular(page, language, region, apiKey);
    }

    @Override
    public Observable<Movies> getTopRatedMovies(int page) {
        return apiRestClient.getTopRated(page, language, region, apiKey);
    }

    @Override
    public Observable<Movies> getNowPlayingMovies(int page) {
        return apiRestClient.getNowPlaying(page, language, region, apiKey);
    }

    @Override
    public Observable<Movies> getUpcomingMovies(int page) {
        return apiRestClient.getUpcoming(page, language, region, apiKey);
    }

    @Override
    public Observable<MovieDetail> getMovieById(int movie_id){
        return apiRestClient.getMovieById(movie_id, language, apiKey);
    }

    @Override
    public Observable<Credits> getMovieCreditsById(int movie_id){
        return apiRestClient.getMovieCreditsById(movie_id, apiKey);
    }

    @Override
    public Observable<Videos> getMovieVideosById(int movie_id){
        return apiRestClient.getMovieVideosById(movie_id, language, apiKey);
    }

    @Override
    public Observable<Movies> getMovieRecommendationsById(int movie_id, int page){
        return apiRestClient.getMovieRecommendationsById(movie_id, page, language, apiKey);
    }

    @Override
    public Observable<Movies> getMovieSimilarsById(int movie_id, int page){
        return apiRestClient.getMovieSimilarsById(movie_id, page, language, apiKey);
    }

    @Override
    public Observable<Movies> getMoviesByGenre(int genre){
        return apiRestClient.getMoviesByGenre(genre, true, language, apiKey);
    }
}
