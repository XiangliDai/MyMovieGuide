package com.mymovieguide.xdai.network.config;

import retrofit2.Retrofit;

/**
 * Created by xiangli on 7/8/17.
 */

public interface IApiRetrofitProvider {
    Retrofit getTMDBApiRetrofit();
}
