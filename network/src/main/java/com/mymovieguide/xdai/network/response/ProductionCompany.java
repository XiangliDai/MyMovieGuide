package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiangli on 8/29/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionCompany implements BaseInterface {
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

    String name;
   int  id;

}
