package com.amateurdeveloper.akaashdev.scarnedice;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    static int score = 0, cscore = 0;
    static boolean turn = true;
    static int tempscore = 0;

    public void rollFunc(View view) {
        TextView res = (TextView) findViewById(R.id.result);
        int d_val = 1 + (int) (Math.random() * 6);
        boolean player = turn;
        ImageView dface = (ImageView) findViewById(R.id.face);
        switch (d_val) {
            case 1:
                dface.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dface.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dface.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dface.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dface.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dface.setImageResource(R.drawable.dice6);
                break;
        }
        if (d_val == 1) {
            tempscore = 0;
            turn = !turn;
            res.setText(String.valueOf(turn));
            if (turn == false)
                computerPlay(view);
        } else
            tempscore += d_val;
    }

    public void holdFunc(View view) {
        TextView res = (TextView) findViewById(R.id.result);
        if (turn == true) {
            score += tempscore;
            TextView sc = (TextView) findViewById(R.id.scoreV);
            sc.setText("YOUR SCORE - " + score);
        } else {
            cscore += tempscore;
            TextView csc = (TextView) findViewById(R.id.cscoreV);
            csc.setText("COMPUTER SCORE - " + cscore);
        }
        if (score >= 100) {
            res.setText("YOU WIN!");
            resetFunc(view);
        }
        if (cscore >= 100) {
            res.setText("COMPUTER WINS!");
            resetFunc(view);
        }
        tempscore = 0;
        turn = !turn;
        if (turn == false)
            computerPlay(view);
        res.setText(String.valueOf(turn));
    }

    public void resetFunc(View view) {
        score = 0;
        cscore = 0;
        tempscore = 0;
        turn = true;
    }

    public void computerPlay(View view) {
        if (turn == false) {
            rollFunc(view);
            if (Math.random() > 0.5) {
                holdFunc(view);
            } else {
                rollFunc(view);
            }
            computerPlay(view);
        }
    }
}
