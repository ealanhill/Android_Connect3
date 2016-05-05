package com.example.ahill.connect3;

/**
 * Created by ahill on 5/4/2016.
 */
public class GameUtilities {
    /** all possible winning combinations */
    private static int[][] winningCombinations = {
            // the horizontal winning combinations
            {0,1,2}, {3,4,5}, {6,7,8},
            // the vertical winning combinations
            {0,3,6}, {1,4,7}, {2,5,8},
            // the diagonal winning combinations
            {0,4,8}, {2,4,6}
    };

    /**
     * Determines if there is a winner in the game.
     *
     * @param game the {@link GameState} to search
     * @return
     */
    public static boolean findWinner(GameState game) {
        // go through all the winning combinations in the array
        for(int[] combination : winningCombinations) {

            // ensure that we are not starting with nothing set in the game state
            // then check to see if all the other parts of the game state are equal to each other
            // in the combination we are checking
            if(game.getState(combination[0]) != Player.NONE &&
                    game.getState(combination[0]) == game.getState(combination[1]) &&
                    game.getState(combination[0]) == game.getState(combination[2]))
            {
                // set the winner of the game and return true
                game.setWinner(game.getState(combination[0]));
                return true;
            }
        }

        // if we couldn't find anything, then there is no winner
        return false;
    }

}
