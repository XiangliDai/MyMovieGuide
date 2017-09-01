package com.xdai.mymovieguide.ui.detail.tabs.overview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.BaseInterface;
import com.xdai.mymovieguide.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class InfoAdapter<T extends BaseInterface>  extends  RecyclerView.Adapter<InfoAdapter.ViewHolder> {
    private List<T> list;
    private Context context;

    public InfoAdapter(Context context, ArrayList<T> data) {
        this.context = context;
        this.list = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.ia_layout_name_item, parent, false);
        return new InfoAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(InfoAdapter.ViewHolder holder, int position) {
        T t = list.get(position);
        holder.text_name.setText(t.getName());
    }

    public void add(List<T> data){
        if(list == null){
            list = new ArrayList<T>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_name)
        TextView text_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}