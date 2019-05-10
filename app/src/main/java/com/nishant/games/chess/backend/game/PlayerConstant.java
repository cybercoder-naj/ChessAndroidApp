package com.nishant.games.chess.backend.game;

import org.jetbrains.annotations.NotNull;

public enum PlayerConstant {
    PLAYER_1("1"), PLAYER_2("2");

    String player;

    PlayerConstant(String player) {
        this.player = player;
    }

    @NotNull
    @Override
    public String toString() {
        return player;
    }
}
