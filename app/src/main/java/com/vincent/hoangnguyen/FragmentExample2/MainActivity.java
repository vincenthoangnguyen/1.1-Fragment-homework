package com.vincent.hoangnguyen.FragmentExample2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button mbutton;
    private Boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("state", isFragmentDisplayed.toString());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbutton = findViewById(R.id.open_button);
        // saveInstanceState = null nghĩa là activity ko thay đổi trạng thái
        // ví dụ như cấu hình ko thay đổi, ko chuyển từ dọc sang ngang bla bla

        // nếu != null tức là trạng thái của activity đã thay đổi
        // lúc này lấy lại trạng thái của fragment và kiểm tra xem nếu nó đang mở thì phải set lại text cho button là đóng
        // vì sau khi trạng thái thay đổi thì activity đã được khởi tạo lại button lúc fragment đang mở lại thành open
        if(savedInstanceState != null){
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT);
            Log.d("state", "get - "+isFragmentDisplayed.toString());
            if(isFragmentDisplayed){
                mbutton.setText(R.string.close);
            }
        }


        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isFragmentDisplayed){ //false
                    displayFragment();
                    Log.d("state", "display - "+isFragmentDisplayed.toString());
                }
                else{
                    closeFragment();
                    Log.d("state", "close - "+isFragmentDisplayed.toString());
            }}
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

    public void onSaveInstanceState(Bundle saveState) {
        saveState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        Log.d("state", "save - "+isFragmentDisplayed.toString());
        super.onSaveInstanceState(saveState);
    }
}