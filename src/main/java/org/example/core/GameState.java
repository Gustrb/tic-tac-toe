package org.example.core;

/**
 * Classe que representa o estado do jogo.
 * Aqui nós só mantemos os jogadores.
 */
public class GameState {
    /**
     * Jogadores do jogo.
     */
    private final Player xPlayer;
    private final Player oPlayer;

    /**
     * Aqui definimos quais jogadores serão usados.
     * Nesse caso, o jogador X será um jogador humano e o jogador O será um jogador de IA.
     */
    public GameState() {
        this.xPlayer = new XPlayer(new CliActionGenerator());
        this.oPlayer = new OPlayer(new MiniMaxActionGenerator(this));
    }

    /**
     * Retorna o oponente do jogador passado como parâmetro.
     * @param player o jogador que queremos saber o oponente
     * @return o oponente do jogador passado como parâmetro
     */
    public Player getOpponent(Player player) {
        if (player == xPlayer) {
            return oPlayer;
        }

        return xPlayer;
    }

    /**
     * @return o jogador X
     */
    public Player getXPlayer() {
        return xPlayer;
    }

    /**
     * @return o jogador O
     */
    public Player getOPlayer() {
        return oPlayer;
    }
}
