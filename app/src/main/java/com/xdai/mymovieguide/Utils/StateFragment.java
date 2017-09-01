package com.xdai.mymovieguide.Utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by xiangli on 8/24/17.
 */

public class StateFragment extends Fragment {
    private HashMap<String, Object> storeMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    public void put(Object obj){
        put(obj.getClass().getCanonicalName(), obj);
    }

    public void put(String key, Object obj) {
        storeMap.put(key, obj);
    }

    public <T> T get(String key){
        return (T) storeMap.get(key);
    }
}
