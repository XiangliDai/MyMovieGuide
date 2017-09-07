package com.xdai.mymovieguide.ui.detail;

import android.app.Activity;

import com.mymovieguide.xdai.network.config.ApiRetrofitProvider;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.PicassoImageLoader;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;
import com.xdai.mymovieguide.repository.MovieDetailRepository;
import com.xdai.mymovieguide.ui.detail.tabs.casts.CastsPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.casts.ICastsPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.overview.OverviewPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.reviews.IReviewsPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.reviews.ReviewsPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.trailers.ITrailersPresenter;
import com.xdai.mymovieguide.ui.detail.tabs.trailers.TrailersPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailActivityModule {
    private final Activity activity;

    public MovieDetailActivityModule(Activity activity) {
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
    IMovieDetailPresenter providesMovieDetailPresenter(IMovieDetailRepository movieDetailRepository) {
        return new MovieDetailPresenter(movieDetailRepository);
    }

    @Provides
    IOverviewPresenter providesOverviewPresenter(IMovieDetailRepository movieDetailRepository) {
        return new OverviewPresenter(movieDetailRepository);
    }

    @Provides
    ICastsPresenter providesCastPresenter(IMovieDetailRepository movieDetailRepository) {
        return new CastsPresenter(movieDetailRepository );
    }

    @Provides
    IReviewsPresenter providesReviewsPresenter(IMovieDetailRepository movieDetailRepository) {
        return new ReviewsPresenter(movieDetailRepository );
    }
    @Provides
    ITrailersPresenter providesTrailersPresenter(IMovieDetailRepository movieDetailRepository) {
        return new TrailersPresenter(movieDetailRepository );
    }


}
