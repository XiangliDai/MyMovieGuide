package com.xdai.mymovieguide.ui.detail.tabs.trailers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.mymovieguide.xdai.network.response.VideoResult;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.Utils.NavigateService;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class VideoListAdapter extends  RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {
    private static String TAG = VideoListAdapter.class.getCanonicalName();
    private List<VideoResult> list;
    private Context context;
    private  IImageLoader imageLoader;

    public VideoListAdapter(Context context, ArrayList<VideoResult> data, IImageLoader imageLoader) {
        this.context = context;
        this.list = data;
        this.imageLoader = imageLoader;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.layout_trailer_item, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        VideoResult t = list.get(position);
        holder.text_name.setText(t.getName());
        holder.text_size.setText(String.format("Size: HD(%d)", t.getSize()));

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener  onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener(){
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize(context.getString(R.string.google_api_key), new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(t.getKey());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
        holder.playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Video payer page
                NavigateService.launchVideoPlayer(context, t.getKey());
            }
        });
    }

    public void add(List<VideoResult> data){
        if(list == null){
            list = new ArrayList<VideoResult>();
        }
        list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_name)
        TextView text_name;
        @Bind(R.id.text_size)
        TextView text_size;
       @Bind(R.id.youtube_thumbnail)
       com.google.android.youtube.player.YouTubeThumbnailView youTubeThumbnailView;
        @Bind(R.id.btnYoutube_player)
        protected ImageView playButton;
        @Bind(R.id.relativeLayout_over_youtube_thumbnail)
        RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        public com.google.android.youtube.player.YouTubePlayerView youtube_player;
        public VideoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}