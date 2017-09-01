package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiangli on 8/25/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Date {
    String maximum;
    String minimum;

    public String getMaximum() {
        return maximum;
    }

    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }
}
