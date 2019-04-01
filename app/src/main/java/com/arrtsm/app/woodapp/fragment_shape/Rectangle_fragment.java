package com.arrtsm.app.woodapp.fragment_shape;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arrtsm.app.woodapp.DrawActivity;
import com.arrtsm.app.woodapp.R;
import com.arrtsm.app.woodapp.WoodCanvas;

public class Rectangle_fragment extends Fragment {
    EditText rect_length, rect_breadth;
    float rect_length_value;
    float rect_breath_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rectangle, container, false);
        rect_length = v.findViewById(R.id.rect_length);
        rect_breadth = v.findViewById(R.id.rect_breadth);
        if (rect_length.getText().toString().isEmpty()){
            ((DrawActivity) getActivity()).rectangle_length(0);
        }
        if (rect_breadth.getText().toString().isEmpty()){
            ((DrawActivity) getActivity()).rectangle_breadth(0);
        }
        rect_length.addTextChangedListener(new TextChange(rect_length));
        rect_breadth.addTextChangedListener(new TextChange(rect_breadth));
        return v;
    }


    private class TextChange implements TextWatcher {

        View view;
        public TextChange(View v) {
            view = v;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            switch (view.getId()){
                case R.id.rect_length:
                    if (!rect_length.getText().toString().isEmpty()) {
                        rect_length_value = Float.parseFloat(rect_length.getText().toString());
                    } else {
                        rect_length_value = 0;
                    }
                    ((DrawActivity) getActivity()).rectangle_length(rect_length_value);
                    break;
                case R.id.rect_breadth:
                    if (!rect_breadth.getText().toString().isEmpty()) {
                        rect_breath_value = Float.valueOf(rect_breadth.getText().toString());
                    } else {
                        rect_breath_value = 0;
                    }
                    ((DrawActivity) getActivity()).rectangle_breadth(rect_breath_value);
                    break;
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}