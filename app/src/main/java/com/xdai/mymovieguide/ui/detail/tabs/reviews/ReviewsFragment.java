package com.xdai.mymovieguide.ui.detail.tabs.reviews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mymovieguide.xdai.network.response.ReviewResult;
import com.mymovieguide.xdai.network.response.Reviews;
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

public class ReviewsFragment  extends Fragment implements IReviewView {
    @Inject
    IReviewsPresenter reviewsPresenter;
    @Inject
    IImageLoader imageLoader;
    @Bind(R.id.reviews_list)
    RecyclerView reviews_list;
    ReviewListAdapter reviewListAdapter;
    ArrayList<ReviewResult> reviewResults = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_movie_reviews, viewGroup, false);
        MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
        movieDetailActivity.getComponent().inject(this);
        ButterKnife.bind(this, rootView);
        reviewsPresenter.setView(this);

        reviews_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        reviewListAdapter = new ReviewListAdapter(getContext(), reviewResults, imageLoader);
        reviews_list.setAdapter(reviewListAdapter);
        reviewsPresenter.getReviews(movieDetailActivity.getMovie_id());

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
    public void bindReviews(Reviews reviews) {
        reviewResults.addAll(reviews.getResults());

        reviewListAdapter.notifyDataSetChanged();
    }
}
