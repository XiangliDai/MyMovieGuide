package com.xdai.mymovieguide.ui.search;

import android.app.Activity;

import com.mymovieguide.xdai.network.config.ApiRetrofitProvider;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.PicassoImageLoader;
import com.xdai.mymovieguide.repository.IMovieRepository;
import com.xdai.mymovieguide.repository.ISearchRepository;
import com.xdai.mymovieguide.repository.MovieRepository;
import com.xdai.mymovieguide.repository.SearchRepository;
import com.xdai.mymovieguide.ui.movie_list.IMovieListPresenter;
import com.xdai.mymovieguide.ui.movie_list.MovieListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchActivityModule {
    private final Activity activity;

    public SearchActivityModule(Activity activity) {
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
    ISearchRepository providesSearchRepository(IApiRetrofitProvider apiRetrofitProvider) {
        return new SearchRepository(activity, apiRetrofitProvider);
    }

    @Provides
    ISearchPresenter providesSearchPresenter(ISearchRepository searchRepository) {
        return new SearchPresenter(searchRepository);
    }
}
