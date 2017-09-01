package com.xdai.mymovieguide.Utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;


import com.squareup.picasso.Callback;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

/**
 * Created by xiangli on 8/25/17.
 */

public interface IImageLoader {


    void loadImageIntoTarget(String imageUrl, Target target);
    void loadImageIntoImageView(Uri imageUrl, Target target);
    void loadImageIntoTarget(String imageUrl, int placeHolderResId, Target target);

    void loadImageIntoImageView(int resId, ImageView imageView, Callback callback);
    void loadImageIntoImageView(String imageUrl,  int placeHolderResId, ImageView imageView, Callback callback);
    void loadImageIntoImageView(Uri imageUrl,  int placeHolderResId, ImageView imageView, Callback callback);


    void loadImageIntoImageView(String imageUrl, ImageView imageView);
    void loadImageIntoImageView(int resId, ImageView imageView);
    void loadImageIntoImageView(String imageUrl, int placeHolderResId, ImageView imageView);
    void loadImageIntoImageView(Uri imageUrl, Drawable placeHolder, ImageView imageView);


    RequestCreator load(String profile_url);

}
