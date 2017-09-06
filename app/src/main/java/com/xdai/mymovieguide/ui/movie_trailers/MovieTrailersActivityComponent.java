package com.xdai.mymovieguide.ui.movie_trailers;


import dagger.Component;

@Component(
        modules = {
            MovieTrailersActivityModule.class
        })
public interface MovieTrailersActivityComponent {
    void inject(MovieTrailersActivity mainActivity);

}
