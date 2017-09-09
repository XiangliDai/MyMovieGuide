package com.xdai.mymovieguide.ui.search;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.mymovieguide.xdai.network.response.SearchResult;
import com.xdai.mymovieguide.mvp.IView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangli on 7/12/17.
 */

public interface ISearchView extends IView {
    void bindSearchResults(List<SearchResult> searchResults);

}
