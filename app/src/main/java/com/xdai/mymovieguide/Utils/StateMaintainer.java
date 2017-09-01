package com.xdai.mymovieguide.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.lang.ref.WeakReference;

/**
 * Created by xiangli on 8/24/17.
 */

public class StateMaintainer {
    private WeakReference<FragmentManager> fragmentManagerWeakReference;
    private String tagString;
    private StateFragment stateFragment;
    public StateMaintainer(String tag, FragmentManager fragmentManager){
        this.tagString = tag;
        this.fragmentManagerWeakReference = new WeakReference<FragmentManager>(fragmentManager);
    }

    public boolean isFirstTime(){
       stateFragment = (StateFragment) fragmentManagerWeakReference.get().findFragmentByTag(tagString);
        if(stateFragment == null){
            stateFragment = new StateFragment();
            fragmentManagerWeakReference.get().beginTransaction().add(stateFragment, tagString).commit();
            return true;
        } else {
            return false;
        }
    }

    public void put(String key, Object obj){
        stateFragment.put(key, obj);
    }
    public void put(Object obj){
        put(obj.getClass().getCanonicalName(), obj);
    }

    public <T> T get(String key){
        return stateFragment.get(key);
    }

    public boolean hasKey(String key){
        return stateFragment.get(key) != null;
    }
}
