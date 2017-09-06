package com.xdai.mymovieguide.ui.detail.tabs.reviews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.IPerson;
import com.mymovieguide.xdai.network.response.ReviewResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class ReviewListAdapter  extends  RecyclerView.Adapter<ReviewListAdapter.PersonViewHolder> {
    private List<ReviewResult> list;
    private Context context;
    private  IImageLoader imageLoader;

    public ReviewListAdapter(Context context, ArrayList<ReviewResult> data, IImageLoader imageLoader) {
        this.context = context;
        this.list = data;
        this.imageLoader = imageLoader;
    }

    @Override
    public ReviewListAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_person_item, parent, false);
        return new ReviewListAdapter.PersonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReviewListAdapter.PersonViewHolder holder, int position) {
        ReviewResult t = list.get(position);
        holder.person_name.setText(t.getAuthor());
        holder.person_title.setText(t.getContent());

        holder.link.setVisibility(View.VISIBLE);
        holder.link.setText(t.getUrl());

    }

    public void add(List<ReviewResult> data){
        if(list == null){
            list = new ArrayList<ReviewResult>();
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
        @Bind(R.id.link)
        TextView link;
        public PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}