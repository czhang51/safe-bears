package com.example.czhan.safebears.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.io.Serializable;

@ParseClassName("Crime")
public class Crime extends ParseObject implements Serializable{
    private static final String KEY_USER = "user";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TIME= "time";
    private static final String KEY_SUS_GENDER = "gender";
    private static final String KEY_SUS_AGE = "age";
    private static final String KEY_SUS_HEIGHT_FEET = "heightFeet";
    private static final String KEY_SUS_HEIGHT_INCHES = "heightInches";
    private static final String KEY_SUS_WEIGHT = "weight";
    private static final String KEY_SUS_HAIR = "hair";
    private static final String KEY_SUS_EYE = "eye";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public String getLocation() {
        return getString(KEY_LOCATION);
    }

    public void setLocation(String location) {
        put(KEY_LOCATION, location);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public String getTime() {
        return getString(KEY_TIME);
    }

    public void setTime(String time) {
        put(KEY_TIME, time);
    }

    public String getGender() {
        return getString(KEY_SUS_GENDER);
    }

    public void setGender(String gender) {
        put(KEY_SUS_GENDER, gender);
    }

    public Integer getAge() {
        return getInt(KEY_SUS_AGE);
    }

    public void setAge(Integer age) {
        put(KEY_SUS_AGE, age);
    }

    public Integer getHeightFeet() {
        return getInt(KEY_SUS_HEIGHT_FEET);
    }

    public void setHeightFeet(Integer heightFeet) {
        put(KEY_SUS_HEIGHT_FEET, heightFeet);
    }

    public Integer getHeightInches() {
        return getInt(KEY_SUS_HEIGHT_INCHES);
    }

    public void setHeightInches(Integer heightInches) {
        put(KEY_SUS_HEIGHT_FEET, heightInches);
    }

    public Integer getWeight() {
        return getInt(KEY_SUS_HEIGHT_FEET);
    }

    public void setWeight(Integer weight) {
        put(KEY_SUS_WEIGHT, weight);
    }

    public String getHair() {
        return getString(KEY_SUS_HAIR);
    }

    public void setHair(String hair) {
        put(KEY_SUS_HAIR, hair);
    }

    public String getEye() {
        return getString(KEY_SUS_EYE);
    }

    public void setEye(String eye) {
        put(KEY_SUS_EYE, eye);
    }
}
