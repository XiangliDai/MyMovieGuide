package com.xdai.mymovieguide.ui.detail.tabs.casts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.BaseInterface;
import com.mymovieguide.xdai.network.response.Cast;
import com.mymovieguide.xdai.network.response.IPerson;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class CreditListAdapter<T extends IPerson>  extends  RecyclerView.Adapter<CreditListAdapter.PersonViewHolder> {
    private List<T> list;
    private Context context;
    private  IImageLoader imageLoader;

    public CreditListAdapter(Context context, ArrayList<T> data, IImageLoader imageLoader) {
        this.context = context;
        this.list = data;
        this.imageLoader = imageLoader;
    }

    @Override
    public CreditListAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_person_item, parent, false);
        return new CreditListAdapter.PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CreditListAdapter.PersonViewHolder holder, int position) {
        T t = list.get(position);
        holder.person_name.setText(t.getName());
        holder.person_title.setText(t.getTitle());
        imageLoader.loadImageIntoImageView(context.getString(R.string.profile_image_url) + t.getProfile_path(), holder.person_profile);

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


    public class PersonViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.person_name)
        TextView person_name;
        @Bind(R.id.person_title)
        TextView person_title;
        @Bind(R.id.person_profile)
        ImageView person_profile;
        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}