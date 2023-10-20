package org.example.core;

/**
 * Representa uma ação tomada por um objeto {@link Player}.
 * Note: Um player pode ser um humano ou um algoritmo.
 *
 * Essa classe é usada para representar a ação tomada por um jogador.
 * A ideia é que seja uma dummy class imuável, ou seja, uma classe que
 * não pode ser alterada depois de criada.
 */
public class PlayerAction {
    /**
     * O jogador que tomou (ou vai tomar) a ação
     */
    private final Player playerWhoActed;
    /**
     * A linha onde o jogador jogou (ou vai jogar)
     */
    private final int row;
    /**
     * A coluna onde o jogador jogou (ou vai jogar)
     */
    private final int column;

    public PlayerAction(Player playerWhoActed, int row, int column) {
        this.playerWhoActed = playerWhoActed;
        this.row = row;
        this.column = column;
    }

    /**
     * @return o jogador que tomou (ou vai tomar) a ação
     */
    public Player getPlayerWhoActed() {
        return playerWhoActed;
    }

    /**
     * @return a linha onde o jogador jogou (ou vai jogar)
     */
    public int getRow() {
        return row;
    }

    /**
     * @return a coluna onde o jogador jogou (ou vai jogar)
     */
    public int getColumn() {
        return column;
    }
}
