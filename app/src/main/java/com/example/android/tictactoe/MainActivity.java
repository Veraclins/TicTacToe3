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
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    int gameMode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void selectSinglePlayer(View view) {
        gameMode = 1;
        //   Create an intent that calls the activity class and pass the gameBoard selected by user
        Intent intent = new Intent(this, SelectMarker.class);
        int mode = gameMode;
        intent.putExtra("mode", String.valueOf(mode));
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }

    public void selectTwoPlayer(View view) {
        gameMode = 2;
        //   Create an intent that calls the activity class and pass the gameBoard selected by user
        Intent intent = new Intent(this, SelectMarker.class);
        int mode = gameMode;
        intent.putExtra("mode", String.valueOf(mode));
        if(intent.resolveActivity(getPackageManager())!=null) {
            startActivity(intent);
        }
    }


}
