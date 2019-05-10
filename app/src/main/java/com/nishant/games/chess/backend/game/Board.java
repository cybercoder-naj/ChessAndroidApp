package com.nishant.games.chess.backend.game;

import android.graphics.Point;

import static com.nishant.games.chess.backend.game.PieceConstant.BISHOP;
import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;
import static com.nishant.games.chess.backend.game.PieceConstant.KING;
import static com.nishant.games.chess.backend.game.PieceConstant.KNIGHT;
import static com.nishant.games.chess.backend.game.PieceConstant.PAWN;
import static com.nishant.games.chess.backend.game.PieceConstant.QUEEN;
import static com.nishant.games.chess.backend.game.PieceConstant.ROOK;
import static com.nishant.games.chess.backend.game.PlayerConstant.PLAYER_1;
import static com.nishant.games.chess.backend.game.PlayerConstant.PLAYER_2;

public class Board {
    public static String[][] board = new String[8][8];

    static {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = EMPTY.toString();
            }
        }

        for (int i = 0; i < 8; i++) {
            board[1][i] = PAWN.toString() + PLAYER_2;
            board[6][i] = PAWN.toString() + PLAYER_1;

            switch (i) {
                case 0:
                    setPiece(i, ROOK);
                    break;

                case 1:
                    setPiece(i, KNIGHT);
                    break;

                case 2:
                    setPiece(i, BISHOP);
            }
        }

        board[0][3] = QUEEN.toString() + PLAYER_2;
        board[7][3] = QUEEN.toString() + PLAYER_1;
        board[0][4] = KING.toString() + PLAYER_2;
        board[7][4] = KING.toString() + PLAYER_1;
    }

    private static void setPiece(int i, PieceConstant pieceConstant) {
        board[0][i] = pieceConstant.toString() + PLAYER_2;
        board[0][7 - i] = pieceConstant.toString() + PLAYER_2;
        board[7][i] = pieceConstant.toString() + PLAYER_1;
        board[7][7 - i] = pieceConstant.toString() + PLAYER_1;
    }

    public static boolean isEmpty(Point point) {
        return board[point.x][point.y].equals(EMPTY.toString());
    }

    public static boolean isEmpty(int x, int y) {
        return isEmpty(new Point(x, y));
    }

    public static void reset() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = EMPTY.toString();
            }
        }

        for (int i = 0; i < 8; i++) {
            board[1][i] = PAWN.toString() + PLAYER_2;
            board[6][i] = PAWN.toString() + PLAYER_1;

            switch (i) {
                case 0:
                    setPiece(i, ROOK);
                    break;

                case 1:
                    setPiece(i, KNIGHT);
                    break;

                case 2:
                    setPiece(i, BISHOP);
            }
        }

        board[0][3] = QUEEN.toString() + PLAYER_2;
        board[7][3] = QUEEN.toString() + PLAYER_1;
        board[0][4] = KING.toString() + PLAYER_2;
        board[7][4] = KING.toString() + PLAYER_1;
    }
}
