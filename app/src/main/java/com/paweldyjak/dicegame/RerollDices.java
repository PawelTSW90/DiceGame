package com.paweldyjak.dicegame;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

public class RerollDices {
    private final Context context;
    private final UIConfig uiConfig;

    public RerollDices(Context context, UIConfig uiConfig) {
        this.context = context;
        this.uiConfig = uiConfig;


    }


    public void setDicesRerolling(int throwNumber) {

        for (int x = 0; x < 5; x++) {
            uiConfig.clearDicesBorder();
            uiConfig.getDicesSlots()[x].setOnClickListener(v -> {
                if(throwNumber!=3) {
                    if (v.getBackground() != null) {
                        v.setBackground(null);
                    } else {
                        uiConfig.setDicesBorder(((ImageView)v), true);
                    }
                }


            });
        }

    }

    public boolean[] getSelectedDices() {
        boolean[] selectedDices = new boolean[5];
        for (int x = 0; x < 5; x++) {
            if (uiConfig.getDicesSlots()[x].getBackground() != null) {
                selectedDices[x] = true;

            }
        }

        return selectedDices;
    }
}
