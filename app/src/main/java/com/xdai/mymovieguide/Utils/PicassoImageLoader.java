package com.xdai.mymovieguide.Utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

/**
 * Created by xiangli on 8/25/17.
 */
public class PicassoImageLoader implements IImageLoader {
    private Context context;

    public PicassoImageLoader(Context context){
        this.context = context;
    }

    @Override
    public void loadImageIntoTarget(String imageUrl, Target target) {
        Picasso.with(context)
                .load(imageUrl)
                .into(target);
    }

    @Override
    public void loadImageIntoImageView(Uri imageUrl, Target target) {
        Picasso.with(context)
                .load(imageUrl)
                .into(target);
    }

    @Override
    public void loadImageIntoImageView(String imageUrl, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public void loadImageIntoImageView(int resId, ImageView imageView) {
        Picasso.with(context)
                .load(resId)
                .into(imageView);
    }

    @Override
    public void loadImageIntoTarget(String imageUrl, int placeHolderResId, Target target){
        Picasso.with(context)
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .placeholder(placeHolderResId)
                .into(target);
    }

    @Override
    public void loadImageIntoImageView(String imageUrl, int placeHolderResId, ImageView imageView){
        Picasso.with(context)
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .placeholder(placeHolderResId)
                .into(imageView);
    }

    @Override
    public void loadImageIntoImageView(Uri imageUrl, Drawable placeHolder, ImageView imageView) {
        Picasso.with(context)
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .placeholder(placeHolder)
                .into(imageView);
    }

    @Override
    public void loadImageIntoImageView(int resId, ImageView imageView, Callback callback) {
        Picasso.with(context)
                .load(resId)
                .into(imageView);
    }

    @Override
    public void loadImageIntoImageView(String imageUrl, int placeHolderResId, ImageView imageView, Callback callback) {
        Picasso.with(context)
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .placeholder(placeHolderResId)
                .into(imageView, callback);
    }

    @Override
    public void loadImageIntoImageView(Uri imageUrl, int placeHolderResId, ImageView imageView, Callback callback) {
        Picasso.with(context)
                .load(imageUrl)
                .memoryPolicy(MemoryPolicy.NO_STORE, MemoryPolicy.NO_CACHE)
                .placeholder(placeHolderResId)
                .into(imageView, callback);
    }

    @Override
    public RequestCreator load(String imageUrl) {
        return Picasso.with(context).load(imageUrl);
    }
}