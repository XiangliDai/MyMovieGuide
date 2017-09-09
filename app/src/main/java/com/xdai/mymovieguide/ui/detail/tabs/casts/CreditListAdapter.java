package com.xdai.mymovieguide.ui.detail.tabs.casts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovieguide.xdai.network.response.IPerson;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.data_bind.PersonViewHolder;
import com.xdai.mymovieguide.data_bind.PersonDataBinder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangli on 8/31/17.
 */

public class CreditListAdapter<T extends IPerson>  extends  RecyclerView.Adapter<PersonViewHolder> {
    private List<T> list;
    private Context context;
    private  IImageLoader imageLoader;

    public CreditListAdapter(Context context, ArrayList<T> data, IImageLoader imageLoader) {
        this.context = context;
        this.list = data;
        this.imageLoader = imageLoader;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_person_item, parent, false);
        return new PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder holder, int position) {
        T t = list.get(position);
        PersonDataBinder<T> personDataBinder = new PersonDataBinder<>(context, t, (PersonViewHolder) holder, imageLoader);
        personDataBinder.bind();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

}