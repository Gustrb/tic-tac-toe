package org.example;

import org.example.core.Game;
import org.example.core.GameState;

public class Main {
    public static void main(String[] args) {
        // iniciamos o estado do jogo
        final var gameState = new GameState();
        final var game = new Game(gameState);

        // iniciamos o loop principal do jogo
        game.mainGameLoop();
    }
}
