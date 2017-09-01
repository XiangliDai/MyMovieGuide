package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiangli on 8/29/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductionCountry implements BaseInterface{
   String iso_3166_1;

    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public void setIso_3166_1(String iso_3166_1) {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    String name;


}
