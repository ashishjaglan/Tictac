package com.example.dell.tic_tac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 0 = Zero   1 = cross
    int activePlayer =0;

    boolean gameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn (View view){
        ImageView counter = (ImageView) view;

        //Toast.makeText(MainActivity.this, counter.getTag().toString(), Toast.LENGTH_LONG).show();

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2 && gameActive) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.o);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.x);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).setDuration(50);
        }

        for (int[] pos : winningPositions) {
            if (gameState[pos[0]] == gameState[pos[1]] && gameState[pos[2]] == gameState[pos[1]] && gameState[pos[0]] != 2) {

                //Somebody has won !!

                gameActive = false;

                String win = "";

                if (gameState[pos[0]] == 0)
                   win="Xero has won !!";
                else if(gameState[pos[0]] == 1)
                    win="Cross has won !!";


                TextView winnerMessage = (TextView)findViewById(R.id.winner);

                winnerMessage.setText(win );

                LinearLayout lay = (LinearLayout) findViewById(R.id.playAgainLayout);

                lay.setVisibility(View.VISIBLE);

            } else{
                boolean gameIsOver =true;

                for(int counterState : gameState){
                    if(counterState==2) gameIsOver = false;
                }
                if(gameIsOver){
                    TextView winnerMessage = (TextView)findViewById(R.id.winner);

                    winnerMessage.setText("Draw!!");

                    LinearLayout lay = (LinearLayout) findViewById(R.id.playAgainLayout);

                    lay.setVisibility(View.VISIBLE);
                }
            }

        }

    }


    public void playAgain(View view){


        LinearLayout lay = (LinearLayout) findViewById(R.id.playAgainLayout);

        lay.setVisibility(View.INVISIBLE);

        activePlayer =0;

        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;

        android.support.v7.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
