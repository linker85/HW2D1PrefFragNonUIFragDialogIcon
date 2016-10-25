package com.example.com.preferencefragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CheckBox prefCheckBox;
    private TextView prefEditText;
    private static final String MY_TAG = "MY_TAG_FRAGMENT";

    final Context context = this;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get reference to components
        prefCheckBox = (CheckBox)findViewById(R.id.prefCheckBox);
        prefEditText = (TextView)findViewById(R.id.prefEditText);

        // Load preferences
        loadPref();
        // First time init, create the UI.
        if (savedInstanceState == null) {    //UI Fragement
            // Add dynamically with fragment manager
            FragmentManager fragmentManager = getSupportFragmentManager();
            UiFragment uiFragment = new UiFragment();
            fragmentManager
                    .beginTransaction()
                    .add(R.id.a_main_frame, uiFragment, MY_TAG)
                    .commit();
        }

        // For dialog that does not extends from fragment
        button = (Button) findViewById(R.id.buttonShowCustomDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.activity_dialog);
                dialog.setTitle("Title...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.ic_3d_rotation_black_24dp);
                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        /////////////////////////////////////////////////


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

      /*
       * Because it's onlt ONE option in the menu.
       * In order to make it simple, We always start SetPreferenceActivity
       * without checking.
       */
        // If menu selected then call activity for the share preferences activity
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_logout1) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, SetPreferenceActivity.class);
            startActivityForResult(intent, 0);
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        //super.onActivityResult(requestCode, resultCode, data);

      /*
       * To make it simple, always re-load Preference setting.
       */
        loadPref();
    }

    // Get shared preferences and update the view components
    private void loadPref(){
        SharedPreferences mySharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Key and default value if key does not exists
        boolean my_checkbox_preference = mySharedPreferences.getBoolean("checkbox_preference", true);
        prefCheckBox.setChecked(my_checkbox_preference);

        String my_edittext_preference  = mySharedPreferences.getString("edittext_preference", "hello");
        prefEditText.setText(my_edittext_preference);
    }

}
