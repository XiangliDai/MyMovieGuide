package com.xdai.mymovieguide.mvp;

/**
 * Created by xiangli on 7/12/17.
 */

public interface IPresenter<V extends IView> {
    V getView();
    void setView(V view);
}
