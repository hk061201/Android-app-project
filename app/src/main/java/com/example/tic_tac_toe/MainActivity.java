
package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import  androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0: yellow,1: red
    boolean gameisactive=true;
    int player=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpoition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropin(View view) {

        ImageView counter = (ImageView) view;

        int tappedcounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedcounter] == 2&&gameisactive) {
            gamestate[tappedcounter] = player;
            counter.setTranslationY(-1500);
            if (player == 0) {
                counter.setImageResource(R.drawable.yellow);
                player = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                player = 0;
            }
            counter.animate().translationYBy(1500).setDuration(500);
            for (int[] pos : winningpoition) {
                if (gamestate[pos[0]] == gamestate[pos[1]] && gamestate[pos[1]] == gamestate[pos[2]] && gamestate[pos[0]] != 2) {
                    //Someone won
                    gameisactive=false;
                    String winner;
                    if (gamestate[pos[0]] == 0)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    Button playagain=(Button)findViewById(R.id.button2);
                    TextView winnerText=(TextView)findViewById(R.id.textView);
                    winnerText.setText(winner + " has won!");
                    playagain.setVisibility(View.VISIBLE);
                    winnerText.setVisibility(View.VISIBLE);
                }


            }


        }
        int count=0;
        for(int game:gamestate){
            if(game==1||game==0)
                count++;
        }
        if(count==9&&gameisactive){
            Button playagain=(Button)findViewById(R.id.button2);
            TextView winnerText=(TextView)findViewById(R.id.textView);
            winnerText.setText( " Game Draw !");
            playagain.setVisibility(View.VISIBLE);
            winnerText.setVisibility(View.VISIBLE);
        }


    }
    public void playAgain(View view){
        Button playagain=(Button)findViewById(R.id.button2);
        TextView winnerText=(TextView)findViewById(R.id.textView);
        playagain.setVisibility(View.INVISIBLE);
        winnerText.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid);
        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView counter=(ImageView)gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        Arrays.fill(gamestate, 2);
        player=0;
        gameisactive=true;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}