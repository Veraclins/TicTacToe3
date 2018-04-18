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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SelectBoard extends AppCompatActivity {
    private int gameBoard;
    private String gameMode;
    private String playerMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_board);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
       gameMode = intent != null ? intent.getString("mode") : null;
       playerMarker = intent != null ? intent.getString("marker") : null;


    }


    public void select3by3Board (View view){
        setContentView(R.layout.activity_select_board);
        gameBoard = 3;

        if (gameMode != null && gameMode.equals("1")) {
            startSinglePlayerGame(gameBoard, playerMarker);
        } else {
            startTwoPlayerGame(gameBoard, playerMarker);
        }
    }

    public void select5by5Board (View view){
        gameBoard = 5;
        if (gameMode != null && gameMode.equals("1")) {
            startSinglePlayerGame(gameBoard, playerMarker);
        } else {
            startTwoPlayerGame(gameBoard, playerMarker);
        }
    }

    private void startSinglePlayerGame(int board, String marker){
        gameBoard = board;
        playerMarker = marker;
        //   Create an intent that calls the activity class and pass the gameBoard selected by user
        Intent intent = new Intent(this, PlaySinglePlayerGame.class);
        intent.putExtra("board", String.valueOf(gameBoard));
        intent.putExtra("marker", String.valueOf(playerMarker));
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    private void startTwoPlayerGame(int board, String marker){
        gameBoard = board;
        playerMarker = marker;
        //   Create an intent that calls the activity class and pass the gameBoard selected by user
        Intent intent = new Intent(this, PlayTwoPlayerGame.class);
        intent.putExtra("board", String.valueOf(gameBoard));
        intent.putExtra("marker", String.valueOf(playerMarker));
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }


    public void selectX(View view) {
        playerMarker = "X";
    }

    public void selectO(View view) {
        playerMarker = "O";
    }

}
