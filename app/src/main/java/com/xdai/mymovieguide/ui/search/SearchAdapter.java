package com.xdai.mymovieguide.ui.search;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovieguide.xdai.network.response.SearchResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.NavigateService;
import com.xdai.mymovieguide.data_bind.MovieDataBinder;
import com.xdai.mymovieguide.data_bind.PersonDataBinder;
import com.xdai.mymovieguide.data_bind.PersonViewHolder;
import com.xdai.mymovieguide.data_bind.MovieViewHolder;
import com.xdai.mymovieguide.ui.movie_list.MovieListAdapter;

import java.util.List;

/**
 * Created by xiangli on 8/24/17.
 */
public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SearchResult> searchResults;
    private Context context;
    private  IImageLoader imageLoader;
    private static int MOVIE = 0;
    private static int PERSON = 1;
    private static int TV = 2;

    public SearchAdapter(Context context, List<SearchResult> searchResults, IImageLoader imageLoader){
        this.context = context;
        this.searchResults = searchResults;
        this.imageLoader = imageLoader;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == MOVIE || viewType == TV) {
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.layout_movie_list_item, parent, false);
            return new MovieViewHolder(itemView);
        } else if(viewType == PERSON){
            View itemView = LayoutInflater.
                    from(parent.getContext()).
                    inflate(R.layout.layout_person_with_know_for_list, parent, false);
            return new PersonViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SearchResult searchResult = searchResults.get(position);
        int viewType = getItemViewType(position);
        if(viewType == MOVIE) {
            MovieDataBinder<SearchResult> movieDataBinder = new MovieDataBinder<>(context, searchResult, (MovieViewHolder)holder, imageLoader);
            movieDataBinder.bind();
        }  if(viewType == TV) {
            MovieViewHolder movieViewHolder = (MovieViewHolder)holder;
            movieViewHolder.movie_desc.setText(searchResult.getOverview());
            movieViewHolder.movie_name.setText(searchResult.getName());
            if(searchResult.getFirst_air_date() != null) {
                movieViewHolder.movie_release.setText(searchResult.getFirst_air_date());
            }
            movieViewHolder.movie_rate.setText(String.valueOf(searchResult.getVote_average()));
            imageLoader.loadImageIntoImageView(context.getString(R.string.image_url) + searchResult.getBackdrop_path(), movieViewHolder.image);
            View.OnClickListener onClickListener = view -> NavigateService.launchMovieDetail(context, searchResult.getId(), searchResult.getTitle());
            movieViewHolder.more_info.setOnClickListener(onClickListener);
            movieViewHolder.itemView.setOnClickListener(onClickListener);
        } else if(viewType == PERSON){
            PersonViewHolder personViewHolder = (PersonViewHolder) holder ;
            PersonDataBinder<SearchResult> personDataBinder = new PersonDataBinder<>(context, searchResult, personViewHolder, imageLoader);
            personDataBinder.bind();
            if(searchResult.getKnown_for() != null){
                personViewHolder.know_for_list.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                MovieListAdapter movieListAdapter = new MovieListAdapter(context, searchResult.getKnown_for(), imageLoader, R.layout.layout_small_movie_list_item );
                personViewHolder.know_for_list.setAdapter(movieListAdapter);
            }
        }
    }

    @Override
    public int getItemCount() {
        return searchResults == null ? 0 : searchResults.size();
    }


    @Override
    public int getItemViewType(int position) {
        SearchResult searchResult = searchResults.get(position);
        switch (searchResult.getMedia_type()){
            case "tv":
                return TV;
            case "movie":
                return MOVIE;
            case "person":
                return PERSON;
            default:
                return -1;
        }
    }

}
