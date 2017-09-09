package com.xdai.mymovieguide.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.mymovieguide.xdai.network.response.Genre;
import com.xdai.mymovieguide.ui.movie_player.MoviePlayerActivity;
import com.xdai.mymovieguide.ui.movie_list.MovieListActivity;
import com.xdai.mymovieguide.ui.detail.MovieDetailActivity;
import com.xdai.mymovieguide.ui.search.SearchActivity;

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
    public static void launchMovieDetailWithTransition(Activity activity, int movieId, String movieName, View imageView){
        Intent intent = new Intent(activity, MovieDetailActivity.class);
        intent.putExtra("movie_name", movieName);
        intent.putExtra("movie_id", movieId);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, imageView, "movie_backdrop");

        activity.startActivity(intent, options.toBundle());
    }
    public static void launchMovieList(Context context, Genre genre, MovieListActivity.Type genre_type) {
        Intent intent = new Intent(context, MovieListActivity.class);
        intent.putExtra("list_type", genre_type);
        intent.putExtra("genre_id", genre.getId());
        intent.putExtra("genre_name", genre.getName());

        context.startActivity(intent);
    }


    public static void launchVideoPlayer(Context context, String id) {
        Intent intent = new Intent(context, MoviePlayerActivity.class);
        intent.putExtra("video_key", id);
        context.startActivity(intent);
    }

    public static void launchSearchActivity(Context context, String query) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra("query", query);
        context.startActivity(intent);
    }
}
