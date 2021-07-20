package com.paweldyjak.dicegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 5; x++) {
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


    public int checkPair(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[6]) {
            return 0;
        }
        int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;
        int highestPairScore = 0;
        for (int x = 0; x < 5; x++) {
            switch (dices[x]) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;

            }
        }
        if (one >= 2) {
            if (firstThrow) {
                highestPairScore = 4;
            } else {
                highestPairScore = 2;
            }
        }
        if (two >= 2) {
            if (firstThrow) {
                highestPairScore = 8;
            } else {
                highestPairScore = 4;
            }

        }
        if (three >= 2) {
            if (firstThrow) {
                highestPairScore = 12;
            } else {
                highestPairScore = 6;
            }
        }
        if (four >= 2) {
            if (firstThrow) {
                highestPairScore = 16;
            } else {
                highestPairScore = 8;
            }
        }
        if (five >= 2) {
            if (firstThrow) {
                highestPairScore = 20;
            } else {
                highestPairScore = 10;
            }
        }
        if (six >= 2) {
            if (firstThrow) {
                highestPairScore = 24;
            } else {
                highestPairScore = 12;
            }
        }
        return highestPairScore;
    }

    public int checkTwoPairs(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[7]) {
            return 0;
        }
        int one = 0, two = 0, three = 0, four = 0, five = 0, six = 0;
        List<Integer> pairs = new ArrayList<>();
        for (int x = 0; x < 5; x++) {
            switch (dices[x]) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                case 3:
                    three++;
                    break;
                case 4:
                    four++;
                    break;
                case 5:
                    five++;
                    break;
                case 6:
                    six++;
                    break;

            }
        }
        if (one >= 2 && one < 4) {
            if (firstThrow) {
                pairs.add(4);
            } else {
                pairs.add(2);

            }
        } else if (one >= 4) {
            if (firstThrow) {
                pairs.add(4);
                pairs.add(4);
            } else {
                pairs.add(2);
                pairs.add(2);
            }
        }

        if (two >= 2 && two < 4) {
            if (firstThrow) {
                pairs.add(8);
            } else {
                pairs.add(4);

            }
        } else if (two >= 4) {
            if (firstThrow) {
                pairs.add(8);
                pairs.add(8);
            } else {
                pairs.add(4);
                pairs.add(4);
            }
        }

        if (three >= 2 && three < 4) {
            if (firstThrow) {
                pairs.add(12);
            } else {
                pairs.add(6);

            }
        } else if (three >= 4) {
            if (firstThrow) {
                pairs.add(12);
                pairs.add(12);
            } else {
                pairs.add(6);
                pairs.add(6);
            }
        }

        if (four >= 2 && four < 4) {
            if (firstThrow) {
                pairs.add(16);
            } else {
                pairs.add(8);

            }
        } else if (four >= 4) {
            if (firstThrow) {
                pairs.add(16);
                pairs.add(16);
            } else {
                pairs.add(8);
                pairs.add(8);
            }
        }

        if (five >= 2 && five < 4) {
            if (firstThrow) {
                pairs.add(20);
            } else {
                pairs.add(10);

            }
        } else if (five >= 4) {
            if (firstThrow) {
                pairs.add(20);
                pairs.add(20);
            } else {
                pairs.add(10);
                pairs.add(10);
            }
        }

        if (six >= 2 && six < 4) {
            if (firstThrow) {
                pairs.add(24);
            } else {
                pairs.add(12);

            }
        } else if (six >= 4) {
            if (firstThrow) {
                pairs.add(24);
                pairs.add(24);
            } else {
                pairs.add(12);
                pairs.add(12);
            }
        }
        Collections.sort(pairs);
        if (pairs.size() < 2) {
            return 0;
        } else if (pairs.size() == 2) {
            return pairs.get(0) + pairs.get(1);
        } else if (pairs.size() == 4) {
            return pairs.get(1) + pairs.get(2);
        } else {
            return 0;
        }
    }


    public int checkEvens(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[8]) {
            return 0;
        }

        List<Integer> evenNumbers = new ArrayList<>();
        Integer sum = 0;

        for (int dice : dices) {
            if (dice % 2 == 0) {
                if (firstThrow) {
                    evenNumbers.add(dice * 2);
                } else {
                    evenNumbers.add(dice);
                }
            } else {
                break;
            }
        }
        if (evenNumbers.size() == 5) {
            for (Integer values : evenNumbers) {
                sum += values;

            }
            return sum;
        }
        return 0;
    }


    public int checkOdds(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[9]) {
            return 0;
        }

        List<Integer> oddNumbers = new ArrayList<>();
        Integer sum = 0;

        for (int dice : dices) {
            if (dice % 2 != 0) {
                if (firstThrow) {
                    oddNumbers.add(dice * 2);
                } else {
                    oddNumbers.add(dice);
                }
            } else {
                break;
            }
        }
        if (oddNumbers.size() == 5) {
            for (Integer values : oddNumbers) {
                sum += values;

            }
            return sum;
        }
        return 0;
    }


    public int checkSmallStraight(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[10]) {
            return 0;
        }
        List<Integer> smallStraight = new ArrayList<>();
        List<Integer> smallStraightTemplate = new ArrayList<>();
        smallStraightTemplate.add(1);
        smallStraightTemplate.add(2);
        smallStraightTemplate.add(3);
        smallStraightTemplate.add(4);
        smallStraightTemplate.add(5);
        Integer sum = 0;
        for (Integer values : dices) {
            smallStraight.add(values);
        }
        Collections.sort(smallStraight);
        if (smallStraight.equals(smallStraightTemplate)) {
            for (Integer values : smallStraight) {
                sum += values;
            }
            if (firstThrow) {
                return sum * 2;
            } else {
                return sum;
            }
        } else {
            return 0;
        }
    }

    public int checkLargeStraight(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[11]) {
            return 0;
        }
        List<Integer> largeStraight = new ArrayList<>();
        List<Integer> largeStraightTemplate = new ArrayList<>();
        largeStraightTemplate.add(2);
        largeStraightTemplate.add(3);
        largeStraightTemplate.add(4);
        largeStraightTemplate.add(5);
        largeStraightTemplate.add(6);
        Integer sum = 0;
        for (Integer values : dices) {
            largeStraight.add(values);
        }
        Collections.sort(largeStraight);
        if (largeStraight.equals(largeStraightTemplate)) {
            for (Integer values : largeStraight) {
                sum += values;
            }
            if (firstThrow) {
                return sum * 2;
            } else {
                return sum;
            }
        } else {
            return 0;
        }
    }


    public int checkFullHouse(int[] dices, boolean firstThrow) {
        if (!uiConfig.getIsCombinationActive()[12]) {
            return 0;
        }
        List<Integer> valuesList = new ArrayList<>();
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int six = 0;
        valuesList.add(one);
        valuesList.add(two);
        valuesList.add(three);
        valuesList.add(four);
        valuesList.add(five);
        valuesList.add(six);
        for (int x = 0; x < dices.length; x++) {
            switch (dices[x]) {
                case 1:
                    valuesList.set(0, valuesList.get(0) + 1);
                    break;
                case 2:
                    valuesList.set(1, valuesList.get(1) + 1);
                    break;
                case 3:
                    valuesList.set(2, valuesList.get(2) + 1);
                    break;
                case 4:
                    valuesList.set(3, valuesList.get(3) + 1);
                    break;
                case 5:
                    valuesList.set(4, valuesList.get(4) + 1);
                    break;
                case 6:
                    valuesList.set(5, valuesList.get(5) + 1);
                    break;
            }
        }
        int nrOfDifferentValues = 0;
        boolean isMoreThanThreeSameDices = false;
        int fullHouseSum = 0;
        for (int x = 0; x < valuesList.size(); x++) {

            if (valuesList.get(x) != 0) {
                nrOfDifferentValues++;
                fullHouseSum = fullHouseSum+(valuesList.get(x)*(x+1));

            }
            if (valuesList.get(x) > 3) {
                isMoreThanThreeSameDices = true;
            }
        }
        if (nrOfDifferentValues == 2 && !isMoreThanThreeSameDices) {
            if (firstThrow) {
                return fullHouseSum * 2;
            } else {
                return fullHouseSum;
            }

        }

        return 0;

    }
    /*


    public int check4OfAKind(int[] dices, boolean firstThrow) {

    }


    public int check5ofAKind(int[] dices, boolean firstThrow) {

    }

    public int checkSOS(int[] dices){

    }*/

    // method checks if user rolled any combination


}
