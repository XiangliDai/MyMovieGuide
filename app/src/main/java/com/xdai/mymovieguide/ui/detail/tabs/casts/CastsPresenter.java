package com.xdai.mymovieguide.ui.detail.tabs.casts;

import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.IMovieDetailRepository;
import com.xdai.mymovieguide.ui.detail.tabs.overview.IOverviewView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xiangli on 8/31/17.
 */

public class CastsPresenter extends BasePresenter<ICreditView> implements ICastsPresenter{
    IMovieDetailRepository movieDetailRepository;
    private ICreditView creditView;

    @Override
    public void getCredit(int movie_id) {
        if(movieDetailRepository != null && creditView != null)
            movieDetailRepository.getMovieCreditsById(movie_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Credits>() {
                @Override
                public void accept(Credits credits) throws Exception {
                    creditView.bindCredit(credits);
                }
            },  throwable -> {
                if(creditView != null) {
                    creditView.hideProgress();
                    creditView.showToast(throwable.getMessage());
                }});
    }

    @Override
    public void setView(ICreditView creditView) {
        this.creditView = creditView;
    }

    public CastsPresenter(IMovieDetailRepository movieDetailRepository) {
        this.movieDetailRepository = movieDetailRepository;
    }


}
