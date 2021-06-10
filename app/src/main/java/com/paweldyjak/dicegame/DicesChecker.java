package com.paweldyjak.dicegame;

public class DicesChecker {

    public int checkOnes(int[] dices, boolean firstThrow) {
        int onesNr = 0;
       for(int x = 0; x<6; x++){
           if(dices[x] == 1){
               onesNr++;
           }
       }

       if(onesNr>=3){
           if(firstThrow){
               return 6;
           } else {
               return 3;
           }
       } else{
           return 0;
       }
    }


    /*public int checkTwo(int[] dices) {

    }


    public int checkThree(int[] dices) {

    }


    public int checkFour(int[] dices) {

    }


    public int checkFive(int[] dices) {

    }


    public int CheckSix(int[] dices) {

    }


    public int checkPair(int[] dices) {

    }


    public int checkTwoPairs(int[] dices) {

    }


    public int checkEvens(int[] dices) {

    }


    public int checkOdds(int[] dices) {

    }


    public int checkSmallStraight(int[] dices) {

    }


    public int checkLargeStraight(int[] dices) {

    }


    public int checkFullHouse(int[] dices) {

    }


    public int check4OfAKind(int[] dices) {

    }


    public int check5ofAKind(int[] dices) {

    }

    public int checkSOS(int[] dices){

    }*/
}
