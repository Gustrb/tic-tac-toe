package org.example.core;

/**
 * Representa a únidade atômica do tabuleiro.
 * Podendo ter três estados: vazio, X ou O.
 */
public enum BoardCell {
    EMPTY, X, O;

    /**
     * Retorna a representação em string da célula. (útil pra não ter que ficar fazendo if)
     *
     * @return a representação em string da célula, se for vazia retorna " " (espaço) se não retorna o nome da célula
     */
    @Override
    public String toString() {
        if (this == EMPTY) {
            return " ";
        } else {
            return this.name();
        }
    }
}
