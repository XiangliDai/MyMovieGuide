package com.xdai.mymovieguide.repository;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.Genres;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Search;
import com.mymovieguide.xdai.network.response.SearchResult;
import com.mymovieguide.xdai.network.response.Videos;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by xiangli on 8/24/17.
 */

public interface ISearchRepository{
    Observable<Search> getSearchResultsByQuery(String query, int page);

}
