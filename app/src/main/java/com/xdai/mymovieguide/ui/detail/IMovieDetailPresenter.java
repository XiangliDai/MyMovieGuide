package com.xdai.mymovieguide.ui.detail;

import com.xdai.mymovieguide.mvp.IPresenter;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMovieDetailPresenter extends IPresenter<IMovieDetailView> {

    void setView(IMovieDetailView mainView);

    void getMovie(int movie_id);
}
