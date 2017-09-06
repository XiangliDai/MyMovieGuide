package com.xdai.mymovieguide.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.xdai.mymovieguide.Utils.StateMaintainer;
import com.xdai.mymovieguide.mvp.IPresenter;
import com.xdai.mymovieguide.mvp.IView;

/**
 * Created by xiangli on 7/12/17.
 */

public abstract class BaseActivity<P extends IPresenter>  extends AppCompatActivity implements IView {
    private final String TAG = getClass().getCanonicalName();

    public StateMaintainer getStateMaintainer() {
        return stateMaintainer;
    }

    private StateMaintainer stateMaintainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initStateManager() {
        stateMaintainer = new StateMaintainer(TAG, getSupportFragmentManager());
        if (stateMaintainer.isFirstTime()) {
            initializeStore();
        } else {
            reinitializeStore();
        }
    }

    protected abstract void initializeStore();

    protected abstract  void reinitializeStore() ;

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
