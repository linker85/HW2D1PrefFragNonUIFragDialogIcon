package com.example.com.preferencefragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by raul on 24/10/2016.
 */

public class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
