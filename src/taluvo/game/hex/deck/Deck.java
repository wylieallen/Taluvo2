package taluvo.game.hex.deck;

import taluvo.game.hex.Tile;

public interface Deck
{
    boolean isEmpty();
    Tile.Entry[] pop();
    Tile.Entry[] peek();
    void rotateLeft();
    void rotateRight();
}
