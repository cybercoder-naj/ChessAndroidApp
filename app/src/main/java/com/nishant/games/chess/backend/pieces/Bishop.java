package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nishant.games.chess.backend.game.Board.board;
import static com.nishant.games.chess.backend.game.Board.isEmpty;
import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;

public final class Bishop extends Pieces {
    public Bishop(Point currentPoint, Player player) {
        super(currentPoint, player, PieceConstant.BISHOP);
    }

    @Override
    public List<Point> getMoves() {
        List<Point> moves = new ArrayList<>();
        int x = getCurrentPoint().x, y = getCurrentPoint().y;
        while (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            doMove(new Point(x + 1, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y + 1));
            undoMove(EMPTY.toString());
            x++;
            y++;
        }
        if (x < 7 && y < 7 && !getPlayer().toString().equals(board[x + 1][y + 1].substring(1))) {
            String piece = board[x + 1][y + 1];
            doMove(new Point(x + 1, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y + 1));
            undoMove(piece);
        }

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            doMove(new Point(x + 1, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y - 1));
            undoMove(EMPTY.toString());
            x++;
            y--;
        }

        if (x < 7 && y > 0 && !getPlayer().toString().equals(board[x + 1][y - 1].substring(1))) {
            String piece = board[x + 1][y - 1];
            doMove(new Point(x + 1, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y - 1));
            undoMove(piece);
        }

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            doMove(new Point(x - 1, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y + 1));
            undoMove(EMPTY.toString());
            x--;
            y++;
        }

        if (x > 0 && y < 7 && !getPlayer().toString().equals(board[x - 1][y + 1].substring(1))) {
            String piece = board[x - 1][y + 1];
            doMove(new Point(x - 1, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y + 1));
            undoMove(piece);
        }

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            doMove(new Point(x - 1, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y - 1));
            undoMove(EMPTY.toString());
            x--;
            y--;
        }

        if (x > 0 && y > 0 && !getPlayer().toString().equals(board[x - 1][y - 1].substring(1))) {
            String piece = board[x - 1][y - 1];
            doMove(new Point(x - 1, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y - 1));
            undoMove(piece);
        }
        return moves;
    }
}
