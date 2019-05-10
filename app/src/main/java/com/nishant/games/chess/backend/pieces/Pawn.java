package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nishant.games.chess.backend.game.Board.board;
import static com.nishant.games.chess.backend.game.Board.isEmpty;
import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;
import static com.nishant.games.chess.backend.game.PlayerConstant.PLAYER_1;
import static com.nishant.games.chess.backend.game.PlayerConstant.PLAYER_2;

public final class Pawn extends Pieces {
    public Pawn(Point currentPoint, Player player) {
        super(currentPoint, player, PieceConstant.PAWN);
    }

    @Override
    public List<Point> getMoves() {
        int x = getCurrentPoint().x, y = getCurrentPoint().y;
        List<Point> moves = new ArrayList<>();
        if (getPlayer().toString().equals(PLAYER_1.toString())) {
            if (x > 0 && isEmpty(x - 1, y)) {
                doMove(new Point(x - 1, y));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y));
                undoMove(EMPTY.toString());
                if (x == 6 && isEmpty(x - 2, y)) {
                    doMove(new Point(x - 2, y));
                    if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 2, y));
                    undoMove(EMPTY.toString());
                }
            }
            if (x > 0 && y < 7 && !isEmpty(x - 1, y + 1) && !getPlayer().toString().equals(board[x - 1][y + 1].substring(1))) {
                String piece = board[x - 1][y + 1];
                doMove(new Point(x - 1, y + 1));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y + 1));
                undoMove(piece);
            }
            if (y > 0 && x > 0 && !isEmpty(x - 1, y - 1) && !getPlayer().toString().equals(board[x - 1][y - 1].substring(1))) {
                String piece = board[x - 1][y - 1];
                doMove(new Point(x - 1, y - 1));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y - 1));
                undoMove(piece);
            }
        }

        if (getPlayer().toString().equals(PLAYER_2.toString())) {
            if (x < 7 && isEmpty(x + 1, y)) {
                doMove(new Point(x + 1, y));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y));
                undoMove(EMPTY.toString());
                if (x == 1 && isEmpty(x + 2, y)) {
                    doMove(new Point(x + 2, y));
                    if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 2, y));
                    undoMove(EMPTY.toString());
                }
            }
            if (x < 7 && y < 7 && !isEmpty(x + 1, y + 1) && !getPlayer().toString().equals(board[x + 1][y + 1].substring(1))) {
                String piece = board[x + 1][y + 1];
                doMove(new Point(x + 1, y + 1));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y + 1));
                undoMove(piece);
            }
            if (x < 7 && y > 0 && !isEmpty(x + 1, y - 1) && !getPlayer().toString().equals(board[x + 1][y - 1].substring(1))) {
                String piece = board[x + 1][y - 1];
                doMove(new Point(x + 1, y - 1));
                if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y - 1));
                undoMove(piece);
            }
        }
        return moves;
    }
}
