package com.xdai.mymovieguide.ui.detail.tabs.trailers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovieguide.xdai.network.response.VideoResult;
import com.mymovieguide.xdai.network.response.Videos;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.ui.detail.MovieDetailActivity;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class TrailersFragment extends Fragment implements ITrailerView {
    @Inject
    ITrailersPresenter trailersPresenter;
    @Inject
    IImageLoader imageLoader;
    @Bind(R.id.recycler_list)
    RecyclerView video_list;

    VideoListAdapter videoListAdapter;
    ArrayList<VideoResult> videoResults = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_with_recycler_only, viewGroup, false);
        MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
        movieDetailActivity.getComponent().inject(this);
        ButterKnife.bind(this, rootView);
        trailersPresenter.setView(this);



        video_list.setLayoutManager(new LinearLayoutManager(movieDetailActivity));
        videoListAdapter = new VideoListAdapter(movieDetailActivity, videoResults, imageLoader);
        video_list.setAdapter(videoListAdapter);
        trailersPresenter.getTrailers(movieDetailActivity.getMovie_id());

        return rootView;
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
    public void bindTrailers(Videos videos) {

        videoResults.addAll(videos.getResults());

        videoListAdapter.notifyDataSetChanged();
    }
}
