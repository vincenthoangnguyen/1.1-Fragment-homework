package com.vincent.hoangnguyen.FragmentExample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mbutton;
    private Boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mbutton.setText(R.string.close);
            }
        }


        mbutton = findViewById(R.id.open_button);
        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFragmentDisplayed){ //false
                    displayFragment();
                }
                else
                    closeFragment();
            }
        });
    }
    private void displayFragment() {
        FragmentSimple fragmentSimple = FragmentSimple.newInstance();
        // get the FragmentManager and start a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // add
        fragmentTransaction.add(R.id.fragment_container, fragmentSimple).addToBackStack(null).commit();

        // change name button and set state for boolean
        mbutton.setText(R.string.close);
        isFragmentDisplayed = true;

    }
    private void closeFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSimple fragmentSimple = (FragmentSimple) fragmentManager.findFragmentById(R.id.fragment_container);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        assert fragmentSimple != null;
        fragmentTransaction.remove(fragmentSimple).commit();

        // change button text and boolean
        mbutton.setText(R.string.open);
        isFragmentDisplayed = false;
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }
}