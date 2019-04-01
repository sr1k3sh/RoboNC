package com.arrtsm.app.woodapp.fragment_shape;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arrtsm.app.woodapp.DrawActivity;
import com.arrtsm.app.woodapp.R;

public class Circle_fragment extends Fragment {
    EditText circle_radius;
    float circle_radius_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_circle, container, false);
        circle_radius = v.findViewById(R.id.cirle_radius);
       if (circle_radius.getText().toString().isEmpty()){
            ((DrawActivity) getActivity()).circle_radius(0);
        }
        circle_radius.addTextChangedListener(new TextChange(circle_radius));
        return v;
    }



    private class TextChange implements TextWatcher {
        View view;
        public TextChange(View v)
        {
            view=v;
        }


        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            switch (view.getId()){
                case R.id.cirle_radius:
                    if (!circle_radius.getText().toString().isEmpty()) {
                        circle_radius_value = Float.parseFloat(circle_radius.getText().toString());

                    } else {
                        circle_radius_value = 0;
                    }
                    ((DrawActivity) getActivity()).circle_radius(circle_radius_value);
                    break;

            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
