package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Select3x3Board extends AppCompatActivity {
    private String gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select3x3_board);

        // Get the Intent that started this activity and extract the string
        Bundle intent = getIntent().getExtras();
        gameBoard = intent != null ? intent.getString("board") : null;
    }

    public void changeBoard(View view) {
    }

    public void changeMode(View view) {
    }

    public void resetGame(View view) {
    }
}
