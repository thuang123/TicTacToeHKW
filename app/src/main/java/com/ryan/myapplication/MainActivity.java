package com.ryan.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private Board game;
    private ArrayList<Button> buttons;

    private TextView playerTurn;

    public int turnCounter = 1;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttons = new ArrayList<>();
        buttons.add((Button) findViewById(R.id.button0));
        buttons.add((Button) findViewById(R.id.button1));
        buttons.add((Button) findViewById(R.id.button2));
        buttons.add((Button) findViewById(R.id.button3));
        buttons.add((Button) findViewById(R.id.button4));
        buttons.add((Button) findViewById(R.id.button5));
        buttons.add((Button) findViewById(R.id.button6));
        buttons.add((Button) findViewById(R.id.button7));
        buttons.add((Button) findViewById(R.id.button8));

        game = new Board();
        playerTurn = (TextView) findViewById(R.id.turn_player);

        startNewGame();

    }

    public void startNewGame() {

        game.clearBoard();
        playerTurn.setText("Player 1's turn");

        for (Button b : buttons) {

            b.setEnabled(true);
            b.setText("");
            b.setOnClickListener(new ButtonClickListener(b));

        }


    }

    private class ButtonClickListener implements View.OnClickListener {


        Button clickedButton;

        public ButtonClickListener(Button b) {

            this.clickedButton = b;

        }

        public void onClick(View view) {


            if (!game.checkWin() || !game.checkTie()) {

                if (clickedButton.isEnabled()) {
                    if (turnCounter == 1) {
                        int tempLocation = Integer.parseInt(String.valueOf(clickedButton.getId()).substring(5));

                        game.setMove(1, 1);
                        this.clickedButton.setText("X");
                        this.clickedButton.setEnabled(false);
                        turnCounter = 2;
                    }
                    if (turnCounter == 2) {
                        int tempLocation = Integer.parseInt(String.valueOf(clickedButton.getId()).substring(5));

                        game.setMove(2, 2);
                        this.clickedButton.setText("O");
                        this.clickedButton.setEnabled(false);
                        turnCounter = 1;
                    }
                }
            }

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
