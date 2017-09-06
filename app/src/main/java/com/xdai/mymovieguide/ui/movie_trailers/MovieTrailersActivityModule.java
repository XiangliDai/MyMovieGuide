package com.xdai.mymovieguide.ui.movie_trailers;

import android.app.Activity;

import com.mymovieguide.xdai.network.config.ApiRetrofitProvider;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.PicassoImageLoader;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;
import com.xdai.mymovieguide.repository.MovieDetailRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieTrailersActivityModule {
    private final Activity activity;

    public MovieTrailersActivityModule(Activity activity) {
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
    IMovieDetailRepository providesMovieDetailRepository(IApiRetrofitProvider apiRetrofitProvider) {
        return new MovieDetailRepository(activity, apiRetrofitProvider);
    }

    @Provides
    IVideosPresenter providesVideosPresenter(IMovieDetailRepository movieDetailRepository) {
        return new VideosPresenter(movieDetailRepository );
    }
}
