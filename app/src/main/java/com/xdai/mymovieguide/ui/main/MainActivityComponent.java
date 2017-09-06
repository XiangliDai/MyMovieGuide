package com.xdai.mymovieguide.ui.main;


import com.xdai.mymovieguide.ui.movie_list.MovieListModule;

import dagger.Component;

@Component(
        modules = {
                MovieListModule.class
        })
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
