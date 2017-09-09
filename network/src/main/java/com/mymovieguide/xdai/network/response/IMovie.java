package com.mymovieguide.xdai.network.response;

/**
 * Created by xiangli on 9/8/17.
 */

public interface IMovie {
    String getPoster_path();
    boolean isAdult();
    String getOverview();
    int getId();
    String getTitle();
    String getBackdrop_path();
    float getPopularity();
    float getVote_average();

    String getRelease_date();
}
