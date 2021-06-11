package com.paweldyjak.dicegame;

public class EraseCombinations {
    DicesChecker dicesChecker;
    UIConfig uiConfig;

    EraseCombinations(DicesChecker dicesChecker, UIConfig uiConfig) {
        this.dicesChecker = dicesChecker;
        this.uiConfig = uiConfig;
    }


    public void combinationEraser() {

        for (int x = 0; x < dicesChecker.getIsCombinationDone().length; x++) {
            if (!dicesChecker.getIsCombinationDone()[x]) {
                int finalX = x;
                uiConfig.getCombinations()[x].setOnClickListener(v -> uiConfig.getCombinations()[finalX].setEnabled(false));
            }
        }


    }


}
