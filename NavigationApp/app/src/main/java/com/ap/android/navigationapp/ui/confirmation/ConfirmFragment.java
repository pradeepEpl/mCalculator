package com.ap.android.navigationapp.ui.confirmation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ap.android.navigationapp.R;

public class ConfirmFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm, container, false);
        TextView textView = view.findViewById(R.id.rupee);
        String s = ConfirmFragmentArgs.fromBundle(getArguments()).getRupee();
        textView.setText("Rs " + s + " Send Successfully");//Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        return view;
    }
}
