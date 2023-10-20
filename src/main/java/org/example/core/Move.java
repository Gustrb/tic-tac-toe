package org.example.core;

/**
 * Representa uma jogada.
 * @param line a linha da jogada
 * @param col a coluna da jogada
 * @param score o score da jogada
 */
public record Move(int line, int col, int score) {
}
