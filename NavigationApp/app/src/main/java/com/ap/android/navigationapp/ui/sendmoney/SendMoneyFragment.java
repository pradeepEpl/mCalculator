package com.ap.android.navigationapp.ui.sendmoney;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.ap.android.navigationapp.R;

public class SendMoneyFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_send_money, container, false);
        final EditText editText = view.findViewById(R.id.amount);
        Button button = view.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amount = editText.getText().toString();
                NavDirections navDirections = SendMoneyFragmentDirections.actionSendMoneyFragmentToConfirmFragment(amount);
                Navigation.findNavController(v).navigate(navDirections);
            }
        });
        return view;
    }
}
