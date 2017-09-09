package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by xiangli on 9/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Search {
    int page;

    ArrayList<SearchResult> results;
    int total_pages;
    int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<SearchResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<SearchResult> results) {
        this.results = results;
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
}
