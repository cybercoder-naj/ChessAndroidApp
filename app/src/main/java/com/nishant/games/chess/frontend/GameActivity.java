package com.nishant.games.chess.frontend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nishant.games.chess.R;
import com.nishant.games.chess.backend.game.Board;
import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;
import com.nishant.games.chess.backend.game.PlayerConstant;
import com.nishant.games.chess.backend.pieces.Bishop;
import com.nishant.games.chess.backend.pieces.King;
import com.nishant.games.chess.backend.pieces.Knight;
import com.nishant.games.chess.backend.pieces.Pawn;
import com.nishant.games.chess.backend.pieces.Pieces;
import com.nishant.games.chess.backend.pieces.Queen;
import com.nishant.games.chess.backend.pieces.Rook;

import java.util.List;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "GameActivity";
    public static final int REQUEST_PLAYER_1 = 1;
    public static final int REQUEST_PLAYER_2 = 2;

    Button[][] buttons = new Button[8][8];
    Button undo1, undo2;
    boolean choosePiece = true, hasCastled = false, doublePressed = false;
    int counter = 0;
    Player player1 = Player.getPlayer1(), player2 = Player.getPlayer2();
    Pieces piece, killedPiece;
    List<Point> moves;
    TextView txtPlayer1, txtPlayer2;
    String[][] previousState = new String[8][8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");
        txtPlayer1 = findViewById(R.id.txt_player1);
        txtPlayer2 = findViewById(R.id.txt_player2);
        init();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                buttons[i][j].setOnClickListener(this);
            }
        }
        undo1.setOnClickListener(this);
        undo2.setOnClickListener(this);
        txtPlayer1.setBackgroundColor(Color.GREEN);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
        if (isDestroyed()) {
            Board.reset();
            player1.reset();
            player2.reset();
            choosePiece = true;
            hasCastled = false;
            doublePressed = false;
            counter = 0;
            render();
        }
    }

    private void init() {
        Log.d(TAG, "init() called");
        buttons[0][0] = findViewById(R.id.btn00);
        buttons[0][1] = findViewById(R.id.btn01);
        buttons[0][2] = findViewById(R.id.btn02);
        buttons[0][3] = findViewById(R.id.btn03);
        buttons[0][4] = findViewById(R.id.btn04);
        buttons[0][5] = findViewById(R.id.btn05);
        buttons[0][6] = findViewById(R.id.btn06);
        buttons[0][7] = findViewById(R.id.btn07);
        buttons[1][0] = findViewById(R.id.btn10);
        buttons[1][1] = findViewById(R.id.btn11);
        buttons[1][2] = findViewById(R.id.btn12);
        buttons[1][3] = findViewById(R.id.btn13);
        buttons[1][4] = findViewById(R.id.btn14);
        buttons[1][5] = findViewById(R.id.btn15);
        buttons[1][6] = findViewById(R.id.btn16);
        buttons[1][7] = findViewById(R.id.btn17);
        buttons[2][0] = findViewById(R.id.btn20);
        buttons[2][1] = findViewById(R.id.btn21);
        buttons[2][2] = findViewById(R.id.btn22);
        buttons[2][3] = findViewById(R.id.btn23);
        buttons[2][4] = findViewById(R.id.btn24);
        buttons[2][5] = findViewById(R.id.btn25);
        buttons[2][6] = findViewById(R.id.btn26);
        buttons[2][7] = findViewById(R.id.btn27);
        buttons[3][0] = findViewById(R.id.btn30);
        buttons[3][1] = findViewById(R.id.btn31);
        buttons[3][2] = findViewById(R.id.btn32);
        buttons[3][3] = findViewById(R.id.btn33);
        buttons[3][4] = findViewById(R.id.btn34);
        buttons[3][5] = findViewById(R.id.btn35);
        buttons[3][6] = findViewById(R.id.btn36);
        buttons[3][7] = findViewById(R.id.btn37);
        buttons[4][0] = findViewById(R.id.btn40);
        buttons[4][1] = findViewById(R.id.btn41);
        buttons[4][2] = findViewById(R.id.btn42);
        buttons[4][3] = findViewById(R.id.btn43);
        buttons[4][4] = findViewById(R.id.btn44);
        buttons[4][5] = findViewById(R.id.btn45);
        buttons[4][6] = findViewById(R.id.btn46);
        buttons[4][7] = findViewById(R.id.btn47);
        buttons[5][0] = findViewById(R.id.btn50);
        buttons[5][1] = findViewById(R.id.btn51);
        buttons[5][2] = findViewById(R.id.btn52);
        buttons[5][3] = findViewById(R.id.btn53);
        buttons[5][4] = findViewById(R.id.btn54);
        buttons[5][5] = findViewById(R.id.btn55);
        buttons[5][6] = findViewById(R.id.btn56);
        buttons[5][7] = findViewById(R.id.btn57);
        buttons[6][0] = findViewById(R.id.btn60);
        buttons[6][1] = findViewById(R.id.btn61);
        buttons[6][2] = findViewById(R.id.btn62);
        buttons[6][3] = findViewById(R.id.btn63);
        buttons[6][4] = findViewById(R.id.btn64);
        buttons[6][5] = findViewById(R.id.btn65);
        buttons[6][6] = findViewById(R.id.btn66);
        buttons[6][7] = findViewById(R.id.btn67);
        buttons[7][0] = findViewById(R.id.btn70);
        buttons[7][1] = findViewById(R.id.btn71);
        buttons[7][2] = findViewById(R.id.btn72);
        buttons[7][3] = findViewById(R.id.btn73);
        buttons[7][4] = findViewById(R.id.btn74);
        buttons[7][5] = findViewById(R.id.btn75);
        buttons[7][6] = findViewById(R.id.btn76);
        buttons[7][7] = findViewById(R.id.btn77);

        undo1 = findViewById(R.id.undo1);
        undo2 = findViewById(R.id.undo2);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick() called with: v = [" + v + "]");
        switch (v.getId()) {
            case R.id.btn00:
                play(0, 0);
                break;
            case R.id.btn01:
                play(0, 1);
                break;
            case R.id.btn02:
                play(0, 2);
                break;
            case R.id.btn03:
                play(0, 3);
                break;
            case R.id.btn04:
                play(0, 4);
                break;
            case R.id.btn05:
                play(0, 5);
                break;
            case R.id.btn06:
                play(0, 6);
                break;
            case R.id.btn07:
                play(0, 7);
                break;
            case R.id.btn10:
                play(1, 0);
                break;
            case R.id.btn11:
                play(1, 1);
                break;
            case R.id.btn12:
                play(1, 2);
                break;
            case R.id.btn13:
                play(1, 3);
                break;
            case R.id.btn14:
                play(1, 4);
                break;
            case R.id.btn15:
                play(1, 5);
                break;
            case R.id.btn16:
                play(1, 6);
                break;
            case R.id.btn17:
                play(1, 7);
                break;
            case R.id.btn20:
                play(2, 0);
                break;
            case R.id.btn21:
                play(2, 1);
                break;
            case R.id.btn22:
                play(2, 2);
                break;
            case R.id.btn23:
                play(2, 3);
                break;
            case R.id.btn24:
                play(2, 4);
                break;
            case R.id.btn25:
                play(2, 5);
                break;
            case R.id.btn26:
                play(2, 6);
                break;
            case R.id.btn27:
                play(2, 7);
                break;
            case R.id.btn30:
                play(3, 0);
                break;
            case R.id.btn31:
                play(3, 1);
                break;
            case R.id.btn32:
                play(3, 2);
                break;
            case R.id.btn33:
                play(3, 3);
                break;
            case R.id.btn34:
                play(3, 4);
                break;
            case R.id.btn35:
                play(3, 5);
                break;
            case R.id.btn36:
                play(3, 6);
                break;
            case R.id.btn37:
                play(3, 7);
                break;
            case R.id.btn40:
                play(4, 0);
                break;
            case R.id.btn41:
                play(4, 1);
                break;
            case R.id.btn42:
                play(4, 2);
                break;
            case R.id.btn43:
                play(4, 3);
                break;
            case R.id.btn44:
                play(4, 4);
                break;
            case R.id.btn45:
                play(4, 5);
                break;
            case R.id.btn46:
                play(4, 6);
                break;
            case R.id.btn47:
                play(4, 7);
                break;
            case R.id.btn50:
                play(5, 0);
                break;
            case R.id.btn51:
                play(5, 1);
                break;
            case R.id.btn52:
                play(5, 2);
                break;
            case R.id.btn53:
                play(5, 3);
                break;
            case R.id.btn54:
                play(5, 4);
                break;
            case R.id.btn55:
                play(5, 5);
                break;
            case R.id.btn56:
                play(5, 6);
                break;
            case R.id.btn57:
                play(5, 7);
                break;
            case R.id.btn60:
                play(6, 0);
                break;
            case R.id.btn61:
                play(6, 1);
                break;
            case R.id.btn62:
                play(6, 2);
                break;
            case R.id.btn63:
                play(6, 3);
                break;
            case R.id.btn64:
                play(6, 4);
                break;
            case R.id.btn65:
                play(6, 5);
                break;
            case R.id.btn66:
                play(6, 6);
                break;
            case R.id.btn67:
                play(6, 7);
                break;
            case R.id.btn70:
                play(7, 0);
                break;
            case R.id.btn71:
                play(7, 1);
                break;
            case R.id.btn72:
                play(7, 2);
                break;
            case R.id.btn73:
                play(7, 3);
                break;
            case R.id.btn74:
                play(7, 4);
                break;
            case R.id.btn75:
                play(7, 5);
                break;
            case R.id.btn76:
                play(7, 6);
                break;
            case R.id.btn77:
                play(7, 7);
                break;
            case R.id.undo1:
                requestUndo(undo1);
                break;
            case R.id.undo2:
                requestUndo(undo2);
                break;
        }
    }

    private void play(int x, int y) {
        Log.d(TAG, "play() called with: x = [" + x + "], y = [" + y + "]");
        if (choosePiece) {
            if (!Board.isEmpty(x, y)) {
                piece = counter % 2 == 0 ? player1.getPiece(new Point(x, y)) : player2.getPiece(new Point(x, y));
                if (piece != null) {
                    moves = piece.getMoves();
                    if (moves.size() != 0) {
                        for (Point move : moves) {
                            if (Board.isEmpty(move.x, move.y))
                                buttons[move.x][move.y].setBackgroundColor(Color.GREEN);
                            else buttons[move.x][move.y].setBackgroundColor(Color.RED);
                        }
                        choosePiece = false;
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if ((i + j) % 2 == 0) buttons[i][j].setBackgroundColor(Color.WHITE);
                    else buttons[i][j].setBackgroundColor(Color.BLACK);
                }
            }
            if (moves.contains(new Point(x, y))) {
                undo1.setBackgroundColor(Color.RED);
                undo2.setBackgroundColor(Color.RED);
                undo1.setEnabled(false);
                undo2.setEnabled(false);
                for (int i = 0; i < 8; i++)
                    System.arraycopy(Board.board[i], 0, previousState[i], 0, 8);
                if (!Board.isEmpty(x, y)) {
                    piece.doMove(new Point(x, y));
                    if (counter % 2 == 0) {
                        killedPiece = player2.getPiece(new Point(x, y));
                        player2.killPiece(killedPiece);
                    } else {
                        killedPiece = player1.getPiece(new Point(x, y));
                        player1.killPiece(killedPiece);
                    }
                } else {
                    killedPiece = null;
                    piece.doMove(new Point(x, y));
                    if (piece instanceof King) {
                        if (counter % 2 == 0) {
                            if (x == 7 && y == 6) {
                                player1.getPiece(new Point(7, 7)).doMove(new Point(7, 5));
                                ((Rook) player1.getPiece(new Point(7, 5))).setHasMoved(true);
                                hasCastled = true;
                            } else if (x == 7 && y == 2) {
                                player1.getPiece(new Point(7, 0)).doMove(new Point(7, 3));
                                ((Rook) player1.getPiece(new Point(7, 3))).setHasMoved(true);
                                hasCastled = true;
                            }
                        } else if (counter % 2 == 1) {
                            if (x == 0 && y == 6) {
                                player2.getPiece(new Point(0, 7)).doMove(new Point(0, 5));
                                ((Rook) player2.getPiece(new Point(0, 5))).setHasMoved(true);
                                hasCastled = true;
                            } else if (x == 0 && y == 2) {
                                player1.getPiece(new Point(0, 0)).doMove(new Point(0, 3));
                                ((Rook) player2.getPiece(new Point(0, 3))).setHasMoved(true);
                                hasCastled = true;
                            }
                        }
                        ((King) piece).setHasMoved(true);
                    }
                }
                if (piece instanceof Rook) ((Rook) piece).setHasMoved(true);
                if (piece instanceof Pawn) {
                    if (x == 0) requestPromote(REQUEST_PLAYER_1);
                    else if (x == 7) requestPromote(REQUEST_PLAYER_2);
                }
                Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
                if (counter % 2 == 0 && player2.getKing().isCheck() && player2.getMoves().size() == 0) {
                    vibrator.vibrate(new long[]{0, 500, 400, 500}, -1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setCancelable(true)
                            .setMessage("Checkmate, Player 1 has Won!!")
                            .setPositiveButton("Return to game", (dialog, which) -> dialog.cancel())
                            .setNegativeButton("Exit to main", (dialog, which) -> finish());
                    builder.create().show();
                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) buttons[i][j].setEnabled(false);
                } else if (counter % 2 == 1 && player1.getKing().isCheck() && player1.getMoves().size() == 0) {
                    vibrator.vibrate(new long[]{0, 500, 400, 500}, -1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setCancelable(true)
                            .setMessage("Checkmate, Player 2 has Won!")
                            .setPositiveButton("Return to game", (dialog, which) -> dialog.cancel())
                            .setNegativeButton("Exit to main", (dialog, which) -> finish());
                    builder.create().show();
                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) buttons[i][j].setEnabled(false);
                } else if (counter % 2 == 0 ? player2.getKing().isCheck() : player1.getKing().isCheck()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View view = inflater.inflate(R.layout.custom_toast, findViewById(R.id.custom_layout));
                    Toast toast = new Toast(this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.setView(view);
                    toast.show();
                } else if ((counter % 2 == 0 && player2.getMoves().size() == 0)
                        || (counter % 2 == 1 && player1.getMoves().size() == 0)) {
                    vibrator.vibrate(new long[]{0, 500, 400, 500}, -1);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this)
                            .setCancelable(true)
                            .setMessage("It's a Stalemate!")
                            .setPositiveButton("Return to game", (dialog, which) -> dialog.cancel())
                            .setNegativeButton("Exit to main", (dialog, which) -> finish());
                    builder.create().show();
                    for (int i = 0; i < 8; i++)
                        for (int j = 0; j < 8; j++) buttons[i][j].setEnabled(false);
                }
                counter++;
                render();
                Handler handler = new Handler(Looper.getMainLooper());
                if (counter % 2 == 0) {
                    undo2.setBackgroundColor(Color.GREEN);
                    undo2.setEnabled(true);
                    handler.postDelayed(() -> {
                        undo2.setBackgroundColor(Color.RED);
                        undo2.setEnabled(false);
                    }, 2000);
                } else {
                    undo1.setBackgroundColor(Color.GREEN);
                    undo1.setEnabled(true);
                    handler.postDelayed(() -> {
                        undo1.setBackgroundColor(Color.RED);
                        undo1.setEnabled(false);
                    }, 2000);
                }
            }
            choosePiece = true;
        }
    }

    private void render() {
        Log.d(TAG, "render() called");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Board.board[i][j].equals(PieceConstant.EMPTY.toString())) {
                    buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                } else if (Board.board[i][j].substring(1).equals(PlayerConstant.PLAYER_1.toString())) {
                    switch (Board.board[i][j].substring(0, 1)) {
                        case "P":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_pawn, 0, 0, 0);
                            break;
                        case "R":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_rook, 0, 0, 0);
                            break;
                        case "N":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_knight, 0, 0, 0);
                            break;
                        case "B":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_bishop, 0, 0, 0);
                            break;
                        case "Q":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_queen, 0, 0, 0);
                            break;
                        case "K":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.white_king, 0, 0, 0);
                            break;
                    }
                } else {
                    switch (Board.board[i][j].substring(0, 1)) {
                        case "P":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_pawn, 0, 0, 0);
                            break;
                        case "R":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_rook, 0, 0, 0);
                            break;
                        case "N":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_knight, 0, 0, 0);
                            break;
                        case "B":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_bishop, 0, 0, 0);
                            break;
                        case "Q":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_queen, 0, 0, 0);
                            break;
                        case "K":
                            buttons[i][j].setCompoundDrawablesWithIntrinsicBounds(R.drawable.black_king, 0, 0, 0);
                            break;
                    }
                }
            }
        }
        if (counter % 2 == 0) {
            txtPlayer1.setBackgroundColor(Color.GREEN);
            txtPlayer2.setBackgroundColor(Color.BLACK);
        } else {
            txtPlayer1.setBackgroundColor(Color.WHITE);
            txtPlayer2.setBackgroundColor(Color.GREEN);
        }
    }

    private void requestUndo(Button button) {
        Log.d(TAG, "requestUndo() called with: button = [" + button + "]");
        button.setBackgroundColor(Color.RED);
        button.setEnabled(false);
        for (int i = 0; i < 8; i++)
            System.arraycopy(previousState[i], 0, Board.board[i], 0, 8);
        if (killedPiece != null) {
            if (counter % 2 == 0) player2.revivePiece(killedPiece);
            else player1.revivePiece(killedPiece);
            piece.undoMove(previousState[killedPiece.getCurrentPoint().x][killedPiece.getCurrentPoint().y]);
        }
        if (hasCastled) {
            if (piece instanceof King) ((King) piece).setHasMoved(false);
            else if (piece instanceof Rook) ((Rook) piece).setHasMoved(false);
        }
        hasCastled = false;
        piece.undoMove(PieceConstant.EMPTY.toString());
        counter--;
        render();
    }

    private void requestPromote(int requestCode) {
        Log.d(TAG, "requestPromote() called");
        Intent intent = new Intent(GameActivity.this, PopupActivity.class);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult() called with: requestCode = [" + requestCode + "], resultCode = [" + resultCode + "], data = [" + data + "]");
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == REQUEST_PLAYER_1) {
                switch ((PieceConstant) data.getSerializableExtra("chosenPiece")) {
                    case ROOK:
                        player1.promotePawn(new Rook(piece.getCurrentPoint(), player1));
                        break;

                    case KNIGHT:
                        player1.promotePawn(new Knight(piece.getCurrentPoint(), player1));
                        break;

                    case BISHOP:
                        player1.promotePawn(new Bishop(piece.getCurrentPoint(), player1));
                        break;

                    case QUEEN:
                        player1.promotePawn(new Queen(piece.getCurrentPoint(), player1));
                }
            } else if (requestCode == REQUEST_PLAYER_2) {
                switch ((PieceConstant) data.getSerializableExtra("chosenPiece")) {
                    case ROOK:
                        player2.promotePawn(new Rook(piece.getCurrentPoint(), player2));
                        break;

                    case KNIGHT:
                        player2.promotePawn(new Knight(piece.getCurrentPoint(), player2));
                        break;

                    case BISHOP:
                        player2.promotePawn(new Bishop(piece.getCurrentPoint(), player2));
                        break;

                    case QUEEN:
                        player2.promotePawn(new Queen(piece.getCurrentPoint(), player2));
                }
            }
            render();
        }
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed() called");

        Toast.makeText(GameActivity.this, "Please click exit icon on action back!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.d(TAG, "onCreateOptionsMenu() called with: menu = [" + menu + "]");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(TAG, "onOptionsItemSelected() called with: item = [" + item + "]");
        switch (item.getItemId()) {
            case R.id.m_restore:
                AlertDialog.Builder adb = new AlertDialog.Builder(GameActivity.this)
                        .setCancelable(false)
                        .setMessage("Do you really want to restart?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            Board.reset();
                            player1.reset();
                            player2.reset();
                            choosePiece = true;
                            hasCastled = false;
                            doublePressed = false;
                            counter = 0;
                            render();
                        })
                        .setNegativeButton("No", (dialog, which) -> dialog.cancel());
                adb.create().show();
                break;

            case R.id.m_info:
                Intent intent = new Intent(GameActivity.this, InfoActivity.class);
                startActivity(intent);
                break;

            case R.id.m_exit:
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this)
                        .setCancelable(false)
                        .setMessage("Do you really want to exit?")
                        .setPositiveButton("Yes", (dialog, which) -> finish())
                        .setNegativeButton("No", (dialog, which) -> dialog.cancel());
                builder.create().show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}