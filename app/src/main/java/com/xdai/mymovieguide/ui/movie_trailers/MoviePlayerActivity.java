package com.xdai.mymovieguide.ui.movie_trailers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.mymovieguide.xdai.network.response.VideoResult;
import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;

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

        //toolbar.setTitle(movie_name);
        //setSupportActionBar(toolbar);



        youtube_player.initialize(getString(R.string.google_api_key), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean wasRestored) {
                if (!wasRestored) {

                    youTubePlayer.cueVideo(video_key);
                    youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                        @Override
                        public void onPlaying() {

                        }

                        @Override
                        public void onPaused() {

                        }

                        @Override
                        public void onStopped() {

                        }

                        @Override
                        public void onBuffering(boolean b) {

                        }

                        @Override
                        public void onSeekTo(int i) {

                        }
                    });
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });




    }

}
