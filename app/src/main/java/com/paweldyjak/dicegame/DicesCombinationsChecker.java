package com.paweldyjak.dicegame;

import com.paweldyjak.dicegame.GameModes.GameMode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// class methods calculates user score and send it to ScoreInput class
public class DicesCombinationsChecker {
    private final GameMode gameMode;
    private final UIConfig uiConfig;

    public DicesCombinationsChecker(GameMode gameMode, UIConfig uiConfig) {
        this.gameMode = gameMode;
        this.uiConfig = uiConfig;
    }

    public int checkOne(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][0] != 0) {
            return 0;
        }
        int nrOfOne = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 1) {
                nrOfOne++;
            }
        }

        if (nrOfOne >= 3) {
            uiConfig.combinationHighlighter(1, false);
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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][1] != 0) {
            return 0;
        }
        int nrOfTwo = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 2) {
                nrOfTwo++;
            }
        }

        if (nrOfTwo >= 3) {
            uiConfig.combinationHighlighter(2, false);
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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][2] != 0) {
            return 0;
        }
        int nrOfThree = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 3) {
                nrOfThree++;
            }
        }

        if (nrOfThree >= 3) {
            uiConfig.combinationHighlighter(3, false);
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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][3] != 0) {
            return 0;
        }
        int nrOfFour = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 4) {
                nrOfFour++;
            }
        }

        if (nrOfFour >= 3) {
            uiConfig.combinationHighlighter(4, false);
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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][4] != 0) {
            return 0;
        }
        int nrOfFive = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 5) {
                nrOfFive++;
            }
        }

        if (nrOfFive >= 3) {
            uiConfig.combinationHighlighter(5, false);
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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][5] != 0) {
            return 0;
        }
        int nrOfSix = 0;
        for (int x = 0; x < 5; x++) {
            if (dices[x] == 6) {
                nrOfSix++;
            }
        }

        if (nrOfSix >= 3) {
            uiConfig.combinationHighlighter(6, false);
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
        int firstThrowValue = 4;
        int notFirstThrowValue = 2;
        int highestPairScore = 0;
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][6] != 0) {
            return 0;
        }
        List<Integer> valuesList = new ArrayList<>();
        for (int x = 0; x < 6; x++) {
            valuesList.add(x, 0);
        }
        for (int x = 0; x < 5; x++) {
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
        for (int x = 0; x < 6; x++) {

            if (valuesList.get(x) >= 2) {
                if (firstThrow) {
                    highestPairScore = firstThrowValue;
                } else {
                    highestPairScore = notFirstThrowValue;
                }
            }
            firstThrowValue += 4;
            notFirstThrowValue += 2;
        }
        if (highestPairScore > 0) {
            uiConfig.combinationHighlighter(7, false);

        }
        return highestPairScore;
    }

    public int checkTwoPairs(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][7] != 0) {
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
            uiConfig.combinationHighlighter(8, false);
            return pairs.get(0) + pairs.get(1);
        } else {
            uiConfig.combinationHighlighter(8, false);
            return pairs.get(1) + pairs.get(2);
        }
    }


    public int checkEvens(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][8] != 0) {
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
            uiConfig.combinationHighlighter(9, false);

            for (Integer values : evenNumbers) {
                sum += values;

            }
            return sum;
        }
        return 0;
    }


    public int checkOdds(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][9] != 0) {
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
            uiConfig.combinationHighlighter(10, false);

            for (Integer values : oddNumbers) {
                sum += values;

            }
            return sum;
        }
        return 0;
    }


    public int checkSmallStraight(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][10] != 0) {
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
            uiConfig.combinationHighlighter(11, false);

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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][11] != 0) {
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
            uiConfig.combinationHighlighter(12, false);

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
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][12] != 0) {
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
        for (int dice : dices) {
            switch (dice) {
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
                fullHouseSum = fullHouseSum + (valuesList.get(x) * (x + 1));

            }
            if (valuesList.get(x) > 3) {
                isMoreThanThreeSameDices = true;
            }
        }
        if (nrOfDifferentValues == 2 && !isMoreThanThreeSameDices) {
            uiConfig.combinationHighlighter(13, false);

            if (firstThrow) {
                return fullHouseSum * 2;
            } else {
                return fullHouseSum;
            }

        }

        return 0;

    }


    public int checkFourOfAKind(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][13] != 0) {
            return 0;
        }
        int[] values = new int[6];
        boolean checkCombination = false;
        int score = 0;
        for (int dice : dices) {
            switch (dice) {
                case 1:
                    values[0] = values[0] + 1;
                    break;
                case 2:
                    values[1] = values[1] + 1;
                    break;
                case 3:
                    values[2] = values[2] + 1;
                    break;
                case 4:
                    values[3] = values[3] + 1;
                    break;
                case 5:
                    values[4] = values[4] + 1;
                    break;
                case 6:
                    values[5] = values[5] + 1;
                    break;
            }
        }

        for (int x = 0; x < values.length; x++) {
            if (values[x] >= 4) {
                checkCombination = true;
                score = values[x] * (x + 1);
                if (values[x] == 5) {
                    score -= values[x];
                }
            }
        }

        if (checkCombination) {
            uiConfig.combinationHighlighter(14, false);

            if (firstThrow) {
                return score * 2;
            } else {
                return score;
            }

        }
        return 0;
    }


    public int checkFiveOfAKind(int[] dices, boolean firstThrow) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][14] != 0) {
            return 0;
        }
        int[] values = new int[6];
        boolean checkCombination = false;
        int score = 0;
        for (int dice : dices) {
            switch (dice) {
                case 1:
                    values[0] = values[0] + 1;
                    break;
                case 2:
                    values[1] = values[1] + 1;
                    break;
                case 3:
                    values[2] = values[2] + 1;
                    break;
                case 4:
                    values[3] = values[3] + 1;
                    break;
                case 5:
                    values[4] = values[4] + 1;
                    break;
                case 6:
                    values[5] = values[5] + 1;
                    break;
            }
        }

        for (int x = 0; x < values.length; x++) {
            if (values[x] == 5) {
                checkCombination = true;
                score = values[x] * (x + 1);
            }
        }

        if (checkCombination) {
            uiConfig.combinationHighlighter(15, false);

            if (firstThrow) {
                return score * 2;
            } else {
                return score;


            }

        }
        return 0;

    }


    public int checkSOS(int[] dices, int throwNumber) {
        if (gameMode.getCombinationsSlotsValues()[gameMode.getCurrentPlayerNumber() - 1][15] != 0) {
            return 0;
        }
        int sosSum = 0;
        if (throwNumber == 3) {
            uiConfig.combinationHighlighter(16, false);

            for (int sum : dices) {
                sosSum += sum;
            }
            return sosSum;
        }
        return 0;


    }

    //method returns chosen combination check
    public int combinationChecker(int combinationNr, int[] dices, boolean firstThrow, int throwNumber) {
        switch (combinationNr) {
            case 0:
                return checkOne(dices, firstThrow);

            case 1:
                return checkTwo(dices, firstThrow);

            case 2:
                return checkThree(dices, firstThrow);

            case 3:
                return checkFour(dices, firstThrow);

            case 4:
                return checkFive(dices, firstThrow);

            case 5:
                return checkSix(dices, firstThrow);

            case 6:
                return checkPair(dices, firstThrow);

            case 7:
                return checkTwoPairs(dices, firstThrow);

            case 8:
                return checkEvens(dices, firstThrow);

            case 9:
                return checkOdds(dices, firstThrow);

            case 10:
                return checkSmallStraight(dices, firstThrow);

            case 11:
                return checkLargeStraight(dices, firstThrow);

            case 12:
                return checkFullHouse(dices, firstThrow);

            case 13:
                return checkFourOfAKind(dices, firstThrow);

            case 14:
                return checkFiveOfAKind(dices, firstThrow);

            case 15:
                return checkSOS(dices, throwNumber);

        }
        return 0;
    }
}
