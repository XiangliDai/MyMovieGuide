package com.xdai.mymovieguide.ui.main;

import com.xdai.mymovieguide.mvp.IPresenter;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMainPresenter extends IPresenter<IMainView> {

    void loadPopularMovies();

    void loadTopRatedMovies();

    void loadNowPlayingMovies();

    void loadUpcomingMovies();

    void refresh(int currentType);
}
