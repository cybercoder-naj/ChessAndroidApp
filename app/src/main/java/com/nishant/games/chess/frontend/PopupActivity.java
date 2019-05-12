package com.nishant.games.chess.frontend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.nishant.games.chess.R;
import com.nishant.games.chess.backend.game.PieceConstant;

public class PopupActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PopupActivity";
    Button rook, knight, bishop, queen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);

        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        getWindow().setLayout((int)(dm.widthPixels*0.8), (int)(dm.heightPixels*0.57));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y =-28;

        getWindow().setAttributes(params);

        rook = findViewById(R.id.choose_rook);
        knight = findViewById(R.id.choose_knight);
        bishop = findViewById(R.id.choose_bishop);
        queen = findViewById(R.id.choose_queen);

        rook.setOnClickListener(this);
        knight.setOnClickListener(this);
        bishop.setOnClickListener(this);
        queen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.choose_rook:
                intent.putExtra("chosenPiece", PieceConstant.ROOK);
                break;

            case R.id.choose_knight:
                intent.putExtra("chosenPiece", PieceConstant.KNIGHT);
                break;

            case R.id.choose_bishop:
                intent.putExtra("chosenPiece", PieceConstant.BISHOP);
                break;

            case R.id.choose_queen:
                intent.putExtra("chosenPiece", PieceConstant.QUEEN);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
