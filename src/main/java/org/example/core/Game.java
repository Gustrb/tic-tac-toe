package org.example.core;

/**
 * Classe que representa o jogo.
 * Aqui nós temos o loop principal do jogo, que é onde o jogo efetivamente acontece.
 */
public class Game {
    /**
     * O estado do jogo.
     * Contém os jogadores
     */
    private final GameState gameState;
    /**
     * O tabuleiro do jogo.
     */
    private final Board board;

    public Game(GameState gameState) {
        this.board = new Board();
        this.gameState = gameState;
    }

    /**
     * Método principal do jogo.
     * Aqui nós mostramos o tabuleiro, pedimos a ação do jogador atual e aplicamos a ação no tabuleiro.
     * Depois disso, verificamos se o jogo acabou e, se não, passamos a vez para o próximo jogador.
     */
    public void mainGameLoop() {
        Player currentPlayer = getRandomPlayer();
        while (true) {
            System.out.println("Vez do jogador " + currentPlayer.getBoardCell() + ":");
            System.out.println(board);
            PlayerAction playerAction = currentPlayer.getPlayerAction(board);
            board.applyAction(playerAction);

            // Se a ação do player atual fez com que o jogo acabasse, nós mostramos o resultado e saímos do loop.
            if (board.isGameOver()) {
                // isGameOver() retorna true se o jogo acabou por vitória ou empate.
                // portanto, precisamos desse if para desambiguar.
                if (board.isTie()) {
                    System.out.println("Fim de jogo! Empate!");
                    break;
                }

                // se não foi velha e o jogo acabou, então alguém venceu.
                System.out.println("Fim de jogo! O jogador " + currentPlayer.getBoardCell() + " venceu!");
                break;
            }

            // se o jogo não acabou, passamos a vez para o próximo jogador.
            currentPlayer = gameState.getOpponent(currentPlayer);
            clearScreen();
        }
    }

    /**
     * Sorteia o jogador que irá começar a partida.
     *
     * @return O jogador que irá começar a partida.
     */
    public Player getRandomPlayer() {
        return Math.random() < 0.5 ? gameState.getXPlayer() : gameState.getOPlayer();
    }

    /**
     * Método utilizado para limpar a tela do terminal.
     * Acho que só funciona no Linux. (:P)
     */
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
