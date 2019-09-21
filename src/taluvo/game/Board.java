package taluvo.game;

import taluvo.game.hex.Building;
import taluvo.game.hex.Direction;
import taluvo.game.hex.Hex;
import taluvo.game.hex.Terrain;
import taluvo.game.listeners.BuildingPlacementListener;
import taluvo.game.listeners.HexPlacementListener;
import taluvo.util.Point;

import java.util.*;

public class Board implements Iterable<Point>
{
    private final Set<Point> neighborOfNeighborOffsets;
    private final Set<Point> neighborOffsets;
    private final Set<HexPlacementListener> hexPlacementListeners;
    private final Set<BuildingPlacementListener> buildingPlacementListeners;

    private final Map<Point, Hex> hexes;
    private final View view;

    public Board()
    {
        this.hexes = new HashMap<>();
        this.hexPlacementListeners = new HashSet<>();
        this.buildingPlacementListeners = new HashSet<>();
        this.neighborOffsets = new HashSet<>();
        this.neighborOfNeighborOffsets = new HashSet<>();
        this.view = new View();
        for(Direction direction : Direction.values())
        {
            neighborOffsets.add(new Point(direction.x, direction.y));
        }
        for(Point point : neighborOffsets)
        {
            for(Direction direction : Direction.values())
            {
                neighborOfNeighborOffsets.add(new Point(direction.x + point.x, direction.y + point.y));
            }
        }
        neighborOfNeighborOffsets.addAll(neighborOffsets);
    }

    private Hex getHex(int x, int y)
    {
        return hexes.getOrDefault(new Point(x, y), Hex.NULL);
    }

    private int getHeight(int x, int y)
    {
        return getHex(x, y).getHeight();
    }

    public void subscribe(HexPlacementListener listener)
    {
        hexPlacementListeners.add(listener);
    }

    public void subscribe(BuildingPlacementListener listener)
    {
        buildingPlacementListeners.add(listener);
    }

    public void placeHexPlaceholder(int x, int y)
    {
        hexes.put(new Point(x, y), Hex.NULL);
        hexPlacementListeners.forEach(listener -> listener.hexPlaced(x, y, Hex.NULL.getHeight(), Hex.NULL.getTerrain(), Hex.NULL.getTileId()));
    }

    private void placeHex(int x, int y, Terrain terrain, int tileId)
    {
        int height = getHeight(x, y) + 1;
        hexes.put(new Point(x, y), new Hex(terrain, height, tileId));
        hexPlacementListeners.forEach(listener -> listener.hexPlaced(x, y, height, terrain, tileId));
    }

    public void placeTile(int x, int y, Terrain terrain, int tileId)
    {
        placeHex(x, y, terrain, tileId);

        for(Point offset : neighborOfNeighborOffsets)
        {
            Point neighborPt = new Point(x + offset.x, y + offset.y);
            if(!hexes.containsKey(neighborPt))
            {
                placeHexPlaceholder(neighborPt.x, neighborPt.y);
            }
        }
    }

    public void placeBuilding(int x, int y, Building building, Player player)
    {
        Hex hex = getHex(x, y);
        hex.build(player, building);
        buildingPlacementListeners.forEach(listener -> listener.buildingPlaced(x, y, building, player));
    }

    public Set<Point> getNeighbors(int x, int y)
    {
        Set<Point> neighborhood = new HashSet<>();
        for(Point offset : neighborOffsets)
        {
            neighborhood.add(new Point(offset.x + x, offset.y + y));
        }
        return neighborhood;
    }

    public Iterator<Point> iterator() { return hexes.keySet().iterator(); }

    public Board.View getView() { return view; }

    public class View implements Iterable<Point>
    {
        public Terrain getTerrain(int x, int y) { return getHex(x, y).getTerrain(); }
        public int getTileId(int x, int y) { return getHex(x, y).getTileId(); }
        public int getHeight(int x, int y) { return getHex(x, y).getHeight(); }
        public Building getBuilding(int x, int y) { return getHex(x, y).getBuilding(); }
        public Player getOwner(int x, int y) { return getHex(x, y).getOwner(); }
        public boolean isBuildable(int x, int y) { return getHex(x, y).isBuildable(); }
        public boolean isDestructible(int x, int y) { return getHex(x, y).isDestructible(); }
        public Iterator<Point> iterator() { return Board.this.iterator(); }
        public Set<Point> getNeighbors() { return new HashSet<>(neighborOffsets); }
        public Set<Point> getNeighbors(int x, int y) { return Board.this.getNeighbors(x, y); }
    }
}
