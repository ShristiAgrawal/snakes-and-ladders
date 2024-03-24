package strategy;


import org.projects.strategy.Dice;

public class MockDice implements Dice {
    @Override
    public int rollDice() {
        return 25;
    }
}
