package com.paweldyjak.dicegame.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.paweldyjak.dicegame.Activities.GameBoardActivity;
import com.paweldyjak.dicegame.R;

public class CombinationsChartFragment extends Fragment {
    GameBoardActivity gameBoardActivity;
    boolean isTrainingMode;
    Button returnButton;

    public CombinationsChartFragment(GameBoardActivity gameBoardActivity, boolean isTrainingMode){
        this.gameBoardActivity = gameBoardActivity;
        this.isTrainingMode = isTrainingMode;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_combinations_chart, container, false);
        returnButton = view.findViewById(R.id.combinations_chart_returnButton);
        returnButton.setOnClickListener(v ->{
            if(isTrainingMode){
                gameBoardActivity.hideFragment();
            }


        });
        return view;
    }
}
