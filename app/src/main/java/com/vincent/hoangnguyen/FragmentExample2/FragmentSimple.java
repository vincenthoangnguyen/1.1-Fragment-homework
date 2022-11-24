package com.vincent.hoangnguyen.FragmentExample2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSimple extends Fragment {
    private final static int YES = 0;
    private final static int NO = 1;
    public FragmentSimple(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_simple,container,false);
        RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                TextView textView = rootView.findViewById(R.id.textHeader);
            View radioButton = radioGroup.findViewById(checkedId); // find radioButton using checkId
                int index = radioGroup.indexOfChild(radioButton);
                switch (index){
                    case YES:
                      textView.setText(R.string.yes_message);
                      break;
                    case NO:
                        textView.setText(R.string.no_message);
                        break;
                    default:
                        break;

                }
            }
        });
        return rootView;
    }
    public static FragmentSimple newInstance(){
        return new FragmentSimple();
    }
}
