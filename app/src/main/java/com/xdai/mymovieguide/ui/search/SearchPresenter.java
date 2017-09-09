package com.xdai.mymovieguide.ui.search;

import android.util.Log;

import com.mymovieguide.xdai.network.response.Search;
import com.mymovieguide.xdai.network.response.SearchResult;
import com.xdai.mymovieguide.mvp.BasePresenter;
import com.xdai.mymovieguide.repository.ISearchRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by xiangli on 7/12/17.
 */

public class SearchPresenter extends BasePresenter<ISearchView> implements ISearchPresenter {
    private static String TAG = SearchPresenter.class.getCanonicalName();
    private ISearchRepository searchRepository;
    private int page = 1;

    public SearchPresenter(ISearchRepository searchRepository) {
        super();
        this.searchRepository = searchRepository;
    }

    @Override
    public void loadSearchResultByQuery(String query, boolean clearPage) {
        if(clearPage) page =1;
        searchRepository.getSearchResultsByQuery(query, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(onReceiveData, throwableConsumer);
    }

    private Consumer<Search> onReceiveData = search -> {
        this.page++;
        Log.d(TAG, "page= " + page);
        getView().bindSearchResults(search.getResults());
    };

    private Consumer<Throwable> throwableConsumer = throwable -> {
        if (getView() != null) {
            getView().hideProgress();
            getView().showToast(throwable.getMessage());
        }
    };

    @Override
    public void setView(ISearchView searchView) {
        super.setView(searchView);
    }

}
