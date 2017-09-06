package com.xdai.mymovieguide.ui.detail;


import com.xdai.mymovieguide.ui.detail.tabs.casts.CastsFragment;
import com.xdai.mymovieguide.ui.detail.tabs.overview.OverviewFragment;
import com.xdai.mymovieguide.ui.detail.tabs.reviews.ReviewsFragment;

import dagger.Component;

@Component(
        modules = {
            MovieDetailActivityModule.class
        })
public interface MovieDetailActivityComponent {
    void inject(MovieDetailActivity mainActivity);
    void inject(OverviewFragment overviewFragment);

    void inject(CastsFragment castsFragment);

    void inject(ReviewsFragment reviewsFragment);

}
