package com.xdai.mymovieguide.ui.main;


import dagger.Component;

@Component(
        modules = {
            MainActivityModule.class
        })
public interface MainActivityComponent {
    void inject(MainActivity mainActivity);
}
