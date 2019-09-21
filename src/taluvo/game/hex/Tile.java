package taluvo.game.hex;

public class Tile
{
    private Direction base;
    private final Terrain[] terrains;

    public Tile(Direction base, Terrain... terrains)
    {
        this.base = base;
        this.terrains = terrains;
    }

    public void rotateLeft() { base = base.rotateLeft(); }
    public void rotateRight() { base = base.rotateRight(); }

    public Entry[] getHexes()
    {
        Entry[] hexes = new Entry[terrains.length + 1];
        Direction hexPosition = base;

        hexes[0] = new Entry(0, 0, Terrain.VOLCANO);

        int i = 1;
        for(Terrain terrain : terrains)
        {
            hexes[i] = new Entry(hexPosition.x, hexPosition.y, terrain);
            ++i;
            hexPosition = hexPosition.rotateRight();
        }

        return hexes;
    }

    public static class Entry
    {
        public final Terrain terrain;
        public final int x, y;

        public Entry(int x, int y, Terrain terrain)
        {
            this.x = x;
            this.y = y;
            this.terrain = terrain;
        }
    }
}
