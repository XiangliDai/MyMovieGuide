package com.xdai.mymovieguide.ui.movie_list;


import dagger.Component;

@Component(
        modules = {
            MovieListModule.class
        })
public interface MovieListActivityComponent {
    void inject(MovieListActivity movieListActivity);

}
