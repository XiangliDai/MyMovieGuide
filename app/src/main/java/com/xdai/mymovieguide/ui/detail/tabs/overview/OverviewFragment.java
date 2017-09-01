package com.xdai.mymovieguide.ui.detail.tabs.overview;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mymovieguide.xdai.network.response.Genre;
import com.mymovieguide.xdai.network.response.MovieDetail;
import com.mymovieguide.xdai.network.response.ProductionCompany;
import com.mymovieguide.xdai.network.response.ProductionCountry;
import com.xdai.mymovieguide.R;
import com.xdai.mymovieguide.mvp.IView;
import com.xdai.mymovieguide.ui.detail.MovieDetailActivity;
import com.xdai.mymovieguide.ui.detail.tabs.casts.ICastsPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by xiangli on 8/31/17.
 */
public class OverviewFragment extends Fragment implements IOverviewView{
    @Inject
    IOverviewPresenter overviewPresenter;

    @Bind(R.id.movie_desc)
    TextView movie_desc;
    @Bind(R.id.movie_release)
    TextView movie_release;
    @Bind(R.id.movie_language)
    TextView movie_language;
    @Bind(R.id.movie_runtime)
    TextView movie_runtime;
    @Bind(R.id.movie_homepage)
    TextView movie_homepage;
    @Bind(R.id.production_companies_list)
    RecyclerView production_companies_list;
    @Bind(R.id.production_countries_list)
    RecyclerView production_countries_list;
    @Bind(R.id.genres_list)
    RecyclerView genres_list;

    ArrayList<Genre> genreList = new ArrayList<>();
    ArrayList<ProductionCompany> companyList = new ArrayList<>();
    ArrayList<ProductionCountry> countryList = new ArrayList<>();

    InfoAdapter<Genre> genreInfoAdapter;
    InfoAdapter<ProductionCountry> countryInfoAdapter;
    InfoAdapter<ProductionCompany> companyInfoAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_movie_overview, viewGroup, false);
        MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
        movieDetailActivity.getComponent().inject(this);
        ButterKnife.bind(this, rootView);
        overviewPresenter.setView(this);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);
        production_companies_list.setLayoutManager(llm);
        companyInfoAdapter = new InfoAdapter<ProductionCompany>(getContext(), companyList);
        production_companies_list.setAdapter(companyInfoAdapter);


        genres_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        genreInfoAdapter = new InfoAdapter<Genre>(getContext(), genreList);
        genres_list.setAdapter(genreInfoAdapter);

        production_countries_list.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        countryInfoAdapter = new InfoAdapter<ProductionCountry>(getContext(), countryList);
        production_countries_list.setAdapter(countryInfoAdapter);

        genres_list.setNestedScrollingEnabled(false);
        production_companies_list.setNestedScrollingEnabled(false);
        production_countries_list.setNestedScrollingEnabled(false);


        overviewPresenter.getOverview(movieDetailActivity.getMovie_id());

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
    public void bindOverview(MovieDetail movieDetail) {
        companyList.addAll(movieDetail.getProductionCompanies());
        genreList.addAll(movieDetail.getGenres());
        countryList.addAll(movieDetail.getProductionCountries());
        genreInfoAdapter.notifyDataSetChanged();
        companyInfoAdapter.notifyDataSetChanged();
        countryInfoAdapter.notifyDataSetChanged();
        movie_desc.setText(movieDetail.getOverview());
        movie_release.setText(movieDetail.getRelease_date());
        movie_homepage.setText(movieDetail.getHomepage());
        movie_language.setText(movieDetail.getOriginal_language());
        movie_runtime.setText(movieDetail.getRuntime());

    }
}
