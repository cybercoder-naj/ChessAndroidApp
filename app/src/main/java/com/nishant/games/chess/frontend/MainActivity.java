package com.nishant.games.chess.frontend;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.nishant.games.chess.R;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private Button play, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_horizontal);
        linearLayout = findViewById(R.id.buttons);
        play = findViewById(R.id.play);
        exit = findViewById(R.id.exit);

        progressBar.setScaleY(3f);

        progressBarAnimation();

        play.setOnClickListener(v -> startActivity(new Intent(".frontend.GameActivity")));

        exit.setOnClickListener(v -> {
            AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this)
                    .setCancelable(false)
                    .setMessage("Do you really want to exit?")
                    .setPositiveButton("Yes", (dialog, which) -> finish())
                    .setNegativeButton("No", (dialog, which) -> dialog.cancel());
            adb.create().show();
        });
    }

    private void progressBarAnimation() {
        ProgressBarAnim anim = new ProgressBarAnim(progressBar, linearLayout);
        anim.setDuration(8000);
        progressBar.setAnimation(anim);
    }
}
