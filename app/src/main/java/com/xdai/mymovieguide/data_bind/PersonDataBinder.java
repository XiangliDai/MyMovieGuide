package com.xdai.mymovieguide.data_bind;

import android.content.Context;

import com.mymovieguide.xdai.network.response.IPerson;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.data_bind.PersonViewHolder;

/**
 * Created by xiangli on 9/8/17.
 */

public class PersonDataBinder<P extends IPerson> {
    P personData;
    PersonViewHolder personViewHolder;
    Context context;
    private IImageLoader imageLoader;
    public PersonDataBinder(Context context, P personData, PersonViewHolder personViewHolder, IImageLoader imageLoader){
        this.context = context;
        this.personData = personData;
        this.personViewHolder = personViewHolder;
        this.imageLoader = imageLoader;
    }

    public void bind(){
        personViewHolder.person_name.setText(personData.getName());
        personViewHolder.person_title.setText(personData.getTitle());
        if(personData.getProfile_path()!= null) {
            imageLoader.loadImageIntoImageView(context.getString(R.string.profile_image_url) + personData.getProfile_path(), personViewHolder.person_profile);
        }
    }
}
