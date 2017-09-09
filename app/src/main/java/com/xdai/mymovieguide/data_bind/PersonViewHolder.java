package com.xdai.mymovieguide.data_bind;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdai.mymovieguide.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 9/8/17.
 */

public class PersonViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.person_name)
    public TextView person_name;
    @Bind(R.id.person_title)
    public TextView person_title;
    @Bind(R.id.person_profile)
    public ImageView person_profile;
    @Bind(R.id.know_for_list)
    public RecyclerView know_for_list;

    public PersonViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}