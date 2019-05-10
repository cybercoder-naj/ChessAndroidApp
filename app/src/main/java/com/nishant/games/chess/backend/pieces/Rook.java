package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.PieceConstant;
import com.nishant.games.chess.backend.game.Player;

import java.util.ArrayList;
import java.util.List;

import static com.nishant.games.chess.backend.game.Board.board;
import static com.nishant.games.chess.backend.game.Board.isEmpty;
import static com.nishant.games.chess.backend.game.PieceConstant.EMPTY;

public final class Rook extends Pieces {

    private boolean hasMoved;

    public Rook(Point currentPoint, Player player) {
        super(currentPoint, player, PieceConstant.ROOK);
        hasMoved = false;
    }

    boolean isHasMoved() {
        return !hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public List<Point> getMoves() {
        int x = getCurrentPoint().x, y = getCurrentPoint().y;
        List<Point> moves = new ArrayList<>();
        while (x < 7 && isEmpty(x + 1, y)) {
            doMove(new Point(x + 1, y));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y));
            undoMove(EMPTY.toString());
            x++;
        }

        if (x < 7 && !getPlayer().toString().equals(board[x + 1][y].substring(1))) {
            String piece = board[x + 1][y];
            doMove(new Point(x + 1, y));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x + 1, y));
            undoMove(piece);
        }

        x = getCurrentPoint().x;

        while (x > 0 && isEmpty(x - 1, y)) {
            doMove(new Point(x - 1, y));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y));
            undoMove(EMPTY.toString());
            x--;
        }

        if (x > 0 && !getPlayer().toString().equals(board[x - 1][y].substring(1))) {
            String piece = board[x - 1][y];
            doMove(new Point(x - 1, y));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x - 1, y));
            undoMove(piece);
        }

        x = getCurrentPoint().x;

        while (y < 7 && isEmpty(x, y + 1)) {
            doMove(new Point(x, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x, y + 1));
            undoMove(EMPTY.toString());
            y++;
        }

        if (y < 7 && !getPlayer().toString().equals(board[x][y + 1].substring(1))) {
            String piece = board[x][y + 1];
            doMove(new Point(x, y + 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x, y + 1));
            undoMove(piece);
        }

        y = getCurrentPoint().y;

        while (y > 0 && isEmpty(x, y - 1)) {
            doMove(new Point(x, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x, y - 1));
            undoMove(EMPTY.toString());
            y--;
        }

        if (y > 0 && !getPlayer().toString().equals(board[x][y - 1].substring(1))) {
            String piece = board[x][y - 1];
            doMove(new Point(x, y - 1));
            if (!getPlayer().getKing().isCheck()) moves.add(new Point(x, y - 1));
            undoMove(piece);
        }

        return moves;
    }
}
