package org.projects.models;


import org.projects.exception.InvalidPositionException;

public class DefaultMove extends Move {

    public DefaultMove() {
        super(0);
    }
    @Override
    public boolean isValidPosition(int cellPosition) throws InvalidPositionException {
        return false;
    }
}
