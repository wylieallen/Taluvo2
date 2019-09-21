package taluvo.game.hex;

import taluvo.game.Player;

public class Hex
{
    private Player owner;
    private Building building;
    private final Terrain terrain;
    private final int height;
    private final int tileId;

    public Hex(Terrain terrain, int height, int tileId)
    {
        this.terrain = terrain;
        this.height = height;
        this.tileId = tileId;
        this.building = Building.NONE;
        this.owner = Player.NULL;
    }

    public Terrain getTerrain() { return terrain; }
    public Building getBuilding() { return building; }
    public int getHeight() { return height; }
    public int getTileId() { return tileId; }
    public Player getOwner() { return owner; }
    public boolean isBuildable() { return !building.occupiesSpace() && terrain.isBuildable(); }
    public boolean isDestructible() { return !building.isPermanent(); }

    public void build(Player owner, Building building)
    {
        this.owner = owner;
        this.building = building;
    }

    public class View
    {
        public Terrain getTerrain() { return terrain; }
        public Building getBuilding() { return building; }
        public int getHeight() { return height; }
        public int getTileId() { return tileId; }
    }

    public static Hex NULL = new Hex(Terrain.NONE, 0, -1)
    {
        @Override
        public void build(Player owner, Building building)
        {
            throw new UnsupportedOperationException("Player " + owner + " attempted to placed a " + building + " on Hex.NULL!");
        }
    };
}
