package com.ap.android.navigationapp.ui.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.ap.android.navigationapp.R;
import com.ap.android.navigationapp.Transaction;

public class TransactionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        Transaction transaction = TransactionFragmentArgs.fromBundle(getArguments()).getTrans();
        TextView textView = view.findViewById(R.id.trans_txt);
        textView.setText("Date : " + transaction.date + "\n" + "Credit : " + transaction.amount);
        return view;
    }
}
