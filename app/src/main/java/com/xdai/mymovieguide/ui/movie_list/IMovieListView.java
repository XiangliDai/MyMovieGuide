package com.xdai.mymovieguide.ui.movie_list;

import com.mymovieguide.xdai.network.response.MovieResult;
import com.xdai.mymovieguide.mvp.IView;

import java.util.ArrayList;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IMovieListView extends IView {
    void bindMovieList(ArrayList<MovieResult> movieResults);
}
