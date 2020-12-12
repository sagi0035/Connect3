package com.example.seana.connectthreeotherfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // soo first we will create general variables to rep an active player, gamestate, winning state, and the number of plays and if the game is active
    boolean gameActive = true;
    int currentPlayer = 0;
    int [][] winningStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int gamePlay = 0;


    public void clicker(View view) {
        // so first we will mark that a play has been made to count to a possible draw!
        gamePlay+=1;
        // so here we are giving a variable to what you clicked
        ImageView a = (ImageView) view;
        int theTag = Integer.parseInt(a.getTag().toString());
        Log.i("Tag", Integer.toString(theTag));

        // so we will only set it to play if the game is active i.e. that there is no winner and not a draw
        // and we will only allow a play if the box was not already clicked
        if (gameActive && gameState[theTag] == 2) {
            // so now we will convert the gamestate in line with the index of what we clicked
            gameState[theTag] = currentPlayer;

            //so here we will put the image up high so it drops down (in the process getting it to of couse show
            a.setTranslationY(-900);
            
            String winner = "";
            if (currentPlayer == 0) {
                a.setImageResource(R.drawable.red);
                a.animate().translationYBy(900).setDuration(700);
                winner = "Red";
                currentPlayer = 1;
            } else if (currentPlayer == 1) {
                a.setImageResource(R.drawable.yellow);
                a.animate().translationYBy(900).setDuration(700);
                winner = "Yellow";
                currentPlayer = 0;
            }

            // now we create a for loop for determining a possible winner
            for (int[] gameStater : winningStates) {
                if (gameState[gameStater[0]] == gameState[gameStater[1]] && gameState[gameStater[1]] == gameState[gameStater[2]] && gameState[gameStater[1]] != 2) {
                    // so now we will try to get the text view on the bottom to reflect the winner
                    TextView c = (TextView) findViewById(R.id.textView2);
                    // and now we will change what the text says according to who the winner is and then make the text show
                    c.setText(winner + " is the winner");
                    c.setVisibility(View.VISIBLE);
                    gameActive = false;
                } else if (gamePlay == 9) {
                    // else we have exhausted all options and the result is actually a draw
                    TextView c = (TextView) findViewById(R.id.textView2);
                    c.setText("It's a draw!");
                    c.setVisibility(View.VISIBLE);
                    gameActive = false;
                }
            }
        }

    }

    public void otherClicker(View view)  {
        // so here we will reset everything
        gameActive = true;
        currentPlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        gamePlay = 0;

        // note that we do not need to reset the winnig states as those are never changing

        // and then we need to re get rid of all the text and the images
        TextView c = (TextView) findViewById(R.id.textView2);
        c.setVisibility(View.INVISIBLE);


        // so to remove all the images the easiest manner to do so is by looping through
        GridLayout g = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < g.getChildCount(); i++) {
            ImageView d = (ImageView) g.getChildAt(i);
            d.setImageDrawable(null);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
