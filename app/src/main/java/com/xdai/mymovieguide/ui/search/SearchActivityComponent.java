package com.xdai.mymovieguide.ui.search;


import com.xdai.mymovieguide.ui.movie_list.MovieListActivity;
import com.xdai.mymovieguide.ui.movie_list.MovieListModule;

import dagger.Component;

@Component(
        modules = {
            SearchActivityModule.class
        })
public interface SearchActivityComponent {
    void inject(SearchActivity searchActivity);

}
