package com.xdai.mymovieguide.ui.movie_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.NavigateService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/24/17.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private List<MovieResult> movieListMovieResults;
    private Context context;
    private  IImageLoader imageLoader;
    public MovieListAdapter(Context context, ArrayList<MovieResult> movieList, IImageLoader imageLoader){
        this.context = context;
        this.movieListMovieResults = movieList;
        this.imageLoader = imageLoader;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_movie_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieResult movieResult = movieListMovieResults.get(position);
        holder.movie_desc.setText(movieResult.getOverview());
        holder.movie_name.setText(movieResult.getTitle());
        holder.movie_release.setText(movieResult.getRelease_date());
        holder.movie_rate.setText(String.valueOf(movieResult.getVote_average()));
        imageLoader.loadImageIntoImageView(context.getString(R.string.image_url) + movieResult.getBackdrop_path(), holder.image);
        View.OnClickListener onClickListener = view -> NavigateService.launchMovieDetail(context, movieResult.getId(), movieResult.getTitle());
        holder.more_info.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        return movieListMovieResults == null ? 0 : movieListMovieResults.size();
    }


    public void reset(){
        if(this.movieListMovieResults == null){
            movieListMovieResults = new ArrayList<>();
        }
        movieListMovieResults.clear();
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.movie_name)
        TextView movie_name;
        @Bind(R.id.movie_desc)
        TextView movie_desc;
        @Bind(R.id.movie_release)
        TextView movie_release;
        @Bind(R.id.movie_rate)
        TextView movie_rate;
        @Bind(R.id.more_info)
        TextView more_info;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
