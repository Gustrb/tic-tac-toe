package org.example.core;

/**
 * Classe que gera a ação do jogador baseado no algoritmo MiniMax.
 * O algoritmo MiniMax é um algoritmo de busca em árvore que busca o melhor movimento para o jogador.
 * Ele funciona da seguinte forma:
 * 1. Se o jogo acabou, retorna o valor do estado do jogo
 * 2. Se o jogador atual é o jogador que queremos maximizar, retorna o maior valor dos filhos
 * 3. Se o jogador atual é o jogador que queremos minimizar, retorna o menor valor dos filhos
 *
 * Definimos o valor do estado do jogo da seguinte forma:
 * 1. Se o jogador X ganhou, retorna -10
 * 2. Se o jogador O ganhou, retorna 10
 * 3. Se deu velha, retorna 0
 */
public class MiniMaxActionGenerator implements ActionGenerator {
    /**
     * O estado do jogo.
     */
    private final GameState gameState;

    public MiniMaxActionGenerator(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public PlayerAction generateAction(Board board, Player player) {
        // pegamos o melhor movimento possível para o jogador atual e retornamos ele.
        final var move = getBestMove(board, player);
        return new PlayerAction(player, move.line(), move.col());
    }

    /**
     * Retorna o melhor movimento possível para o jogador atual.
     * Pra isso nós iteramos por todas as células vazias do tabuleiro, simulamos que o jogador atual
     * jogou nessa célula e chamamos o MiniMax para o jogador adversário.
     * Depois disso, desfazemos a jogada e comparamos o valor retornado pelo MiniMax com o melhor valor
     * que já encontramos até agora.
     * Se o valor retornado pelo MiniMax for maior que o melhor valor que já encontramos até agora,
     * atualizamos o melhor valor e o melhor movimento.
     * Depois de iterar por todas as células vazias, retornamos o melhor movimento.
     *
     * @param board é o tabuleiro atual
     * @param player é o jogador atual
     * @return o melhor movimento possível para o jogador atual
     */
    private Move getBestMove(Board board, Player player) {
        var bestScore = Integer.MIN_VALUE;
        Move bestMove = null;
        for (var row = 0; row < board.getBoardSize(); ++row) {
            for (var col = 0; col < board.getBoardSize(); ++col) {
                if (board.isCellEmpty(row, col)) {
                    // emula a jogada
                    board.setCell(row, col, player.getBoardCell());
                    // vê qual o melhor score possível considerando que a jogada foi feita
                    var score = miniMax(board, gameState.getOpponent(player), false);
                    // desfaz a jogada
                    board.setCell(row, col, BoardCell.EMPTY);
                    // se o score for melhor que o melhor score que já encontramos até agora,
                    // atualizamos o melhor score e o melhor movimento
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Move(row, col, score);
                    }
                }
            }
        }

        // retorna o melhor movimento
        return bestMove;
    }

    /**
     * Retorna o valor do estado do jogo.
     * O valor do estado do jogo é definido da seguinte forma:
     * 1. Se o jogador X ganhou, retorna -10
     * 2. Se o jogador O ganhou, retorna 10
     * 3. Se deu velha, retorna 0
     *
     * Se o jogo não acabou, retorna o melhor valor possível para o jogador atual.
     * Pra isso, iteramos por todas as células vazias do tabuleiro, simulamos que o jogador atual
     * jogou nessa célula e chamamos o MiniMax para o jogador adversário.
     *
     * @param board é o tabuleiro atual
     * @param player é o jogador atual
     * @param isMax é true se o jogador atual é o jogador que queremos maximizar e false caso contrário
     * @return o valor do estado do jogo
     */
    private int miniMax(Board board, Player player, boolean isMax) {
        // se o jogo acabou, retornamos o valor do estado do jogo
        if (board.isWinner(gameState.getXPlayer().getBoardCell())) {
            return -10;
        }
        if (board.isWinner(gameState.getOPlayer().getBoardCell())) {
            return 10;
        }
        if (board.isBoardFull()) {
            return 0;
        }

        if (isMax) {
            // se o jogador atual é o jogador que queremos maximizar, retornamos o maior valor dos filhos
            var bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < board.getBoardSize(); ++row) {
                for (int col = 0; col < board.getBoardSize(); ++col) {
                    if (board.isCellEmpty(row, col)) {
                        // emula a jogada
                        board.setCell(row, col, player.getBoardCell());
                        // vê qual o melhor score possível considerando que a jogada foi feita
                        bestScore = Math.max(bestScore, miniMax(board, gameState.getOpponent(player), false));
                        // desfaz a jogada
                        board.setCell(row, col, BoardCell.EMPTY);
                    }
                }
            }

            return bestScore;
        } else {
            //aqui é a mesma coisa, mas agora queremos minimizar
            var bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < board.getBoardSize(); ++row) {
                for (int col = 0; col < board.getBoardSize(); ++col) {
                    if (board.isCellEmpty(row, col)) {
                        board.setCell(row, col, player.getBoardCell());
                        bestScore = Math.min(bestScore, miniMax(board, gameState.getOpponent(player), true));
                        board.setCell(row, col, BoardCell.EMPTY);
                    }
                }
            }

            return bestScore;
        }
    }
}
