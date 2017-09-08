package com.xdai.mymovieguide.Utils;

import android.content.Context;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.xdai.mymovieguide.R;

/**
 * Created by xiangli on 9/7/17.
 */

public class YoutubePlayerUtils {

    public static void initializePlayer(Context context, YouTubePlayerView youtube_player, String video_key){
        youtube_player.initialize(context.getString(R.string.google_api_key), new YouTubePlayer.OnInitializedListener() {
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
