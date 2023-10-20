package org.example.core;

/**
 * Representa o jogador X.
 */
public class XPlayer implements Player {
    private final ActionGenerator actionGenerator;

    public XPlayer(ActionGenerator actionGenerator) {
        this.actionGenerator = actionGenerator;
    }

    @Override
    public BoardCell getBoardCell() {
        return BoardCell.X;
    }

    @Override
    public PlayerAction getPlayerAction(Board board) {
        return actionGenerator.generateAction(board, this);
    }
}
