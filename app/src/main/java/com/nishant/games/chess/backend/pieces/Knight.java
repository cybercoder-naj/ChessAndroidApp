package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nishant.games.chess.backend.game.Board.board;
import static com.nishant.games.chess.backend.game.Board.isEmpty;
import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;

public final class Knight extends Pieces {
    public Knight(Point currentPoint, Player player) {
        super(currentPoint, player, PieceConstant.KNIGHT);
    }

    @Override
    public List<Point> getMoves() {
        List<Point> moves = new ArrayList<>();
        int x = getCurrentPoint().x, y = getCurrentPoint().y;
        if (x < 7 && y < 6 && isEmpty(x + 1, y + 2)) {
            doMove(new Point(x + 1, y + 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y + 2));
            undoMove(EMPTY.toString());
        }
        if (x > 0 && y < 6 && isEmpty(x - 1, y + 2)) {
            doMove(new Point(x - 1, y + 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y + 2));
            undoMove(EMPTY.toString());
        }
        if (x < 6 && y > 0 && isEmpty(x + 2, y - 1)) {
            doMove(new Point(x + 2, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 2, y - 1));
            undoMove(EMPTY.toString());
        }
        if (x < 6 && y < 7 && isEmpty(x + 2, y + 1)) {
            doMove(new Point(x + 2, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 2, y + 1));
            undoMove(EMPTY.toString());
        }
        if (x < 7 && y > 1 && isEmpty(x + 1, y - 2)) {
            doMove(new Point(x + 1, y - 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y - 2));
            undoMove(EMPTY.toString());
        }
        if (x > 0 && y > 1 && isEmpty(x - 1, y - 2)) {
            doMove(new Point(x - 1, y - 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y - 2));
            undoMove(EMPTY.toString());
        }
        if (x > 1 && y > 0 && isEmpty(x - 2, y - 1)) {
            doMove(new Point(x - 2, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 2, y - 1));
            undoMove(EMPTY.toString());
        }
        if (x > 1 && y < 7 && isEmpty(x - 2, y + 1)) {
            doMove(new Point(x - 2, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 2, y + 1));
            undoMove(EMPTY.toString());
        }

        if (x < 7 && y < 6 && !isEmpty(x + 1, y + 2) && !getPlayer().toString().equals(board[x + 1][y + 2].substring(1))) {
            String piece = board[x + 1][y + 2];
            doMove(new Point(x + 1, y + 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y + 2));
            undoMove(piece);
        }
        if (x > 0 && y < 6 && !isEmpty(x - 1, y + 2) && !getPlayer().toString().equals(board[x - 1][y + 2].substring(1))) {
            String piece = board[x - 1][y + 2];
            doMove(new Point(x - 1, y + 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y + 2));
            undoMove(piece);
        }
        if (x < 6 && y > 0 && !isEmpty(x + 2, y - 1) && !getPlayer().toString().equals(board[x + 2][y - 1].substring(1))) {
            String piece = board[x + 2][y - 1];
            doMove(new Point(x + 2, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 2, y - 1));
            undoMove(piece);
        }
        if (x < 6 && y < 7 && !isEmpty(x + 2, y + 1) && !getPlayer().toString().equals(board[x + 2][y + 1].substring(1))) {
            String piece = board[x + 2][y + 1];
            doMove(new Point(x + 2, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 2, y + 1));
            undoMove(piece);
        }
        if (x < 7 && y > 1 && !isEmpty(x + 1, y - 2) && !getPlayer().toString().equals(board[x + 1][y - 2].substring(1))) {
            String piece = board[x + 1][y - 2];
            doMove(new Point(x + 1, y - 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y - 2));
            undoMove(piece);
        }
        if (x > 0 && y > 1 && !isEmpty(x - 1, y - 2) && !getPlayer().toString().equals(board[x - 1][y - 2].substring(1))) {
            String piece = board[x - 1][y - 2];
            doMove(new Point(x - 1, y - 2));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y - 2));
            undoMove(piece);
        }
        if (x > 1 && y > 0 && !isEmpty(x - 2, y - 1) && !getPlayer().toString().equals(board[x - 2][y - 1].substring(1))) {
            String piece = board[x - 2][y - 1];
            doMove(new Point(x - 2, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 2, y - 1));
            undoMove(piece);
        }
        if (x > 1 && y < 7 && !isEmpty(x - 2, y + 1) && !getPlayer().toString().equals(board[x - 2][y + 1].substring(1))) {
            String piece = board[x - 2][y + 1];
            doMove(new Point(x - 2, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 2, y + 1));
            undoMove(piece);
        }

        return moves;
    }
}
