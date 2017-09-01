package com.mymovieguide.xdai.network.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiangli on 8/30/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Crew implements IPerson{
    String credit_id;
    String department;
    int gender;
    int id;
    String job;
    String name;
    String profile_path;
    public String getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(String credit_id) {
        this.credit_id = credit_id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_path() {
        return profile_path;
    }

    @Override
    public String getTitle() {
        return job;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }


}
