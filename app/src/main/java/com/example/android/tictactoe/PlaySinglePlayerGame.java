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
import android.os.SystemClock;
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

import java.util.Random;


public class PlaySinglePlayerGame extends AppCompatActivity implements View.OnClickListener {

    private boolean playerTurn = true;

    private int playerPoints = 0;
    private int compPoints = 0;

    private TextView textViewplayerPoints;
    private TextView textViewcompPoints;
    private TextView textViewPlayerTurn;


    private int round;
    private int boardSize;
    private Button[][] buttons;
    private String playerMarker;
    private String compMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the Intent that started this activity and extract the data passed to it
        Bundle intent = getIntent().getExtras();
        boardSize = 3;
        playerMarker = intent != null ? intent.getString("marker") : null;
        buttons = new Button [boardSize][boardSize];


        if (playerMarker != null && playerMarker.equals("X")){
            compMarker = "O";
        } else {
            compMarker = "X";
        }
            setContentView(R.layout.activity_select3x3_board);
            textViewplayerPoints = findViewById(R.id.text_score1);
            textViewcompPoints = findViewById(R.id.text_score2);
            textViewPlayerTurn = findViewById(R.id.text_turn);

//            Set the names for the computer and User
            TextView textViewComp = findViewById(R.id.text_p2);
            TextView textViewPlayer = findViewById(R.id.text_p1);
            textViewComp.setText(String.valueOf("Droid"));
            textViewPlayer.setText(String.valueOf("You"));

            for (int a = 0; a < boardSize; a++) {
                for (int b = 0; b < boardSize; b++) {
                    String buttonID = "button_" + a + b;
                    int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
                    buttons[a][b] = findViewById(resourceID);
                    buttons[a][b].setOnClickListener(this);
                }
            }

        Button resetButton = findViewById(R.id.button_reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
        changeTurn();
    }
    /**
     * Watches for button clicks and execute player game
     */
    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }

        if (playerTurn) {

            ((Button) view).setText(playerMarker);
            ((Button) view).setTextColor(Color.GREEN);
        }
        finishMove();
    }
     /**
     * This was implemented using my understanding of the strategies of the game.
     * It is therefore not unbeatable.
     * My desire is to have the computer make moves based on on certain conditions and this, it does to a certain level.
     * I chose not to use any of the more refined algorithms so I can figure out a solution of my own
     *
     * Plays the computer turn and update the UI as appropriate
     */
    private void compMove ()  {
        if(playerTurn) return;
            String[][] cell = new String[3][3];
            
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    cell[i][j] = buttons[i][j].getText().toString();
                }
            }
//            CHECK IF WINNING IS POSSIBLE WITH NEXT MOVE
//            Check for horizontal win possibility
            for (int i = 0; i < 3; i++) {
                if (cell[i][0].equals(cell[i][1])
                        && cell[i][0].equals(compMarker)
                        && cell[i][2].equals("")) {
                    buttons[i][2].setText(compMarker);
                    buttons[i][2].setTextColor(Color.RED);
                    finishMove();
                    return;
                } else if (cell[i][0].equals(cell[i][2])
                        && cell[i][0].equals(compMarker)
                        && cell[i][1].equals("")) {
                    buttons[i][1].setText(compMarker);
                    buttons[i][1].setTextColor(Color.RED);
                    finishMove();
                    return;
                    
                } else if (cell[i][1].equals(cell[i][2])
                        && cell[i][1].equals(compMarker)
                        && cell[i][0].equals("")) {
                    buttons[i][0].setText(compMarker);
                    buttons[i][0].setTextColor(Color.RED);
                    finishMove();
                    return;
                }
            }
//            Check for vertical win possibility
            for (int i = 0; i < 3; i++) {
                if (cell[0][i].equals(cell[1][i])
                        && cell[0][i].equals(compMarker)
                        && cell[2][i].equals("")) {
                    buttons[2][i].setText(compMarker);
                    buttons[2][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                    
                } else if (cell[0][i].equals(cell[2][i])
                        && cell[0][i].equals(compMarker)
                        && cell[1][i].equals("")) {
                    buttons[1][i].setText(compMarker);
                    buttons[1][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                    
                } else if (cell[1][i].equals(cell[2][i])
                        && cell[1][i].equals(compMarker)
                        && cell[0][i].equals("")) {
                    buttons[0][i].setText(compMarker);
                    buttons[0][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                }
            }
//            Check for diagonal win possibility (top-left to bottom-right)
            if (cell[0][0].equals(cell[1][1])
                    && cell[0][0].equals(compMarker)
                    && cell[2][2].equals("")){
                buttons[2][2].setText(compMarker);
                buttons[2][2].setTextColor(Color.RED);

                finishMove();
                return;
                
            } else if (cell[0][0].equals(cell[2][2])
                    && cell[0][0].equals(compMarker)
                    && cell[1][1].equals("")){
                buttons[1][1].setText(compMarker);
                buttons[1][1].setTextColor(Color.RED);

                finishMove();
                return;

                
            } else if (cell[2][2].equals(cell[1][1])
                    && cell[2][2].equals(compMarker)
                    && cell[0][0].equals("")) {
                buttons[0][0].setText(compMarker);
                buttons[0][0].setTextColor(Color.RED);

                finishMove();
                return;


//            Check for diagonal win possibility (top-right to bottom-left)
            } else if (cell[0][2].equals(cell[1][1])
                    && cell[0][2].equals(compMarker)
                    && cell[2][0].equals("")){
                buttons[2][0].setText(compMarker);
                buttons[2][0].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[0][2].equals(cell[2][0])
                    && cell[0][2].equals(compMarker)
                    && cell[1][1].equals("")){
                buttons[1][1].setText(compMarker);
                buttons[1][1].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[2][0].equals(cell[1][1])
                    && cell[2][0].equals(compMarker)
                    && cell[0][2].equals("")) {
                buttons[0][2].setText(compMarker);
                buttons[0][2].setTextColor(Color.RED);

                finishMove();
                return;

            }

//            CHECK IF IT CAN BLOCK HUMAN WINNING WITH NEXT MOVE
//            Check for horizontal block possibility
                for (int i = 0; i < 3; i++) {
                    if (cell[i][0].equals(cell[i][1])
                            && cell[i][0].equals(playerMarker)
                            && cell[i][2].equals("")) {
                        buttons[i][2].setText(compMarker);
                        buttons[i][2].setTextColor(Color.RED);
                        finishMove();
                        return;

                    } else if (cell[i][0].equals(cell[i][2])
                            && cell[i][0].equals(playerMarker)
                            && cell[i][1].equals("")) {
                        buttons[i][1].setText(compMarker);
                        buttons[i][1].setTextColor(Color.RED);
                        finishMove();
                        return;

                    } else if (cell[i][1].equals(cell[i][2])
                            && cell[i][1].equals(playerMarker)
                            && cell[i][0].equals("")) {
                        buttons[i][0].setText(compMarker);
                        buttons[i][0].setTextColor(Color.RED);
                        finishMove();
                        return;
                    }
                }

//            Check for vertical block possibility
            for (int i = 0; i < 3; i++) {
                if (cell[0][i].equals(cell[1][i])
                        && cell[0][i].equals(playerMarker)
                        && cell[2][i].equals("")) {
                    buttons[2][i].setText(compMarker);
                    buttons[2][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                    
                } else if (cell[0][i].equals(cell[2][i])
                        && cell[0][i].equals(playerMarker)
                        && cell[1][i].equals("")) {
                    buttons[1][i].setText(compMarker);
                    buttons[1][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                    
                } else if (cell[1][i].equals(cell[2][i])
                        && cell[1][i].equals(playerMarker)
                        && cell[0][i].equals("")) {
                    buttons[0][i].setText(compMarker);
                    buttons[0][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                }
            }
//            Check for diagonal block possibility (top-left to bottom-right)
            if (cell[0][0].equals(cell[1][1])
                    && cell[0][0].equals(playerMarker)
                    && cell[2][2].equals("")){
                buttons[2][2].setText(compMarker);
                buttons[2][2].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[0][0].equals(cell[2][2])
                    && cell[0][0].equals(playerMarker)
                    && cell[1][1].equals("")){
                buttons[1][1].setText(compMarker);
                buttons[1][1].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[2][2].equals(cell[1][1])
                    && cell[2][2].equals(playerMarker)
                    && cell[0][0].equals("")) {
                buttons[0][0].setText(compMarker);
                buttons[0][0].setTextColor(Color.RED);

                finishMove();
                return;

            }
            
//            Check for diagonal block possibility (top-right to bottom-left)
            if (cell[0][2].equals(cell[1][1])
                    && cell[0][2].equals(playerMarker)
                    && cell[2][0].equals("")){
                buttons[2][0].setText(compMarker);
                buttons[2][0].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[0][2].equals(cell[2][0])
                    && cell[0][2].equals(playerMarker)
                    && cell[1][1].equals("")){
                buttons[1][1].setText(compMarker);
                buttons[1][1].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[2][0].equals(cell[1][1])
                    && cell[2][0].equals(playerMarker)
                    && cell[0][2].equals("")) {
                buttons[0][2].setText(compMarker);
                buttons[0][2].setTextColor(Color.RED);

                finishMove();
                return;

            }
            
//            Play centre if empty
            if (cell[1][1].equals("")) {
                buttons[1][1].setText(compMarker);
                buttons[1][1].setTextColor(Color.RED);

                finishMove();
                return;

            }

//            Or play corners if available
            if (cell[0][0].equals("")) {
                buttons[0][0].setText(compMarker);
                buttons[0][0].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[0][2].equals("")) {
                buttons[0][2].setText(compMarker);
                buttons[0][2].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[2][0].equals("")) {
                buttons[2][0].setText(compMarker);
                buttons[2][0].setTextColor(Color.RED);

                finishMove();
                return;


            } else if (cell[2][2].equals("")) {
                buttons[2][2].setText(compMarker);
                buttons[2][2].setTextColor(Color.RED);

                finishMove();
                return;

            }
//            Or play any random empty cell
            int n = new Random().nextInt(3);
            for (int i = 0; i < 3; i++) {
                if (cell[i][n].equals("")) {
                    buttons[i][n].setText(compMarker);
                    buttons[i][n].setTextColor(Color.RED);
                    finishMove();
                    return;

                } else if (cell[n][i].equals("")) {
                    buttons[n][i].setText(compMarker);
                    buttons[n][i].setTextColor(Color.RED);
                    finishMove();
                    return;
                }
            }

    }

    /**
     * Completes every move for human and computer play
     */
    private void finishMove() {
        if (checkForWinner()) {
            if (playerTurn) {
                playerWins ();
            } else {
                compWins ();
            }
        } else if (boardSize == 3 && round == 9 || boardSize ==5 && round == 25) {
            draw ();
        } else {
            playerTurn = !playerTurn;
        }
        round++;
        changeTurn();
    }

    /**
     * Changes the turn between human and computer play
     */
    private void changeTurn() {
        String playerMove;
        if (playerTurn){
            playerMove = "Your Move pal";
            textViewPlayerTurn.setTextColor(Color.GREEN);
            textViewPlayerTurn.setText(String.valueOf(playerMove));
        } else {
            SystemClock.sleep(300);
            compMove();
        }

    }

    /**
     * Checks if there is a winner from the latest move
     */
    private boolean checkForWinner() {

        if (boardSize == 3) {
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
                    cell[i][j] = buttons[i][j].getText().toString();
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
     * Shows that the Player has won and update the UI as appropriate
     */
    private void playerWins () {
        playerPoints++;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("Good job Champ \n Let's go again!");
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
     * Shows that the Computer has won and update the UI as appropriate
     */
    private void compWins () {
        compPoints++;
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("You lose pal! \n Try Again");
        text.setTextColor(Color.RED);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        updatePointsText();
        resetBoard();
    }

    /**
     * Shows that the game has ended in a draw
     */
    private void draw () {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.my_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        TextView text = layout.findViewById(R.id.text);
        text.setText("It's a draw \n Not bad but you can win this!");
        text.setTextColor(Color.YELLOW);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
        resetBoard();
    }

    /**
     * Updates the UI with the points for eah player
     */
    private void updatePointsText () {
        textViewplayerPoints.setText(String.valueOf(playerPoints));
        textViewcompPoints.setText(String.valueOf(compPoints));
    }

    /**
     * Clears the board and make it ready for another round of game
     */
    private void resetBoard () {
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    buttons[i][j].setText("");
                }
            }

        round = 0;
        playerTurn = true;
    }

    /**
     * Clears the board and make it ready for another round of game and also clear the points by players
     */
    private void resetGame () {
        playerPoints = 0;
        compPoints = 0;
        updatePointsText();
        resetBoard();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }



}
