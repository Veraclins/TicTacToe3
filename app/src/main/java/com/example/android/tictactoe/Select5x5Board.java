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


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Select5x5Board extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select5x5_board);

        // Get the Intent that started this activity and extract the string "board"
        Bundle intent = getIntent().getExtras();
        String gameBoard = intent != null ? intent.getString("board") : null;
    }

    public void resetGame(View view) {
    }
}
