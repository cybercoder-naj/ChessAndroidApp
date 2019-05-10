package com.nishant.games.chess.backend.game;

public enum PieceConstant {
    PAWN("P"), ROOK("R"), KNIGHT("N"), BISHOP("B"), QUEEN("Q"), KING("K"), EMPTY("-");

    String piece;

    PieceConstant(String piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        return piece;
    }
}
