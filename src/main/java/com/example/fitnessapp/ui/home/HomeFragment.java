package com.example.fitnessapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fitnessapp.FoodActivity;
import com.example.fitnessapp.R;
import com.example.fitnessapp.WorkoutActivity;

public class HomeFragment extends Fragment {

    private View view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton buttonFood = (ImageButton) view.findViewById(R.id.button_food);

        buttonFood.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), FoodActivity.class);
                startActivity(intent);
            }
        });

        ImageButton buttonWorkout = (ImageButton) view.findViewById(R.id.button_workout);
        buttonWorkout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeFragment.this.getActivity(), WorkoutActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
    }



}