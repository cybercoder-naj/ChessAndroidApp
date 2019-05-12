package com.nishant.games.chess.frontend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nishant.games.chess.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        findViewById(R.id.button).setOnClickListener(v -> finish());
    }
}
