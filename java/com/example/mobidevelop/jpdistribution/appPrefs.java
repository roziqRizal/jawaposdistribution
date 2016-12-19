package com.example.mobidevelop.jpdistribution;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

/**
 * Created by IZANK on 10/17/2016.
 */
public class appPrefs {
    private static final String USER_PREFS = "USER_PREFS";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;
    private String token = "token", firstname = "firstname", lastname = "lastname", image = "image", routeID = "routeID", email = "email";

    public appPrefs(Context context){
        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public String getFirstname() {
        return appSharedPrefs.getString(firstname, "unkown");
    }
    public void setFirstname( String _firstName) {
        prefsEditor.putString(firstname, _firstName).commit();
    }

    public String getEmail(){
        return appSharedPrefs.getString(email,"unknown");
    }
    public void setEmail(String _email){
        prefsEditor.putString(email, _email).commit();
    }

    public String getRouteID() {
        return appSharedPrefs.getString(routeID, "unkown");
    }
    public void setgetRouteID( String _routeID) {
        prefsEditor.putString(routeID, _routeID).commit();
    }

    public String getLastname() {
        return appSharedPrefs.getString(lastname, "unkown");
    }
    public void setLastname( String _lastname) {
        prefsEditor.putString(lastname, _lastname).commit();
    }

    public String getToken() {
        return appSharedPrefs.getString(token, "unkown");
    }
    public void setToken( String _token) {
        prefsEditor.putString(token, _token).commit();
    }

    public String getImage() {
        return appSharedPrefs.getString(image, "unknown");
    }
    public void setImage( String _image) {prefsEditor.putString(image, _image).commit();}
}
