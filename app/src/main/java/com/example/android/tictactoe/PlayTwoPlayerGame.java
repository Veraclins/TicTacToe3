/*
 * Copyright 2018 Agada Clinton Innocent
 * Email: icstps4love@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PlayTwoPlayerGame extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button [3][3];
    private Button[][] buttons_5 = new Button [5][5];

    private boolean player1Turn = true;

    private int player1Points = 0;
    private int player2Points = 0;

    private TextView textViewPlayer1Points;
    private TextView textViewPlayer2Points;
    private TextView textViewPlayerTurn;


    private int round;
    private String board;
    private String player1Marker;
    private String player2Marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
        board = intent != null ? intent.getString("board") : null;
        player1Marker = intent != null ? intent.getString("marker") : null;


        if (player1Marker != null && player1Marker.equals("X")){
            player2Marker = "O";
        } else {
            player2Marker = "X";
        }
        if (board != null && board.equals("5")) {

            setContentView(R.layout.activity_select5x5_board);

            textViewPlayer1Points = findViewById(R.id.text_score1);
            textViewPlayer2Points = findViewById(R.id.text_score2);
            textViewPlayerTurn = findViewById(R.id.text_turn);

            for (int a = 0; a < 5; a++) {
                for (int b = 0; b < 5; b++) {
                    String buttonID = "button_" + a + b;
                    int resourceID = getResources().getIdentifier(buttonID,"id", getPackageName());
                    buttons_5[a][b] = findViewById(resourceID);
                    buttons_5[a][b].setOnClickListener(this);
                }
            }
        } else {

            setContentView(R.layout.activity_select3x3_board);
            textViewPlayer1Points = findViewById(R.id.text_score1);
            textViewPlayer2Points = findViewById(R.id.text_score2);
            textViewPlayerTurn = findViewById(R.id.text_turn);

            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    String buttonID = "button_" + a + b;
                    int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    buttons[a][b] = findViewById(resourceID);
                    buttons[a][b].setOnClickListener(this);
                }
            }
        }
//         Sets a listener on the reset button

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        changeTurn(player1Turn);
    }

    /**
     * Watches for button clicks and execute player game
     */
    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {

            ((Button) view).setText(player1Marker);
            ((Button) view).setTextColor(Color.GREEN);

        } else {
            ((Button) view).setText(player2Marker);
            ((Button) view).setTextColor(Color.RED);
        }

        round++;

        if (checkForWinner()) {
            if (player1Turn) {
                player1Wins ();
            } else {
                player2Wins ();
            }
        } else if (board.equals("3") && round == 9 || board.equals("5") && round == 25) {
            draw ();
        } else {
            player1Turn = !player1Turn;
        }
        changeTurn(player1Turn);
    }

    /**
     * Changes the turn of players
     */
    private void changeTurn(boolean player1Turn) {
        String playerTurn;
        if (player1Turn){
            playerTurn = "Player 1 to Move";
            textViewPlayerTurn.setTextColor(Color.GREEN);
        } else {
            playerTurn = "Player 2 to Move";
            textViewPlayerTurn.setTextColor(Color.RED);
        }
        textViewPlayerTurn.setText(String.valueOf(playerTurn));
    }

    /**
     * Checks if there is a winner from the latest move
     */
    private boolean checkForWinner() {

        if (board != null && board.equals("3")) {
            String[][] cell = new String[3][3];

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cell[i][j] = buttons[i][j].getText().toString();
                }
            }
            for (int i = 0; i < 3; i++) {
                if (cell[i][0].equals(cell[i][1])
                        && cell[i][0].equals(cell[i][2])
                        && !cell[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < 3; i++) {
                if (cell[0][i].equals(cell[1][i])
                        && cell[0][i].equals(cell[2][i])
                        && !cell[0][i].equals("")) {
                    return true;
                }
            }

            return cell[0][0].equals(cell[1][1])
                    && cell[0][0].equals(cell[2][2])
                    && !cell[0][0].equals("")
                    || cell[0][2].equals(cell[1][1])
                    && cell[0][2].equals(cell[2][0])
                    && !cell[0][2].equals("");
        } else {
            String[][] cell = new String[5][5];

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    cell[i][j] = buttons_5[i][j].getText().toString();
                }
            }
            for (int i = 0; i < 5; i++) {
                if (cell[i][0].equals(cell[i][1])
                        && cell[i][0].equals(cell[i][2])
                        && cell[i][0].equals(cell[i][3])
                        && cell[i][0].equals(cell[i][4])
                        && !cell[i][0].equals("")) {
                    return true;
                }
            }

            for (int i = 0; i < 5; i++) {
                if (cell[0][i].equals(cell[1][i])
                        && cell[0][i].equals(cell[2][i])
                        && cell[0][i].equals(cell[3][i])
                        && cell[0][i].equals(cell[4][i])
                        && !cell[0][i].equals("")) {
                    return true;
                }
            }

            return cell[0][0].equals(cell[1][1])
                    && cell[0][0].equals(cell[2][2])
                    && cell[0][0].equals(cell[3][3])
                    && cell[0][0].equals(cell[4][4])
                    && !cell[0][0].equals("")
                    || cell[0][4].equals(cell[1][3])
                    && cell[0][4].equals(cell[2][2])
                    && cell[0][4].equals(cell[3][1])
                    && cell[0][4].equals(cell[4][0])
                    && !cell[0][4].equals("");
        }
    }

    /**
     * Updates the UI if the game has been won by Player one
     */
    private void player1Wins () {
        player1Points++;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("Good job Player 1 \n You are the Winner");
        text.setTextColor(Color.GREEN);

        ImageView image = layout.findViewById(R.id.toast_image);
        image.setImageResource(R.drawable.thumb);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        updatePointsText();
        resetBoard();
    }

    /**
     * Updates the UI if the game has been won by Player two
     */
    private void player2Wins () {
        player2Points++;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("Good job Player 2 \n You are the Winner");
        text.setTextColor(Color.GREEN);

        ImageView image = layout.findViewById(R.id.toast_image);
        image.setImageResource(R.drawable.thumb);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        updatePointsText();
        resetBoard();
    }

    /**
     * Updates the UI if the game ends in a draw
     */
    private void draw () {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("It's a draw \n No one could get the better of the other!");
        text.setTextColor(Color.YELLOW);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        resetBoard();
    }

    /**
     * Updates the UI with the points of each player
     */
    private void updatePointsText () {
        textViewPlayer1Points.setText(String.valueOf(player1Points));
        textViewPlayer2Points.setText(String.valueOf(player2Points));
    }

    /**
     * Clears the board for another round of game
     */
    private void resetBoard () {
        if (board != null && board.equals("3")) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setText("");
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    buttons_5[i][j].setText("");
                }
            }
        }

        round = 0;
        player1Turn = true;
    }

    /**
     * Clears the board for another round of game and resets the points
     */
    private void resetGame () {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
