package org.example.core;

/**
 * Board é a classe que representa o estado atual do tabuleiro.
 * Ela é responsável por verificar se o jogo acabou, se alguém ganhou, se deu velha, etc.
 *
 * Ela também é responsável por aplicar uma ação no tabuleiro.
 * Ela é, nada além de que um wrapper para uma matriz de BoardCell.
 * Porém capaz de nos dar informações sobre o estado atual do jogo.
 */
public class Board {
    /**
     * Tamanho do tabuleiro, jogos da velha tradicionais são uma matriz quadrada 3x3
     */
    private static final int BOARD_SIZE = 3;
    /**
     * Matriz de BoardCell que representa o tabuleiro
     */
    private final BoardCell[][] boardCells;

    public Board() {
        boardCells = new BoardCell[BOARD_SIZE][BOARD_SIZE];

        // Aqui inicializamos o tabuleiro com todas as células vazias
        for (var row = 0; row < BOARD_SIZE; row++) {
            for (var column = 0; column < BOARD_SIZE; column++) {
                boardCells[row][column] = BoardCell.EMPTY;
            }
        }
    }

    @Override
    public String toString() {
        // A ideia desse override do método toString é apenas para facilitar a visualização do tabuleiro
        // ou seja, podemos pensar como o tabuleiro stringificado, sem precisarmos olhar intermanete para a matriz
        // Usando um string builder para facilitar a concatenação de strings
        StringBuilder stringBuilder = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            // borda superior
            stringBuilder.append("|");
            for (int column = 0; column < BOARD_SIZE; column++) {
                stringBuilder.append(boardCells[row][column].toString());
                // borda lateral
                stringBuilder.append("|");
            }
            // separador de linhas
            stringBuilder.append("\n");
        }

        // efetivamente retorna o tabuleiro stringificado
        return stringBuilder.toString();
    }

    /**
     * @return o tamanho do tabuleiro, isso poderia ser hardcoded, mas é melhor ter um método para isso
     */
    public int getBoardSize() {
        return BOARD_SIZE;
    }

    /**
     * Simplesmente seta uma célula do para um valor informado
     * TODO: Converter em um objeto do tipo Move ao invés de receber linha e coluna separadamente
     * @param row linha
     * @param column coluna
     * @param boardCell valor
     */
    public void setCell(int row, int column, BoardCell boardCell) {
        boardCells[row][column] = boardCell;
    }

    /**
     * Valida se uma célula está vazia
     * @param row linha
     * @param column coluna
     * @return true se a célula está vazia, false caso contrário
     */
    public boolean isCellEmpty(int row, int column) {
        return boardCells[row][column] == BoardCell.EMPTY;
    }

    /**
     * Existem três formas de acabar o jogo:
     * - O tabuleiro está cheio
     * - O jogador X ganhou
     * - O jogador O ganhou
     *
     * @return true se o jogo acabou, false caso contrário
     */
    public boolean isGameOver() {
        return isBoardFull() || isWinner(BoardCell.X) || isWinner(BoardCell.O);
    }

    /**
     * Verifica se o tabuleiro está cheio, ou seja, se todas as células estão ocupadas (diferente de EMPTY)
     *
     * @return true se o tabuleiro está cheio, false caso contrário
     */
    public boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int column = 0; column < BOARD_SIZE; column++) {
                if (boardCells[row][column] == BoardCell.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Verifica se um jogador ganhou o jogo
     * @param boardCell o jogador que queremos verificar se ganhou
     * @return true se o jogador ganhou, false caso contrário
     */
    public boolean isWinner(BoardCell boardCell) {
        return isWinnerInRows(boardCell) || isWinnerInColumns(boardCell) || isWinnerInDiagonals(boardCell);
    }

    /**
     * Verifica se um jogador ganhou nas linhas, ou seja, se ele tem três células em sequência na mesma linha
     *
     * @param boardCell o jogador que queremos verificar se ganhou
     * @return true se o jogador ganhou nas linhas, false caso contrário
     */
    private boolean isWinnerInRows(BoardCell boardCell) {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (boardCells[row][0] == boardCell && boardCells[row][1] == boardCell && boardCells[row][2] == boardCell) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um jogador ganhou nas colunas, ou seja, se ele tem três células em sequência na mesma coluna
     * @param boardCell o jogador que queremos verificar se ganhou
     * @return true se o jogador ganhou nas colunas, false caso contrário
     */
    private boolean isWinnerInColumns(BoardCell boardCell) {
        for (int column = 0; column < BOARD_SIZE; column++) {
            if (boardCells[0][column] == boardCell && boardCells[1][column] == boardCell && boardCells[2][column] == boardCell) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica se um jogador ganhou nas diagonais, ou seja, se ele tem três células em sequência na mesma diagonal
     * TODO: Este método acabaria quebrando se aumentássemos o tamanho do tabuleiro, talvez seja melhor encontrar a diagonal
     *       da forma correta, ao invés de hardcodar
     * @param boardCell o jogador que queremos verificar se ganhou
     * @return true se o jogador ganhou nas diagonais, false caso contrário
     */
    private boolean isWinnerInDiagonals(BoardCell boardCell) {
        return boardCells[0][0] == boardCell && boardCells[1][1] == boardCell && boardCells[2][2] == boardCell ||
                boardCells[0][2] == boardCell && boardCells[1][1] == boardCell && boardCells[2][0] == boardCell;
    }

    /**
     * Verifica se o jogo deu velha, ou seja, se o tabuleiro está cheio e ninguém ganhou
     *
     * @return true se o jogo deu velha, false caso contrário
     */
    public boolean isTie() {
        return isBoardFull() && !isWinner(BoardCell.X) && !isWinner(BoardCell.O);
    }

    /**
     * Retorna o valor de uma célula do tabuleiro
     * @param row linha
     * @param column coluna
     * @return o valor da célula
     */
    public BoardCell getBoardCell(int row, int column) {
        return boardCells[row][column];
    }

    /**
     * Aplica uma ação no tabuleiro, ou seja, seta uma célula para um valor
     * @param playerAction a ação que deve ser aplicada
     */
    public void applyAction(PlayerAction playerAction) {
        boardCells[playerAction.getRow()][playerAction.getColumn()] = playerAction.getPlayerWhoActed().getBoardCell();
    }
}
