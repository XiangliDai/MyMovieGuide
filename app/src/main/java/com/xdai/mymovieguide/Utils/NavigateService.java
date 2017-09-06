package com.xdai.mymovieguide.Utils;

import android.content.Context;
import android.content.Intent;

import com.mymovieguide.xdai.network.response.Genre;
import com.xdai.mymovieguide.ui.movie_trailers.MoviePlayerActivity;
import com.xdai.mymovieguide.ui.movie_trailers.MovieTrailersActivity;
import com.xdai.mymovieguide.ui.movie_list.MovieListActivity;
import com.xdai.mymovieguide.ui.detail.MovieDetailActivity;

/**
 * Created by xiangli on 8/30/17.
 */

public class NavigateService {
    public static void launchMovieDetail(Context context, int movieId, String movieName){
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra("movie_name", movieName);
        intent.putExtra("movie_id", movieId);
        context.startActivity(intent);
    }

    public static void launchMovieList(Context context, Genre genre, MovieListActivity.Type genre_type) {
        Intent intent = new Intent(context, MovieListActivity.class);
        intent.putExtra("list_type", genre_type);
        intent.putExtra("genre_id", genre.getId());
        intent.putExtra("genre_name", genre.getName());

        context.startActivity(intent);
    }

    public static void launchMovieTrailer(Context context, int movieId, String movieName) {
        Intent intent = new Intent(context, MovieTrailersActivity.class);
        intent.putExtra("movie_name", movieName);
        intent.putExtra("movie_id", movieId);
        context.startActivity(intent);
    }

    public static void launchVideoPlayer(Context context, String id) {
        Intent intent = new Intent(context, MoviePlayerActivity.class);
        intent.putExtra("video_key", id);
        context.startActivity(intent);
    }
}
