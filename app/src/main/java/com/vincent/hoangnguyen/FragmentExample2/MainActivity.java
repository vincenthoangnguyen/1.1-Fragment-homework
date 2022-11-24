package com.vincent.hoangnguyen.FragmentExample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mButton;
    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // If returning from a configuration change, get the
        // fragment state and set the button text.
        if(savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            if(isFragmentDisplayed){
                mButton.setText(R.string.close);
            }
        }
        mButton = findViewById(R.id.open_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!isFragmentDisplayed){  // false
                    displayFragment();
                }
                else {
                    closeFragment();
                }
            }
        });
    }
    public void displayFragment(){
        // get fragmentManager and start a transaction
        FragmentSimple fragmentSimple = FragmentSimple.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //add the fragmentSimple
        fragmentTransaction.add(R.id.fragment_container,fragmentSimple).addToBackStack(null).commit();
        // update button text
        mButton.setText(R.string.close);
        // set boolean flag to indicate fragment is open
        isFragmentDisplayed = true;
    }
    public void closeFragment(){
        //get fragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Check to see if the fragment is already showing.
        FragmentSimple fragmentSimple = (FragmentSimple) fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentSimple != null) {
            // Create and commit the transaction to remove the fragment.
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentSimple).commit();
        }
        // set text button
        mButton.setText(R.string.open);
        isFragmentDisplayed = false;
    }


    // method save state of fragment
    public void onSaveInstanceState(Bundle saveInstanceState) {
        // save the state of the fragment(true = open, false = close )
        saveInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(saveInstanceState);
    }
}