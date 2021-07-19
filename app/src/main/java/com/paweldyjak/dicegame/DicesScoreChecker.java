package com.paweldyjak.dicegame;

// class methods calculates user score and send it to ScoreInput class
public class DicesScoreChecker {
    UIConfig uiConfig;

    public DicesScoreChecker(UIConfig uiConfig) {
        this.uiConfig = uiConfig;
    }


    public int checkOne(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[0]) {
            return 0;
        }
        int nrOfOne = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 1) {
                nrOfOne++;
            }
        }

        if (nrOfOne >= 3) {
            if (firstThrow) {
                return 6;
            } else {
                return 3;
            }
        } else {
            return 0;
        }

    }


    public int checkTwo(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[1]) {
            return 0;
        }
        int nrOfTwo = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 2) {
                nrOfTwo++;
            }
        }

        if (nrOfTwo >= 3) {
            if (firstThrow) {
                return 12;
            } else {
                return 6;
            }
        } else {
            return 0;
        }
    }


    public int checkThree(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[2]) {
            return 0;
        }
        int nrOfThree = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 3) {
                nrOfThree++;
            }
        }

        if (nrOfThree >= 3) {
            if (firstThrow) {
                return 18;
            } else {
                return 9;
            }
        } else {
            return 0;
        }
    }


    public int checkFour(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[3]) {
            return 0;
        }
        int nrOfFour = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 4) {
                nrOfFour++;
            }
        }

        if (nrOfFour >= 3) {
            if (firstThrow) {
                return 24;
            } else {
                return 12;
            }
        } else {
            return 0;
        }
    }


    public int checkFive(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[4]) {
            return 0;
        }
        int nrOfFive = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 5) {
                nrOfFive++;
            }
        }

        if (nrOfFive >= 3) {
            if (firstThrow) {
                return 30;
            } else {
                return 15;
            }
        } else {
            return 0;
        }
    }


    public int checkSix(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[5]) {
            return 0;
        }
        int nrOfSix = 0;
        for (int x = 0; x < 6; x++) {
            if (dices[x] == 6) {
                nrOfSix++;
            }
        }

        if (nrOfSix >= 3) {
            if (firstThrow) {
                return 36;
            } else {
                return 18;
            }
        } else {
            return 0;
        }
    }

    // method checks if user rolled any combination
    public boolean checkForAvailableCombination(int[] dices, boolean isFirstThrow) {
        return checkOne(dices, isFirstThrow) != 0 || checkTwo(dices, isFirstThrow) != 0 || checkThree(dices, isFirstThrow) != 0
                || checkFour(dices, isFirstThrow) != 0 || checkFive(dices, isFirstThrow) != 0 || checkSix(dices, isFirstThrow) != 0;
    }


    /*public int checkPair(int[] dices, boolean firstThrow) {

    }


    public int checkTwoPairs(int[] dices, boolean firstThrow) {

    }


    public int checkEvens(int[] dices, boolean firstThrow) {

    }


    public int checkOdds(int[] dices, boolean firstThrow) {

    }


    public int checkSmallStraight(int[] dices, boolean firstThrow) {

    }


    public int checkLargeStraight(int[] dices, boolean firstThrow) {

    }


    public int checkFullHouse(int[] dices, boolean firstThrow) {

    }


    public int check4OfAKind(int[] dices, boolean firstThrow) {

    }


    public int check5ofAKind(int[] dices, boolean firstThrow) {

    }

    public int checkSOS(int[] dices){

    }*/


}
