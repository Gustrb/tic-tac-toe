package org.example.core;

/**
 * Representa o jogador O.
 */
public class OPlayer implements Player {
    private final ActionGenerator actionGenerator;

    public OPlayer(ActionGenerator actionGenerator) {
        this.actionGenerator = actionGenerator;
    }

    @Override
    public BoardCell getBoardCell() {
        return BoardCell.O;
    }

    @Override
    public PlayerAction getPlayerAction(Board board) {
        return actionGenerator.generateAction(board, this);
    }
}
