package com.shrobon.connect_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int activePlayer =0; // 0 = yello , and 1 is red
    int gameState[] = {2,2,2,2,2,2,2,2,2}; // 2 means unplayed
    int [][] winningPositions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int end_game= 0;
    public void dropin(View v)
    {
        //to find which button was tapped  on
        ImageView counter= (ImageView)v;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());


        if(gameState[tappedCounter] == 2 && end_game == 0)
        {
            gameState[tappedCounter] = activePlayer;
            counter.setTranslationY(-1000f);
            if(activePlayer ==0)
            {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(3600).setDuration(400);
            for(int [] winningPosition: winningPositions)
            {
                if(gameState[winningPosition[0]]== gameState[winningPosition[1]] && gameState[winningPosition[1]]==gameState[winningPosition[2]]  && gameState[winningPosition[0]] !=2 )
                {
                    String winner="";
                    end_game = 1;
                    if(gameState[winningPosition[0]]==0)
                    {
                        winner = "Yellow has won !!";
                    }
                    else
                    {
                        winner = "Red has won !!";
                    }
                    Log.i("Winning",winner);
                    Toast.makeText(MainActivity.this,winner,Toast.LENGTH_LONG).show();

                    //Make the play again button appear again
                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                    TextView winner_message = (TextView)findViewById(R.id.wintext);
                    winner_message.setText(winner);
                    layout.setVisibility(View.VISIBLE); //make the restart button appear out of nowhere
                }
                else
                {
                    boolean game_over= true;
                    //Check if the game is a draw
                    for(int i :gameState)
                    {
                        if(i == 2)
                        {
                            game_over = false;
                        }
                    }
                    if(game_over == true)
                    {
                        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
                        TextView winner_message = (TextView)findViewById(R.id.wintext);
                        winner_message.setText("Its a DRAW !!");
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }

    }

    public void playAgain(View v)
    {
        end_game = 0;
        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        for(int i=0;i<9;i++)
        {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            //getChildLayout finds out the number of views inside the grid layout
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0); // 0 means an empty image
        }

    }
}
