package com.paweldyjak.dicegame.layouts;

import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.paweldyjak.dicegame.MainActivity;
import com.paweldyjak.dicegame.R;

public class TitleScreen extends AppCompatActivity {
    MainActivity mainActivity;
    PlayerNamesInputScreen playerNamesInputScreen;

    public TitleScreen(MainActivity mainActivity, PlayerNamesInputScreen playerNamesInputScreen){
        this.mainActivity = mainActivity;
        this.playerNamesInputScreen = playerNamesInputScreen;
        setTwoPlayerButton();
    }


    public void setTwoPlayerButton(){

        Button twoPlayerButton = mainActivity.findViewById(R.id.twoPlayersButton);
        twoPlayerButton.setOnClickListener(v-> playerNamesInputScreen.playerInputScreen(mainActivity));



    }
}
