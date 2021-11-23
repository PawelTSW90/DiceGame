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
    private final GameBoardActivity gameBoardActivity;

    public CombinationsChartFragment(GameBoardActivity gameBoardActivity){
        this.gameBoardActivity = gameBoardActivity;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_combinations_chart, container, false);
        Button returnButton = view.findViewById(R.id.combinations_chart_returnButton);
        returnButton.setOnClickListener(v ->{
                gameBoardActivity.manageFragments(false, true, this);
        });
        return view;
    }
}
