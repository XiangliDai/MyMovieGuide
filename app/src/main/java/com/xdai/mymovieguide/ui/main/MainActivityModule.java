package com.xdai.mymovieguide.ui.main;

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
public class MainActivityModule {
    private final Activity activity;

    public MainActivityModule(Activity activity) {
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
    IMainPresenter providesMainPresenter(IMovieRepository movieRepository) {
        return new MainPresenter(movieRepository);
    }
}
