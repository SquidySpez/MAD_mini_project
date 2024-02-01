package com.sp.madminiproject;

public class SocialModel {
    String social_name;
    String social_age;
    String social_relationship;

    public SocialModel(String social_name, String social_age, String social_relationship) {
        this.social_name = social_name;
        this.social_age = social_age;
        this.social_relationship = social_relationship;

    }

    public String getSocial_name() {
        return social_name;
    }

    public String getSocial_age() {
        return social_age;
    }

    public String getSocial_relationship() {
        return social_relationship;
    }
}
