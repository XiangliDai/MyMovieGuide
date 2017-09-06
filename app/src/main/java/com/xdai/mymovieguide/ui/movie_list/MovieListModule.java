package com.xdai.mymovieguide.ui.movie_list;

import android.app.Activity;

import com.mymovieguide.xdai.network.config.ApiRetrofitProvider;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.PicassoImageLoader;
import com.xdai.mymovieguide.repository.IMovieRepository;
import com.xdai.mymovieguide.repository.MovieRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieListModule {
    private final Activity activity;

    public MovieListModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    Activity activity() {
        return activity;
    }


    @Provides
    IApiRetrofitProvider providesApiRetrofitProvider() {
        return new ApiRetrofitProvider();
    }

    @Provides
    IImageLoader providesImageLoader() {
        return new PicassoImageLoader(activity);
    }

    @Provides
    IMovieRepository providesMovieRepository(IApiRetrofitProvider apiRetrofitProvider) {
        return new MovieRepository(activity, apiRetrofitProvider);
    }

    @Provides
    IMovieListPresenter providesMovieListPresenter(IMovieRepository movieRepository) {
        return new MovieListPresenter(movieRepository);
    }
}
