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
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class SelectMarker extends AppCompatActivity {
    private String gameMode;
    private String playerMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_marker);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
       gameMode = intent != null ? intent.getString("mode") : null;


    }

    protected void selectBoard (String mode, String marker){
//        Single Player game is only available for the 3 x 3 board hence no need for choosing board in this mode
        if (Integer.parseInt(gameMode) == 1){
            startSinglePlayerGame();
        } else {
            //   Create an intent that calls the activity class and pass the gameBoard selected by user
            Intent intent = new Intent(this, SelectBoard.class);
            intent.putExtra("mode", String.valueOf(mode));
            intent.putExtra("marker", String.valueOf(marker));
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    public void selectX(View view) {
        playerMarker = "X";
        selectBoard(gameMode, playerMarker);
    }

    public void selectO(View view) {
        playerMarker = "O";
        selectBoard(gameMode, playerMarker);
    }

    protected void startSinglePlayerGame (){
        //   Create an intent that calls the activity class and pass the gameBoard selected by user
        Intent intent = new Intent(this, PlaySinglePlayerGame.class);
        intent.putExtra("marker", String.valueOf(playerMarker));
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

}
