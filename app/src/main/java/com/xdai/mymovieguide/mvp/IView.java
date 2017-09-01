package com.xdai.mymovieguide.mvp;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IView {
    void showToast(String message);
    void showProgress();
    void hideProgress();
}
