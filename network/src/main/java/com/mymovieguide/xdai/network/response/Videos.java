package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

/**
 * Created by xiangli on 8/30/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Videos {
   int id;

    ArrayList<VideoResult> results;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<VideoResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<VideoResult> results) {
        this.results = results;
    }


}
