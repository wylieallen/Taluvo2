package taluvo.game.hex.deck;

import taluvo.util.RandomNumbers;
import taluvo.game.hex.Direction;
import taluvo.game.hex.Terrain;
import taluvo.game.hex.Tile;

public class InfiniteDeck implements Deck
{
    private Tile tile;
    private Direction orientation;

    public InfiniteDeck()
    {
        this.orientation = Direction.NORTHEAST;
        this.tile = new Tile(orientation, getRandomNonVolcano(), getRandomNonVolcano());
    }

    public boolean isEmpty() { return false; }
    public Tile.Entry[] peek() { return tile.getHexes(); }
    public Tile.Entry[] pop()
    {
        Tile tile = this.tile;
        this.tile = new Tile(orientation, getRandomNonVolcano(), getRandomNonVolcano());
        return tile.getHexes();
    }

    public Terrain getRandomNonVolcano()
    {
        return Terrain.values()[RandomNumbers.nextInt(4) + 2];
    }

    public void rotateLeft()
    {
        orientation = orientation.rotateLeft();
        tile.rotateLeft();
    }

    public void rotateRight()
    {
        orientation = orientation.rotateRight();
        tile.rotateRight();
    }
}
