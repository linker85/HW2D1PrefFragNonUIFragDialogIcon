package com.example.com.preferencefragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by raul on 24/10/2016.
 */

public class SetPreferenceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        //Dynamically add prefers fragment to SetPreferenceActivity
        getFragmentManager().beginTransaction().replace(android.R.id.content,
                new PrefsFragment()).commit();
    }
}
