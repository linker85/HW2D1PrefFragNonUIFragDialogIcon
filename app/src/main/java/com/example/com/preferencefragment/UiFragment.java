package com.example.com.preferencefragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * This is a fragment showing UI that will be updated from work done
 * in the retained fragment.
 */
public class UiFragment extends Fragment {   //UI Fragment that is used
    RetainedFragment mWorkFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_ui, container, false);

        // Watch for button clicks.
        Button button = (Button)v.findViewById(R.id.restart);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mWorkFragment.restart();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getFragmentManager();

        // Check to see if we have retained the worker fragment.
        mWorkFragment = (RetainedFragment)fm.findFragmentByTag("work");

        // If not retained (or first time running), we need to create it.
        if (mWorkFragment == null) {
            mWorkFragment = new RetainedFragment();   //create instance of NON UI Fragment
            // Tell it who it is working with.
            mWorkFragment.setTargetFragment(this, 0);
            fm.beginTransaction().add(mWorkFragment, "work").commit();  //NON UI Fragment
        }
    }

}