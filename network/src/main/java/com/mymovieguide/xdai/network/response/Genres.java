package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiangli on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genres {
    @JsonProperty("genres")
    private ArrayList<Genre> genres;
    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<Genre> genres) {
        this.genres = genres;
    }
}

