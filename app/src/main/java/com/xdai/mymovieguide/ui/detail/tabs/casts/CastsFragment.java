package com.xdai.mymovieguide.ui.detail.tabs.casts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.Cast;
import com.mymovieguide.xdai.network.response.Credits;
import com.mymovieguide.xdai.network.response.Crew;
import com.mymovieguide.xdai.network.response.Genre;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.ProductionCompany;
import com.mymovieguide.xdai.network.response.ProductionCountry;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.Utils.IImageLoader;
import com.xdai.mymovieguide.ui.detail.MovieDetailActivity;
import com.xdai.mymovieguide.ui.detail.tabs.overview.InfoAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */

public class CastsFragment extends Fragment implements ICreditView{
    @Inject
    ICastsPresenter castsPresenter;

    @Inject
    IImageLoader imageLoader;
    @Bind(R.id.cast_list)
    RecyclerView cast_list;
    @Bind(R.id.crew_list)
    RecyclerView crew_list;

    ArrayList<Cast> castArrayList = new ArrayList<>();
    ArrayList<Crew> crewArrayList = new ArrayList<>();

    CreditListAdapter<Cast> castListAdapter;
    CreditListAdapter<Crew> crewListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_movie_credit, viewGroup, false);
        MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
        movieDetailActivity.getComponent().inject(this);
        ButterKnife.bind(this, rootView);
        castsPresenter.setView(this);

        GridLayoutManager gm1 = new GridLayoutManager(getActivity(), 2);
        cast_list.setLayoutManager(gm1);
        castListAdapter = new CreditListAdapter<>(getContext(), castArrayList, imageLoader);
        cast_list.setAdapter(castListAdapter);


        GridLayoutManager gm2 = new GridLayoutManager(getActivity(), 2);
        crew_list.setLayoutManager(gm2);
        crewListAdapter = new CreditListAdapter<>(getContext(), crewArrayList, imageLoader);
        crew_list.setAdapter(crewListAdapter);

        crew_list.setNestedScrollingEnabled(false);
        cast_list.setNestedScrollingEnabled(false);

        castsPresenter.getCredit(movieDetailActivity.getMovie_id());

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
    public void bindCredit(Credits credits) {
        castArrayList.addAll(credits.getCast());
        crewArrayList.addAll(credits.getCrew());

        castListAdapter.notifyDataSetChanged();
        crewListAdapter.notifyDataSetChanged();
    }
}
