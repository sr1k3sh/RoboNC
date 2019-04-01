package com.arrtsm.app.woodapp.fragment_shape;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arrtsm.app.woodapp.DrawActivity;
import com.arrtsm.app.woodapp.R;


public class Oval_fragment extends Fragment {
    EditText oval_length, oval_breadth;
    float oval_length_value;
    float oval_breadth_value;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_oval, container, false);
        oval_length = v.findViewById(R.id.oval_length);
        oval_breadth = v.findViewById(R.id.oval_breadth);

        if (oval_length.getText().toString().isEmpty()) {
            ((DrawActivity) getActivity()).oval_length(0);
        }
        if (oval_breadth.getText().toString().isEmpty()){
            ((DrawActivity) getActivity()).oval_breadth(0);
        }

        oval_length.addTextChangedListener(new TextChange(oval_length));
        oval_breadth.addTextChangedListener(new TextChange(oval_breadth));
        Log.d("oval","dshfdh");
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
            switch (view.getId()) {
                case R.id.oval_length:
                    if (!oval_length.getText().toString().isEmpty()) {
                        oval_length_value = Float.parseFloat(oval_length.getText().toString());

                    } else {
                        oval_length_value = 0;
                    }
                    ((DrawActivity) getActivity()).oval_length(oval_length_value);
                    break;

                case R.id.oval_breadth:
                    if (!oval_breadth.getText().toString().isEmpty()) {
                        oval_breadth_value = Float.parseFloat(oval_breadth.getText().toString());
                    } else {
                        oval_breadth_value = 0;
                    }
                    ((DrawActivity) getActivity()).oval_breadth(oval_breadth_value);
                    break;
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
