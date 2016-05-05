package com.example.ahill.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ConnectThreeActivity extends AppCompatActivity {

    /** holds the current game state */
    private GameState gameState = new GameState();

    /**
     * Drops in a pip from off the screen, then performs a check to see if anyone has won
     * @param view the {@link View} that called the method
     */
    public void dropIn(View view) {
        Log.i("Info", "entering drop in");
        // cast our view to an ImageView
        ImageView counter = (ImageView) view;

        // determine which counter was tapped
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // if the counter is already set to a Player, then return from this method
        if(gameState.getState(tappedCounter) != Player.NONE) {
            return;
        }

        // move the counter off the screen
        counter.setTranslationY(-1000f);

        // check to see whose turn it is
        if(gameState.getRedTurn()) {
            // hand the turn for the red player
            handleTurn(R.drawable.red, counter, Player.RED);
        } else {
            // handle the turn for the yellow player
            handleTurn(R.drawable.yellow, counter, Player.YELLOW);
        }

        // finally, drop in the correct pip and ensure it is not clickable
        counter.animate().translationYBy(1000f).setDuration(300);
        counter.setClickable(false);
        Log.i("Info", "exiting drop in");
    }

    /**
     * Resets the game to a new one
     * @param view
     */
    public void resetGame(View view) {
        // grab the board
        ViewGroup board = (ViewGroup) findViewById(R.id.board);

        // ensure all the children are now clickable
        setChildrenClickable(board, true);

        // go through all the children and hide the pips
        for(int i = 0; i < board.getChildCount(); i++) {
            ((ImageView)board.getChildAt(i)).setImageResource(android.R.color.transparent);
        }

        // reset the game state
        this.gameState.reset();

        // hide the winner message
        ((LinearLayout)findViewById(R.id.playAgainLayout)).setVisibility(View.GONE);
    }

    /**
     * Displays a winner/draw message to the user.
     *
     * @param message the message to be displayed
     */
    private void displayWinner(String message) {
        // create the toast and show the message to the user
        ((TextView)findViewById(R.id.winnerMessage)).setText(message);

        // get the message layout for the winner
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.playAgainLayout);

        // ensure it is visible
        linearLayout.setVisibility(View.VISIBLE);

        // send it to the left of the screen
        linearLayout.setTranslationX(-1000f);

        // have it slowly move into the screen
        linearLayout.animate().translationXBy(1000f).setDuration(1000);
    }

    /**
     * Sets all the clickable of children of the {@link ViewGroup}
     * @param viewGroup the {@link ViewGroup} to go through and set the children
     * @param clickable whether or not the children should be clickable
     */
    private void setChildrenClickable(ViewGroup viewGroup, boolean clickable) {
        Log.i("Info", "entering set children clickable");
        // go through the child count and set each to clickable
        for(int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setClickable(clickable);
        }
        Log.i("Info", "exiting set children clickable");
    }

    /**
     * Handles a turn based on the provided parameters.
     *
     * @param drawable the image the {@link ImageView} is to be set to
     * @param counter the {@link ImageView} to be processed
     * @param player the current {@link Player} whose turn it is
     */
    private void handleTurn(int drawable, ImageView counter, Player player) {
        Log.i("Info", "entering handle turn");
        // determine which counter was tapped
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // set the counter's image, then set the game state appropriately
        counter.setImageResource(drawable);
        gameState.setState(tappedCounter, player);

        // check to see if the current player is RED, if so, then set the next player's turn to false
        gameState.setRedTurn(!(player == Player.RED));

        // increase the next turn
        this.gameState.nextTurn();

        // see if there is a winner in the current game state
        if(GameUtilities.findWinner(this.gameState)) {
            // get who the winner is and display it to the user
            displayWinner("The Winner is " + this.gameState.getWinner().toString());

            // ensure that all children are no longer clickable, the user cannot continue to play
            setChildrenClickable((ViewGroup)counter.getParent(), false);

        // if the game turn is 10 and no winner has been found, then we have a draw
        } else if(this.gameState.getTurn() == 10 && this.gameState.getWinner() == Player.NONE) {
            displayWinner("The game is a draw!");

            // ensure that all children are no longer clickable, the user cannot continue to play
            setChildrenClickable((ViewGroup)counter.getParent(), false);
        }
        Log.i("Info", "exiting handle turn");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_three);
    }
}
