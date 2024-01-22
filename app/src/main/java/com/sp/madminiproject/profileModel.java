package com.sp.madminiproject;

public class profileModel {
    String profile_name;
    String profile_age;
    String profile_relationship;
    byte[] profile_image;

    public profileModel(String profile_name, String profile_age, String profile_relationship, byte[] profile_image) {
        this.profile_name = profile_name;
        this.profile_age = profile_age;
        this.profile_relationship = profile_relationship;
        this.profile_image = profile_image;

        public String getProfileName() { return profile_name; }
    }
}
