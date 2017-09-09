package com.xdai.mymovieguide.repository;

import android.content.Context;

import com.mymovieguide.xdai.network.client.ApiRestClient;
import com.mymovieguide.xdai.network.config.IApiRetrofitProvider;
import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.Movies;
import com.mymovieguide.xdai.network.response.Reviews;
import com.mymovieguide.xdai.network.response.Search;
import com.mymovieguide.xdai.network.response.SearchResult;
import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.R;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;

/**
 * Created by xiangli on 8/30/17.
 */

public class SearchRepository implements ISearchRepository {
    private Context context;
    private IApiRetrofitProvider retrofitProvider;
    private ApiRestClient apiRestClient;
    private String apiKey;
    private String language;
    private String region;
    public SearchRepository(Context context, IApiRetrofitProvider retrofitProvider) {
        this.context = context;

        apiRestClient = retrofitProvider.getTMDBApiRetrofit().create(ApiRestClient.class);
        apiKey = context.getString(R.string.api_key);
        language = Locale.getDefault().toString();
        region = Locale.getDefault().getCountry();
    }


    @Override
    public Observable<Search> getSearchResultsByQuery(String query, int page) {
        return apiRestClient.getSearchResultsByQuery(query, true,  page, language,region,  apiKey);

    }
}
