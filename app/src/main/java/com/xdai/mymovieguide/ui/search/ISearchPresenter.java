package com.xdai.mymovieguide.ui.search;

import com.xdai.mymovieguide.mvp.IPresenter;
import com.xdai.mymovieguide.ui.detail.IMovieDetailView;
import com.xdai.mymovieguide.ui.movie_list.IMovieListView;
import com.xdai.mymovieguide.ui.movie_list.MovieListBaseActivity;

/**
 * Created by xiangli on 7/12/17.
 */

public interface ISearchPresenter extends IPresenter<ISearchView> {

    void loadSearchResultByQuery(String query, boolean b);
    void setView(ISearchView searchView);

}
