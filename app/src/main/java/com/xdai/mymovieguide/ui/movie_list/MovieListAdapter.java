package com.xdai.mymovieguide.ui.movie_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovieguide.xdai.network.response.IMovie;
import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.data_bind.MovieDataBinder;
import com.xdai.mymovieguide.data_bind.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangli on 8/24/17.
 */
public class MovieListAdapter<M extends IMovie> extends RecyclerView.Adapter<MovieViewHolder> {
    private List<M> movieListMovieResults;
    private Context context;
    private IImageLoader imageLoader;
    private int layoutRestId;
    public MovieListAdapter(Context context, ArrayList<M> movieList, IImageLoader imageLoader, int layoutRestId) {
        this.context = context;
        this.movieListMovieResults = movieList;
        this.imageLoader = imageLoader;
        this.layoutRestId = layoutRestId;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(layoutRestId, parent, false);
        return new MovieViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        M movieResult = movieListMovieResults.get(position);
        MovieDataBinder<M> movieDataBinder = new MovieDataBinder<>(context, movieResult, holder, imageLoader);
        movieDataBinder.bind();
    }

    @Override
    public int getItemCount() {
        return movieListMovieResults == null ? 0 : movieListMovieResults.size();
    }


    public void reset() {
        if (this.movieListMovieResults == null) {
            movieListMovieResults = new ArrayList<>();
        }
        movieListMovieResults.clear();
        notifyDataSetChanged();
    }

}
