package org.example.core;

/**
 * Player é uma interface que representa um jogador do jogo da velha.
 * Utilizamos uma interface para que possamos ter o playerX e o playerY em suas classes separadas.
 * Talvez, no futuro seja melhor só ter uma classe Player e passar um parâmetro para o construtor
 *
 * Achei essa abstração meio chata de lidar, mas estou com preguiça de mudar agora
 */
public interface Player {
    /**
     * @return a célula que o jogador representa
     */
    BoardCell getBoardCell();

    /**
     * Gera uma ação para o jogador
     * @param board é o tabuleiro atual
     * @return a ação gerada (pode ser ótima ou não)
     */
    PlayerAction getPlayerAction(Board board);
}
