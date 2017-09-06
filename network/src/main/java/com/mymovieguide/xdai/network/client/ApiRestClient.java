package com.mymovieguide.xdai.network.client;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.Genres;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Reviews;
import com.mymovieguide.xdai.network.response.Videos;

import io.reactivex.Observable;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xiangli on 7/7/17.
 */

public interface ApiRestClient {
    @GET("discover/movie?language=en&sort_by=popularity.desc")
    Observable<ResponseBody> getPopularVideo(@Query("api_key") String apiKey);

    @GET("discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Observable<ResponseBody> getHighestRatedVideo(@Query("api_key") String apiKey);

    @GET("movie/{movie}/videos")
    Observable<ResponseBody> getTrailers(@Path("movie") String movie, @Query("api_key") String apiKey);

    @GET("movie/{movie}/reviews")
    Observable<ResponseBody> getReviews(@Path("movie") String movie, @Query("api_key") String apiKey);

    @GET("genre/movie/list")
    Observable<Genres> getGenres(@Query("api_key") String apiKey);

    @GET("genre/{genre_id}/movies")
    Observable<Movies> getMoviesByGenre(@Path("genre_id") int genre_id, @Query("include_adult") boolean include_adult, @Query("language") String language, @Query("api_key") String apiKey);

    @GET("movie/popular")
    Observable<Movies> getPopular(@Query("page") int page, @Query("language") String language, @Query("region") String region, @Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Observable<Movies> getTopRated(@Query("page") int page, @Query("language") String language, @Query("region") String region, @Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Observable<Movies> getUpcoming(@Query("page") int page, @Query("language") String language, @Query("region") String region, @Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Observable<Movies> getNowPlaying(@Query("page") int page, @Query("language") String language, @Query("region") String region, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}")
    Observable<MovieDetail> getMovieById(@Path("movie_id") int movie_id, @Query("language") String language, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/credits")
    Observable<Credits> getMovieCreditsById(@Path("movie_id") int movie_id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Observable<Videos> getMovieVideosById(@Path("movie_id") int movie_id, @Query("language") String language, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/recommendations")
    Observable<Movies> getMovieRecommendationsById(@Path("movie_id") int movie_id, @Query("page") int page,  @Query("language") String language, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/similar")
    Observable<Movies> getMovieSimilarsById(@Path("movie_id") int movie_id, @Query("page") int page,  @Query("language") String language, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Observable<Reviews> getMovieReviewsById(@Path("movie_id") int movie_id, @Query("page") int page, @Query("language") String language, @Query("api_key") String apiKey);

    /*
    public static final String POSTER_PATH = "http://image.tmdb.org/t/p/w342";
    public static final String BACKDROP_PATH = "http://image.tmdb.org/t/p/w780";
    static final String YOUTUBE_VIDEO_URL = "http://www.youtube.com/watch?v=%1$s";
    static final String YOUTUBE_THUMBNAIL_URL = "http://img.youtube.com/vi/%1$s/0.jpg";
*/
}
