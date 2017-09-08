package com.xdai.mymovieguide.ui.detail.share;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xdai.mymovieguide.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.copy;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.email;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.facebook;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.fb_messenger;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.instagram;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.sms;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.twitter;
import static com.xdai.mymovieguide.ui.detail.share.ShareBottomSheetRecyclerAdapter.EnabledShareOptionNames.whatsapp;

/**
 * Created by xiangli on 9/6/17.
 */

public class ShareBottomSheetRecyclerAdapter extends RecyclerView.Adapter<ShareBottomSheetRecyclerAdapter.ViewHolder> {
    List<ShareOptionEnum> shareOptionEnumList;
    Context context;

    public ShareBottomSheetRecyclerAdapter(Context context) {
        this.context = context;
        shareOptionEnumList = ShareOptionEnum.getAll();
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }

    private ItemClickListener itemClickListener;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_share_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ShareOptionEnum shareOptionEnum = shareOptionEnumList.get(position);
        holder.imageView.setImageResource(shareOptionEnum.getIconResId());
        holder.sharingNetworkTextview.setText(shareOptionEnum.getTitle());


        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                switch (shareOptionEnum.getKey()) {

                    case facebook:
                    case fb_messenger:
                    case whatsapp:
                    case twitter:
                    case instagram:
                    case sms:
                    case copy:
                    case email:
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return shareOptionEnumList == null ? 0 : shareOptionEnumList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.share_network_icon)
        ImageView imageView;


        @Bind(R.id.share_network_text)
        TextView sharingNetworkTextview;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface ItemClickListener {
        void OnClicked();
    }

    public static class EnabledShareOptionNames {
        public static final String facebook = "facebook";
        public static final String fb_messenger = "fbmessenger";
        public static final String twitter = "twitter";
        public static final String instagram = "instagram";
        public static final String sms = "sms";
        public static final String email = "email";
        public static final String whatsapp = "whatsapp";
        public static final String copy = "copy";
    }

    public enum ShareOptionEnum {

        ShareFacebook(facebook, "Share on Facebook", "Sharing on Facebook is the single most important thing you can do to raise money.", R.mipmap.share_facebook),
        ShareMessenger(fb_messenger, "Share with your Facebook friends", "Directly contacting people you are close to can help getCoRetrofit the ball rolling.", R.mipmap.share_messenger),
        ShareTwitter(twitter, "Share on Twitter", "Tweet your campaign and ask your followers for their help.", R.mipmap.share_twitter),
        ShareInstagram(instagram, "Share on Instagram", "Share your campaign and ask your followers to help.", R.mipmap.share_instagram),
        ShareText(sms, "Share with Text", "The first step to success is to reach out to everyone you know.", R.mipmap.share_sms),
        ShareEmail(email, "Share with Email", "A personal heartfelt message will have a profound impact on your campaign.", R.mipmap.share_email),
        ShareWhatsApp(whatsapp, "Share with WhatsApp", "", R.mipmap.share_whatsapp),
        CopyLink(copy, "Copy link", "You need to share at least once to continue", R.mipmap.share_copy_url);

        public static final HashMap<String, ShareOptionEnum> sharePageMap = new HashMap<>();

        static {
            sharePageMap.put(ShareFacebook.key, ShareFacebook);
            sharePageMap.put(ShareMessenger.key, ShareMessenger);
            sharePageMap.put(ShareTwitter.key, ShareTwitter);
            sharePageMap.put(ShareInstagram.key, ShareInstagram);
            sharePageMap.put(ShareText.key, ShareText);
            sharePageMap.put(ShareEmail.key, ShareEmail);
            sharePageMap.put(ShareWhatsApp.key, ShareWhatsApp);
            sharePageMap.put(CopyLink.key, CopyLink);

        }

        private String key;
        private String title;
        private int iconResId;
        private String shareDescriptionLabelText;

        ShareOptionEnum(String key, String title, String shareDescriptionLabelText, int iconResId) {
            this.key = key;
            this.title = title;
            this.shareDescriptionLabelText = shareDescriptionLabelText;
            this.iconResId = iconResId;
        }

        public String getTitle() {
            return title;
        }

        public int getIconResId() {
            return iconResId;
        }


        public String getKey() {
            return key;
        }

        public static  List<ShareOptionEnum> getAll() {
            return Arrays.asList(ShareOptionEnum.values());

        }
        public String getShareDescriptionLabelText() {
            return shareDescriptionLabelText;
        }

        public void setShareDescriptionLabelText(String shareDescriptionLabelText) {
            this.shareDescriptionLabelText = shareDescriptionLabelText;
        }
    }
}
