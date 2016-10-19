package com.example.mobilestyx.retrofit;

/**
 * Created by Chari on 10/11/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public static final String PREFS_NAME = "PRODUCT_APP";
    public static final String FAVORITES = "Product_Favorite";

    public SharedPreference() {
        super();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Actor> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.commit();
    }

    public void addFavorite(Context context, Actor actor) {
        List<Actor> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<Actor>();
        favorites.add(actor);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Actor actor) {
        ArrayList<Actor> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(actor);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Actor> getFavorites(Context context) {
        SharedPreferences settings;
        List<Actor> favorites;


        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Actor[] favoriteItems = gson.fromJson(jsonFavorites,
                    Actor[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<Actor>(favorites);
        } else
            return null;

        return (ArrayList<Actor>) favorites;
    }
}

