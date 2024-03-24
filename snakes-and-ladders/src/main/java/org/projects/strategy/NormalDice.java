package org.projects.strategy;

import java.util.Random;

public class NormalDice implements Dice {
    int numDice;
    String movementStrategy;

    public NormalDice(int numDices, String movementStrategy) {
        numDice = numDices;
        this.movementStrategy = movementStrategy;
    }
    @Override
    public int rollDice() {
        int sum = 0;
        for (int i = 0; i < numDice; i++) {
            int roll = (int) (Math.random() * 6) + 1;
            switch (movementStrategy) {
                case "SUM":
                    sum += roll;
                    break;
                case "MAX":
                    sum = Math.max(sum, roll);
                    break;
                case "MIN":
                    sum = (sum == 0) ? roll : Math.min(sum, roll);
                    break;
            }
        }
        return sum;
    }
}
