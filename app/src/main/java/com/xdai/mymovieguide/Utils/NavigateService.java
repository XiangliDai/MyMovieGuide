package com.xdai.mymovieguide.Utils;

import android.content.Context;
import android.content.Intent;

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
}
