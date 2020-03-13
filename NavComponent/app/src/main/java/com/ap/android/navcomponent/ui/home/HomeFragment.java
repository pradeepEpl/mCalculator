package com.ap.android.navcomponent.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ap.android.navcomponent.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.atomic.AtomicBoolean;

import akndmr.github.io.colorprefutil.ColorPrefUtil;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button button;
    SharedPreferences mSharedPreferences;
    int selectedBackgroundColorId;
    ConstraintLayout mConstraintLayout;
    String url = "https://images.immdemo.net/coupon/235/FWC_38824.jpeg";

    ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        mSharedPreferences = getActivity().getSharedPreferences("Theme", MODE_PRIVATE);
        mConstraintLayout = root.findViewById(R.id.layout);
        button = root.findViewById(R.id.button);
        imageView = root.findViewById(R.id.imageView);
        //button.setBackground(getActivity().getDrawable(R.drawable.button_selector));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //selectedBackgroundColorId = mSharedPreferences.getInt("COLOR_SELECTED", R.color.colorPrimary);

                // Single view
                //ColorPrefUtil.changeBackgroundColorOfSingleView(getActivity(), button, selectedBackgroundColorId);

                //All views inside parent layout
                //ColorPrefUtil.changeBackgroundColorOfChildViews(getActivity(), mConstraintLayout, selectedBackgroundColorId);

                //ColorPrefUtil.changeColorOfItemsOfNavView(mNavigationView, iconColorId, textColorId);
                final AtomicBoolean playAnimation = new AtomicBoolean(true);
                Picasso.get().load(url).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        if (playAnimation.get()) {
                            //play fade
                            Animation fadeOut = new AlphaAnimation(0, 1);
                            fadeOut.setInterpolator(new AccelerateInterpolator());
                            fadeOut.setDuration(1000);
                            imageView.startAnimation(fadeOut);

                            Animation fadeOutPlaceholder = new AlphaAnimation(1, 0);
                            fadeOutPlaceholder.setInterpolator(new AccelerateInterpolator());
                            fadeOutPlaceholder.setDuration(1000);
                            imageView.startAnimation(fadeOutPlaceholder);
                        }
                        //imageView.setAlpha(0f);
                        //Picasso.get().load(url).into(imageView);
                        //imageView.animate().setDuration(300).alpha(1f).start();
                    }

                    @Override
                    public void onError(Exception e) {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                        Log.d("TAG", e.getMessage(), e);

                    }


                });
                playAnimation.set(false);


            }
        });
        return root;
    }
}