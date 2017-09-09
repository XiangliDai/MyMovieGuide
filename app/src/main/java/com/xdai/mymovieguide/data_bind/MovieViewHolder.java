package com.xdai.mymovieguide.data_bind;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdai.mymovieguide.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 9/8/17.
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.image_movie_backdrop)
    public ImageView image;
    @Bind(R.id.movie_name)
    public TextView movie_name;
    @Bind(R.id.movie_desc)
    public TextView movie_desc;
    @Bind(R.id.movie_release)
    public TextView movie_release;
    @Bind(R.id.movie_rate)
    public TextView movie_rate;
    @Bind(R.id.more_info)
    public TextView more_info;
    public MovieViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}