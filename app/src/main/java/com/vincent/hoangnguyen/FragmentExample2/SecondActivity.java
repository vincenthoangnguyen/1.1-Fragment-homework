package com.vincent.hoangnguyen.FragmentExample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    private Button mButton;
    private boolean isFragmentDisplayed_2 = false;
    static final String STATE_FRAGMENT2 = "state_of_fragment_2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mButton = findViewById(R.id.open_button_second);
        if(savedInstanceState != null ) // config changed
        {
            isFragmentDisplayed_2 = savedInstanceState.getBoolean(STATE_FRAGMENT2);
            if(isFragmentDisplayed_2){
                mButton.setText(R.string.close);
            }
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFragmentDisplayed_2) {
                    displayFragment_2();
                } else {
                    closeFragment_2();
                }
            }
        });
    }

    private void displayFragment_2() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentSimple fragmentSimple = FragmentSimple.newInstance();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_2, fragmentSimple).addToBackStack(null).commit();
        isFragmentDisplayed_2 = true;
        mButton.setText(R.string.close);
    }

    private void closeFragment_2() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentSimple fragmentSimple = (FragmentSimple)fragmentManager.findFragmentById(R.id.fragment_container_2);
        assert fragmentSimple != null;
        fragmentTransaction.remove(fragmentSimple).commit();
        mButton.setText(R.string.open);
        isFragmentDisplayed_2 = false;
    }


    @Override
    protected void onSaveInstanceState( Bundle outState) {
        outState.putBoolean(STATE_FRAGMENT2, isFragmentDisplayed_2);
        super.onSaveInstanceState(outState);
    }
    public void previous_click(View view){
        finish();

    }

}