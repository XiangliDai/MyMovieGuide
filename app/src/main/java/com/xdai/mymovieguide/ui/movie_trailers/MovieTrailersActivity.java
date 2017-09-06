package com.xdai.mymovieguide.ui.movie_trailers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.mymovieguide.xdai.network.response.VideoResult;
import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieTrailersActivity extends YouTubeBaseActivity implements IVideoView {
    private final String TAG = getClass().getCanonicalName();
    @Inject
    IVideosPresenter videosPresenter;
    @Inject
    IImageLoader imageLoader;
    @Bind(R.id.videos_list)
    RecyclerView video_list;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    VideoListAdapter videoListAdapter;
    ArrayList<VideoResult> videoResults = new ArrayList<>();
    private MovieTrailersActivityComponent component;

    MovieTrailersActivityComponent component() {
        if (component == null) {
            component = DaggerMovieTrailersActivityComponent.builder()
                    .movieTrailersActivityModule(new MovieTrailersActivityModule(this))
                    .build();
        }
        return component;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_trailer);
        component().inject(this);

        ButterKnife.bind(this, this.findViewById(android.R.id.content));
        int movie_id = getIntent().getIntExtra("movie_id", 0);
        String movie_name = getIntent().getStringExtra("movie_name");
        toolbar.setVisibility(View.VISIBLE);

        toolbar.setTitle(movie_name);
        //setSupportActionBar(toolbar);

        videosPresenter.setView(this);

        video_list.setLayoutManager(new LinearLayoutManager(this));
        videoListAdapter = new VideoListAdapter(this, videoResults, imageLoader);
        video_list.setAdapter(videoListAdapter);
        videosPresenter.getVideos(movie_id);


    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void bindVideos(Videos videos) {


        videoResults.addAll(videos.getResults());

        videoListAdapter.notifyDataSetChanged();
    }
}
