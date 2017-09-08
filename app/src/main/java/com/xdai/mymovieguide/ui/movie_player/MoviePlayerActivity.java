package com.xdai.mymovieguide.ui.movie_player;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.YoutubePlayerUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MoviePlayerActivity extends YouTubeBaseActivity {
    private final String TAG = getClass().getCanonicalName();

    @Bind(R.id.youtube_player)
    YouTubePlayerView youtube_player;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_player);

        ButterKnife.bind(this, this.findViewById(android.R.id.content));
        String video_key = getIntent().getStringExtra("video_key");

        YoutubePlayerUtils.initializePlayer(this, youtube_player, video_key);
    }

}
