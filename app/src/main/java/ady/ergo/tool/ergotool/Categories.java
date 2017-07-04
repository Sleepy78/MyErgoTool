package ady.ergo.tool.ergotool;

import android.app.Activity;

import java.util.ArrayList;

/**
 * Created by Aurel on 04/07/2017.
 */

public class Categories {
    private ArrayList<Categorie> categories;

    public Categories(Activity activity) {
        this.categories = new ArrayList<Categorie>();
        categories.add(new Categorie(activity));
    }
}
