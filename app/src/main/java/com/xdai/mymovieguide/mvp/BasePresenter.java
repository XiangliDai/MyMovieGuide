package com.xdai.mymovieguide.mvp;

import com.xdai.mymovieguide.mvp.IPresenter;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 7/12/17.
 */

public class BasePresenter<V extends IView>  implements IPresenter<V>{
    private V view;

    @Override
    public V getView() {
        return view;
    }

    @Override
    public void setView(V view){
        this.view = view;
    }
}
