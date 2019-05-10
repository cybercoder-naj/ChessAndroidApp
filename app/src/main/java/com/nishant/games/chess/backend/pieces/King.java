package com.nishant.games.chess.backend.pieces;

import android.graphics.Point;

import com.nishant.games.chess.backend.game.Player;
import com.nishant.games.chess.backend.game.PlayerConstant;

import java.util.ArrayList;
import java.util.List;

import static com.nishant.games.chess.backend.game.Board.board;
import static com.nishant.games.chess.backend.game.Board.isEmpty;
import static com.nishant.games.chess.backend.game.PieceConstant.*;

public final class King extends Pieces {
    private boolean hasMoved;

    public King(Point currentPoint, Player player) {
        super(currentPoint, player, KING);
        hasMoved = false;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    @Override
    public List<Point> getMoves() {
        int x = getCurrentPoint().x, y = getCurrentPoint().y;
        List<Point> moves = new ArrayList<>();
        if (x < 7 && isEmpty(x + 1, y)) {
            doMove(new Point(x + 1, y));
            if (!isCheck()) moves.add(new Point(x + 1, y));
            undoMove(EMPTY.toString());
        }
        if (x > 0 && isEmpty(x - 1, y)) {
            doMove(new Point(x - 1, y));
            if (!isCheck()) moves.add(new Point(x - 1, y));
            undoMove(EMPTY.toString());
        }
        if (y < 7 && isEmpty(x, y + 1)) {
            doMove(new Point(x, y + 1));
            if (!isCheck()) moves.add(new Point(x, y + 1));
            undoMove(EMPTY.toString());
            if (getPlayerConstant().equals(PlayerConstant.PLAYER_1)) {
                if (!isCheck() && getCurrentPoint().equals(7, 4) && isEmpty(7, 6)
                        && !hasMoved && board[7][7].equals(ROOK.toString() + PlayerConstant.PLAYER_1)) {
                    Rook rook = (Rook)getPlayer().getPiece(new Point(7, 7));
                    if(rook.isHasMoved()) {
                        doMove(new Point(7, 6));
                        rook.doMove(new Point(7,5));
                    }
                    if(!isCheck()) moves.add(new Point(7, 6));
                    undoMove(EMPTY.toString());
                    rook.undoMove(EMPTY.toString());
                }
            }
            else {
                if(!isCheck() && getCurrentPoint().equals(0, 4) && isEmpty(0, 6)
                        && !hasMoved && board[0][7].equals(ROOK.toString() + PlayerConstant.PLAYER_2)) {
                    Rook rook = (Rook)getPlayer().getPiece(new Point(0, 7));
                    if(rook.isHasMoved()) {
                        doMove(new Point(0, 6));
                        rook.doMove(new Point(0,5));
                    }
                    if(!isCheck()) moves.add(new Point(0, 6));
                    undoMove(EMPTY.toString());
                    rook.undoMove(EMPTY.toString());
                }
            }
        }
        if (y > 0 && isEmpty(x, y - 1)) {
            doMove(new Point(x, y - 1));
            if (!isCheck()) moves.add(new Point(x, y - 1));
            undoMove(EMPTY.toString());
            if (getPlayerConstant().equals(PlayerConstant.PLAYER_1)) {
                if (!isCheck() && getCurrentPoint().equals(7, 4) && isEmpty(7, 2) && isEmpty(7, 1)
                        && !hasMoved && board[7][0].equals(ROOK.toString() + PlayerConstant.PLAYER_1)) {
                    Rook rook = (Rook)getPlayer().getPiece(new Point(7, 0));
                    if(rook.isHasMoved()) {
                        doMove(new Point(7, 2));
                        rook.doMove(new Point(7,3));
                    }
                    if(!isCheck()) moves.add(new Point(7, 2));
                    undoMove(EMPTY.toString());
                    rook.undoMove(EMPTY.toString());
                }
            }
            else {
                if(!isCheck() && getCurrentPoint().equals(0, 4) && isEmpty(0, 2) && isEmpty(0, 1)
                        && !hasMoved && board[0][0].equals(ROOK.toString() + PlayerConstant.PLAYER_2)) {
                    Rook rook = (Rook)getPlayer().getPiece(new Point(0, 0));
                    if(rook.isHasMoved()) {
                        doMove(new Point(0, 2));
                        rook.doMove(new Point(0,3));
                    }
                    if(!isCheck()) moves.add(new Point(0, 2));
                    undoMove(EMPTY.toString());
                    rook.undoMove(EMPTY.toString());
                }
            }
        }
        if (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            doMove(new Point(x + 1, y + 1));
            if (!isCheck()) moves.add(new Point(x + 1, y + 1));
            undoMove(EMPTY.toString());
        }
        if (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            doMove(new Point(x + 1, y - 1));
            if (!isCheck()) moves.add(new Point(x + 1, y - 1));
            undoMove(EMPTY.toString());
        }
        if (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            doMove(new Point(x - 1, y + 1));
            if (!isCheck()) moves.add(new Point(x - 1, y + 1));
            undoMove(EMPTY.toString());
        }
        if (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            doMove(new Point(x - 1, y - 1));
            if (!isCheck()) moves.add(new Point(x - 1, y - 1));
            undoMove(EMPTY.toString());
        }

        if (x < 7 && !isEmpty(x + 1, y) && !getPlayer().toString().equals(board[x + 1][y].substring(1))) {
            String piece = board[x + 1][y];
            doMove(new Point(x + 1, y));
            if (!isCheck()) moves.add(new Point(x + 1, y));
            undoMove(piece);
        }
        if (x > 0 && !isEmpty(x - 1, y) && !getPlayer().toString().equals(board[x - 1][y].substring(1))) {
            String piece = board[x - 1][y];
            doMove(new Point(x - 1, y));
            if (!isCheck()) moves.add(new Point(x - 1, y));
            undoMove(piece);
        }
        if (y < 7 && !isEmpty(x, y + 1) && !getPlayer().toString().equals(board[x][y + 1].substring(1))) {
            String piece = board[x][y + 1];
            doMove(new Point(x, y + 1));
            if (!isCheck()) moves.add(new Point(x, y + 1));
            undoMove(piece);
        }
        if (y > 0 && !isEmpty(x, y - 1) && !getPlayer().toString().equals(board[x][y - 1].substring(1))) {
            String piece = board[x][y - 1];
            doMove(new Point(x, y - 1));
            if (!isCheck()) moves.add(new Point(x, y - 1));
            undoMove(piece);
        }
        if (x < 7 && y < 7 && !isEmpty(x + 1, y + 1) && !getPlayer().toString().equals(board[x + 1][y + 1].substring(1))) {
            String piece = board[x + 1][y + 1];
            doMove(new Point(x + 1, y + 1));
            if (!isCheck()) moves.add(new Point(x + 1, y + 1));
            undoMove(piece);
        }
        if (x < 7 && y > 0 && !isEmpty(x + 1, y - 1) && !getPlayer().toString().equals(board[x + 1][y - 1].substring(1))) {
            String piece = board[x + 1][y - 1];
            doMove(new Point(x + 1, y - 1));
            if (!isCheck()) moves.add(new Point(x + 1, y - 1));
            undoMove(piece);
        }
        if (x > 0 && y < 7 && !isEmpty(x - 1, y + 1) && !getPlayer().toString().equals(board[x - 1][y + 1].substring(1))) {
            String piece = board[x - 1][y + 1];
            doMove(new Point(x - 1, y + 1));
            if (!isCheck()) moves.add(new Point(x - 1, y + 1));
            undoMove(piece);
        }
        if (x > 0 && y > 0 && !isEmpty(x - 1, y - 1) && !getPlayer().toString().equals(board[x - 1][y - 1].substring(1))) {
            String piece = board[x - 1][y - 1];
            doMove(new Point(x - 1, y - 1));
            if (!isCheck()) moves.add(new Point(x - 1, y - 1));
            undoMove(piece);
        }
        return moves;
    }

    public boolean isCheck() {
        int x = getCurrentPoint().x, y = getCurrentPoint().y;

        while (x < 7 && y < 7 && isEmpty(x + 1, y + 1)) {
            x++;
            y++;
        }

        if (x < 7 && y < 7 && !getPlayerConstant().toString().equals(board[x + 1][y + 1].substring(1))
                && (board[x + 1][y + 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x + 1][y + 1].substring(0, 1).equals(BISHOP.toString()))) return true;

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x < 7 && y > 0 && isEmpty(x + 1, y - 1)) {
            x++;
            y--;
        }

        if (x < 7 && y > 0 && !getPlayerConstant().toString().equals(board[x + 1][y - 1].substring(1))
                && (board[x + 1][y - 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x + 1][y - 1].substring(0, 1).equals(BISHOP.toString()))) return true;

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x > 0 && y > 0 && isEmpty(x - 1, y - 1)) {
            x--;
            y--;
        }

        if (x > 0 && y > 0 && !getPlayerConstant().toString().equals(board[x - 1][y - 1].substring(1))
                && (board[x - 1][y - 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x - 1][y - 1].substring(0, 1).equals(BISHOP.toString()))) return true;

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x > 0 && y < 7 && isEmpty(x - 1, y + 1)) {
            x--;
            y++;
        }

        if (x > 0 && y < 7 && !getPlayerConstant().toString().equals(board[x - 1][y + 1].substring(1))
                && (board[x - 1][y + 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x - 1][y + 1].substring(0, 1).equals(BISHOP.toString()))) return true;

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        while (x < 7 && isEmpty(x + 1, y)) {
            x++;
        }

        if (x < 7 && !getPlayerConstant().toString().equals(board[x + 1][y].substring(1))
                && (board[x + 1][y].substring(0, 1).equals(QUEEN.toString()) ||
                board[x + 1][y].substring(0, 1).equals(ROOK.toString()))) return true;

        x = getCurrentPoint().x;

        while (x > 0 && isEmpty(x - 1, y)) {
            x--;
        }

        if (x > 0 && !getPlayerConstant().toString().equals(board[x - 1][y].substring(1))
                && (board[x - 1][y].substring(0, 1).equals(QUEEN.toString()) ||
                board[x - 1][y].substring(0, 1).equals(ROOK.toString()))) return true;

        x = getCurrentPoint().x;

        while (y < 7 && isEmpty(x, y + 1)) {
            y++;
        }

        if (y < 7 && !getPlayerConstant().toString().equals(board[x][y + 1].substring(1))
                && (board[x][y + 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x][y + 1].substring(0, 1).equals(ROOK.toString()))) return true;

        y = getCurrentPoint().y;

        while (y > 0 && isEmpty(x, y - 1)) {
            y--;
        }

        if (y > 0 && !getPlayerConstant().toString().equals(board[x][y - 1].substring(1))
                && (board[x][y - 1].substring(0, 1).equals(QUEEN.toString()) ||
                board[x][y - 1].substring(0, 1).equals(ROOK.toString()))) return true;

        x = getCurrentPoint().x;
        y = getCurrentPoint().y;

        if (x < 7 && y < 7 && !getPlayerConstant().toString().equals(board[x + 1][y + 1].substring(1))
                && board[x + 1][y + 1].substring(0, 1).equals(KING.toString())) return true;
        if (x > 0 && y < 7 && !getPlayerConstant().toString().equals(board[x - 1][y + 1].substring(1))
                && board[x - 1][y + 1].substring(0, 1).equals(KING.toString())) return true;
        if (x < 7 && y > 0 && !getPlayerConstant().toString().equals(board[x + 1][y - 1].substring(1))
                && board[x + 1][y - 1].substring(0, 1).equals(KING.toString())) return true;
        if (x > 0 && y > 0 && !getPlayerConstant().toString().equals(board[x - 1][y - 1].substring(1))
                && board[x - 1][y - 1].substring(0, 1).equals(KING.toString())) return true;
        if (x < 7 && !getPlayerConstant().toString().equals(board[x + 1][y].substring(1))
                && board[x + 1][y].substring(0, 1).equals(KING.toString())) return true;
        if (x > 0 && !getPlayerConstant().toString().equals(board[x - 1][y].substring(1))
                && board[x - 1][y].substring(0, 1).equals(KING.toString())) return true;
        if (y < 7 && !getPlayerConstant().toString().equals(board[x][y + 1].substring(1))
                && board[x][y + 1].substring(0, 1).equals(KING.toString())) return true;
        if (y > 0 && !getPlayerConstant().toString().equals(board[x][y - 1].substring(1))
                && board[x][y - 1].substring(0, 1).equals(KING.toString())) return true;

        //Checks if knight is in the way of the king.
        if (x < 7 && y < 6 && !getPlayerConstant().toString().equals(board[x + 1][y + 2].substring(1))
                && board[x + 1][y + 2].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x > 0 && y < 6 && !getPlayerConstant().toString().equals(board[x - 1][y + 2].substring(1))
                && board[x - 1][y + 2].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x < 6 && y > 0 && !getPlayerConstant().toString().equals(board[x + 1][y - 1].substring(1))
                && board[x + 1][y - 1].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x < 6 && y < 7 && !getPlayerConstant().toString().equals(board[x + 2][y + 1].substring(1))
                && board[x + 2][y + 1].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x < 7 && y > 1 && !getPlayerConstant().toString().equals(board[x + 1][y - 2].substring(1))
                && board[x + 1][y - 2].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x > 0 && y > 1 && !getPlayerConstant().toString().equals(board[x - 1][y - 2].substring(1))
                && board[x - 1][y - 2].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x > 1 && y > 0 && !getPlayerConstant().toString().equals(board[x - 2][y - 1].substring(1))
                && board[x - 2][y - 1].substring(0, 1).equals(KNIGHT.toString())) return true;
        if (x > 1 && y < 7 && !getPlayerConstant().toString().equals(board[x - 2][y + 1].substring(1))
                && board[x - 2][y + 1].substring(0, 1).equals(KNIGHT.toString())) return true;

        if (getPlayerConstant().toString().equals(PlayerConstant.PLAYER_1.toString())) {
            if (x > 0 && y > 0 && !getPlayerConstant().toString().equals(board[x - 1][y - 1].substring(1))
                    && board[x - 1][y - 1].substring(0, 1).equals(PAWN.toString())) return true;
            return x > 0 && y < 7 && !getPlayerConstant().toString().equals(board[x - 1][y + 1].substring(1))
                    && board[x - 1][y + 1].substring(0, 1).equals(PAWN.toString());
        } else {
            if (x < 7 && y > 0 && !getPlayerConstant().toString().equals(board[x + 1][y - 1].substring(1))
                    && board[x + 1][y - 1].substring(0, 1).equals(PAWN.toString())) return true;
            return x < 7 && y < 7 && !getPlayerConstant().toString().equals(board[x + 1][y + 1].substring(1))
                    && board[x + 1][y + 1].substring(0, 1).equals(PAWN.toString());
        }
    }
}
