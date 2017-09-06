package com.xdai.mymovieguide.ui.movie_list;

import com.xdai.mymovieguide.mvp.IPresenter;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMovieListPresenter extends IPresenter<IMovieListView> {

    void loadMovieList(MovieListBaseActivity.Type type, int id);
    void loadMovieList(MovieListBaseActivity.Type type);
}
