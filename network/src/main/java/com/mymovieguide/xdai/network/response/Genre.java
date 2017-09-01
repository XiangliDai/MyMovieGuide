package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiangli on 7/8/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre implements BaseInterface {
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String name;

}
