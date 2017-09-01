package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by xiangli on 8/25/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movies {
    int page;
    ArrayList<MovieResult> results;
    int total_pages;
    int total_results;
    Date dates;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<MovieResult> getMovieResults() {
        return results;
    }

    public void setMovieResults(ArrayList<MovieResult> movieResults) {
        this.results = movieResults;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public Date getDates() {
        return dates;
    }

    public void setDates(Date dates) {
        this.dates = dates;
    }


}
