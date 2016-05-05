package com.example.ahill.connect3;

import android.util.Log;

/**
 * Holds the current game state.
 *
 * Created by ahill on 5/4/2016.
 */
public class GameState {
    /** whose turn it is */
    private boolean redTurn = true;
    /** the current game state, initially not one */
    private Player[] gameState = {
            Player.NONE, Player.NONE, Player.NONE,
            Player.NONE, Player.NONE, Player.NONE,
            Player.NONE, Player.NONE, Player.NONE
    };
    /** the winner of the game */
    private Player winner = Player.NONE;
    /** what turn we are on */
    private int turn = 1;


    public GameState() {

    }

    /**
     * Resets the game to the initial values
     */
    public void reset() {
        this.redTurn = true;

        // go through all the game states and set them to no player
        for(int i = 0; i < this.gameState.length; i++) {
            this.gameState[i] = Player.NONE;
        }

        this.winner = Player.NONE;
        this.turn = 1;
    }

    /**
     * Gets the {@link Player} at the given index
     *
     * @param index
     * @return
     */
    public Player getState(int index) {
        return this.gameState[index];
    }

    /**
     * Sets the {@link Player} at the given index
     *
     * @param index
     * @param player
     */
    public void setState(int index, Player player) {
        this.gameState[index] = player;
    }

    /**
     * Determine whose turn it is
     *
     * @return
     */
    public boolean getRedTurn() {
        return this.redTurn;
    }

    /**
     * Sets whose turn it is
     *
     * @param redTurn
     */
    public void setRedTurn(boolean redTurn) {
        this.redTurn = redTurn;
    }

    /**
     * Retrieve the winner, if there is one
     *
     * @return
     */
    public Player getWinner() {
        return this.winner;
    }

    /**
     * Sets the winner of the game
     *
     * @param player
     */
    public void setWinner(Player player) {
        this.winner = player;
    }

    /**
     * Increases the turn number
     */
    public void nextTurn() {
        this.turn++;
    }

    /**
     * Retrieve what turn we are currently on
     *
     * @return
     */
    public int getTurn() {
        return this.turn;
    }
}

