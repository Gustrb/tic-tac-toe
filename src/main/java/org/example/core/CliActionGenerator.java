package org.example.core;

import java.util.Scanner;

/**
 * É o action generator que gera a ação do jogador a partir de uma entrada do
 * terminal.
 */
public class CliActionGenerator implements ActionGenerator {
    @Override
    public PlayerAction generateAction(Board board, Player player) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Digite a linha e a coluna da jogada:");

            int row = scanner.nextInt();
            int column = scanner.nextInt();
            // se não passar em alguma validaçao, vai lançar uma exceção
            // e cair no catch, fazendo com queo jogador tenha que digitar de novo
            // pois estamos em um loop infinito (recursivo).
            validateAction(board, row, column);

            return new PlayerAction(player, row, column);
        } catch (Exception e) {
            System.out.println("Jogada inválida");

            // re-executa a função, pois não retornou nada
            return generateAction(board, player);
        }
    }

    /**
     * Valida se a ação é válida. Uma ação é válida se:
     * - a linha está entre 0 e 2
     * - a coluna está entre 0 e 2
     * - a célula está vazia
     *
     * @param board a situação atual do tabuleiro
     * @param row a linha da ação
     * @param column a coluna da ação
     */
    private void validateAction(Board board, int row, int column) {
        if (row < 0 || row > 2) {
            throw new IllegalArgumentException("Linha inválida");
        }

        if (column < 0 || column > 2) {
            throw new IllegalArgumentException("Coluna inválida");
        }

        if (board.getBoardCell(row, column) != BoardCell.EMPTY) {
            throw new IllegalArgumentException("Célula já ocupada");
        }
    }
}
