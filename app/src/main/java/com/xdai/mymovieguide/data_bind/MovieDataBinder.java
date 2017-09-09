package com.xdai.mymovieguide.data_bind;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.mymovieguide.xdai.network.response.IMovie;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.NavigateService;

/**
 * Created by xiangli on 9/8/17.
 */

public class MovieDataBinder<M extends IMovie> {
    M movieData;
    MovieViewHolder movieViewHolder;
    Context context;
    private IImageLoader imageLoader;
    public MovieDataBinder(Context context, M movieData, MovieViewHolder movieViewHolder, IImageLoader imageLoader){
        this.context = context;
        this.movieData = movieData;
        this.movieViewHolder = movieViewHolder;
        this.imageLoader = imageLoader;
    }

    public void bind(){
        movieViewHolder.movie_desc.setText(movieData.getOverview());
        movieViewHolder.movie_name.setText(movieData.getTitle());
        movieViewHolder.movie_release.setText(movieData.getRelease_date());
        movieViewHolder.movie_rate.setText(String.valueOf(movieData.getVote_average()));
        imageLoader.loadImageIntoImageView(context.getString(R.string.image_url) + movieData.getBackdrop_path(), movieViewHolder.image);
        View.OnClickListener onClickListener = view -> NavigateService.launchMovieDetailWithTransition((Activity)context, movieData.getId(), movieData.getTitle(), movieViewHolder.image);
        movieViewHolder.more_info.setOnClickListener(onClickListener);
        movieViewHolder.itemView.setOnClickListener(onClickListener);
    }
}
