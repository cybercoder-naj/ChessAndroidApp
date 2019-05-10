package com.nishant.games.chess.backend.game;

import android.graphics.Point;

import com.nishant.games.chess.backend.pieces.Bishop;
import com.nishant.games.chess.backend.pieces.King;
import com.nishant.games.chess.backend.pieces.Knight;
import com.nishant.games.chess.backend.pieces.Pawn;
import com.nishant.games.chess.backend.pieces.Pieces;
import com.nishant.games.chess.backend.pieces.Queen;
import com.nishant.games.chess.backend.pieces.Rook;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class Player {

    private static Player player1 = new Player(PlayerConstant.PLAYER_1);
    private static Player player2 = new Player(PlayerConstant.PLAYER_2);
    private PlayerConstant player;
    private Pieces[] pieces = new Pieces[16];

    private Player(PlayerConstant player) {
        this.player = player;
        init();
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    private void init() {
        switch (player) {
            case PLAYER_1:
                pieces[0] = new Pawn(new Point(6, 0), this);
                pieces[1] = new Pawn(new Point(6, 1), this);
                pieces[2] = new Pawn(new Point(6, 2), this);
                pieces[3] = new Pawn(new Point(6, 3), this);
                pieces[4] = new Pawn(new Point(6, 4), this);
                pieces[5] = new Pawn(new Point(6, 5), this);
                pieces[6] = new Pawn(new Point(6, 6), this);
                pieces[7] = new Pawn(new Point(6, 7), this);
                pieces[8] = new Rook(new Point(7, 0), this);
                pieces[9] = new Knight(new Point(7, 1), this);
                pieces[10] = new Bishop(new Point(7, 2), this);
                pieces[11] = new Queen(new Point(7, 3), this);
                pieces[12] = new King(new Point(7, 4), this);
                pieces[13] = new Bishop(new Point(7, 5), this);
                pieces[14] = new Knight(new Point(7, 6), this);
                pieces[15] = new Rook(new Point(7, 7), this);
                break;

            case PLAYER_2:
                pieces[0] = new Pawn(new Point(1, 0), this);
                pieces[1] = new Pawn(new Point(1, 1), this);
                pieces[2] = new Pawn(new Point(1, 2), this);
                pieces[3] = new Pawn(new Point(1, 3), this);
                pieces[4] = new Pawn(new Point(1, 4), this);
                pieces[5] = new Pawn(new Point(1, 5), this);
                pieces[6] = new Pawn(new Point(1, 6), this);
                pieces[7] = new Pawn(new Point(1, 7), this);
                pieces[8] = new Rook(new Point(0, 0), this);
                pieces[9] = new Knight(new Point(0, 1), this);
                pieces[10] = new Bishop(new Point(0, 2), this);
                pieces[11] = new Queen(new Point(0, 3), this);
                pieces[12] = new King(new Point(0, 4), this);
                pieces[13] = new Bishop(new Point(0, 5), this);
                pieces[14] = new Knight(new Point(0, 6), this);
                pieces[15] = new Rook(new Point(0, 7), this);
                break;
        }
    }

    public King getKing() {
        return (King) pieces[12];
    }

    public List<Point> getMoves() {
        List<Point> moves = new ArrayList<>();
        for (Pieces piece: pieces)
            if(piece.isAlive()) moves.addAll(piece.getMoves());
        return moves;
    }

    @NotNull
    @Override
    public String toString() {
        return player.toString();
    }

    public PlayerConstant getPlayerConstant() {
        return player;
    }

    public Pieces getPiece(Point point) {
        for (Pieces piece : pieces) {
            if (piece.isAlive() && piece.getCurrentPoint().equals(point)) {
                return piece;
            }
        }
        return null;
    }

    public void killPiece(Pieces piece) {
        for (int i = 0; i < 16; i++) {
            if (pieces[i].isAlive() && pieces[i].equals(piece)) {
                pieces[i].kill();
                break;
            }
        }
    }

    public void revivePiece(Pieces piece) {
        for (int i = 0; i < 16; i++) {
            if(!pieces[i].isAlive() && pieces[i].equals(piece)) {
                pieces[i].revive();
                break;
            }
        }
    }

    public void promotePawn(Pieces piece) {
        Board.board[piece.getCurrentPoint().x][piece.getCurrentPoint().y] = piece.getPieceConstant().toString() + piece.getPlayer();
        for (int i = 0; i < 16; i++)
            if (pieces[i].getCurrentPoint().equals(piece.getCurrentPoint()))
                pieces[i] = piece;
    }

    public void reset() {
        init();
    }
}