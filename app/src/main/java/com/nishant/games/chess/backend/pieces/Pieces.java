package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.Board;
import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;
import com.nishant.games.chess.backend.game.PlayerConstant;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;

abstract public class Pieces {
    private Point currentPoint, lastPoint;
    private boolean isAlive;
    private Player player;
    private PieceConstant pieceConstant;
    private String[][] board;

    Pieces(Point currentPoint, Player player, PieceConstant pieceConstant) {
        this.currentPoint = currentPoint;
        this.player = player;
        this.pieceConstant = pieceConstant;
        lastPoint = null;
        isAlive = true;
        board = Board.board;
    }

    public final Point getCurrentPoint() {
        return currentPoint;
    }

    public final boolean isAlive() {
        return isAlive;
    }

    final PlayerConstant getPlayerConstant() {
        return player.getPlayerConstant();
    }

    public final Player getPlayer() {
        return player;
    }

    public PieceConstant getPieceConstant() {
        return pieceConstant;
    }

    public final void doMove(Point to) {
        board[to.x][to.y] = getPieceConstant().toString() + getPlayerConstant();
        board[currentPoint.x][currentPoint.y] = EMPTY.toString();
        lastPoint = currentPoint;
        currentPoint = to;
    }

    public final void undoMove(String piece) {
        board[lastPoint.x][lastPoint.y] = getPieceConstant().toString() + getPlayerConstant();
        board[currentPoint.x][currentPoint.y] = piece;
        currentPoint = lastPoint;
        lastPoint = null;
    }

    public final void kill() {
        isAlive = false;
    }

    public final void revive() {
        isAlive = true;
    }

    @Override
    public final boolean equals(Object obj) {
        if(this == obj) return true;
        if (obj instanceof Pieces) {
            Pieces p = (Pieces) obj;
            return p.lastPoint == this.lastPoint && p.currentPoint == this.currentPoint;
        }
        return false;
    }

    public abstract List<Point> getMoves();

    @NotNull
    @Override
    public String toString() {
        return getPieceConstant().toString()+getPlayerConstant()+"@"+currentPoint.toString();
    }
}
