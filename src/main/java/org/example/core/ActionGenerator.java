package org.example.core;

/**
 * Abstração para gerar movimentos.
 * Isso foi feito para que a interface para geração de movimentos pelo bot
 * seja exatamente a mesma que a interface para geração de movimentos pelo
 * jogador humano.
 *
 * Fazendo com que o bot seja um jogador como qualquer outro, podemos
 * reaproveitar o código do jogo para jogar contra o bot.
 *
 * Ou podemos até mesmo, no futuro, fazer com que o bot jogue contra outro
 * bot.
 *
 * Ou player contra player sem grandes dificuldades
 */
public interface ActionGenerator {
    /**
     * Gera um movimento para o jogador `player` considerando a situação
     * @param board é o tabuleiro atual
     * @param player é o jogador que deve gerar o movimento
     * @return o movimento gerado
     */
    PlayerAction generateAction(Board board, Player player);
}
