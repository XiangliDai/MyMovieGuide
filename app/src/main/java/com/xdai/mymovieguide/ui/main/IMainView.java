package com.xdai.mymovieguide.ui.main;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.mvp.IView;

import java.util.ArrayList;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMainView extends IView {
    void bindMovieList(ArrayList<MovieResult> movieResults);
}
