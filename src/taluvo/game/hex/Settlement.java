package taluvo.game.hex;

import taluvo.game.Player;
import taluvo.util.Point;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Settlement
{
    private final Set<Point> territory;
    private final int id;
    private final Player owner;
    private boolean hasTower, hasTemple;

    public Settlement(int id, Player owner)
    {
        this.id = id;
        this.owner = owner;
        this.territory = new HashSet<>();
        hasTower = hasTemple = false;
    }

    public void add(int x, int y, Building building)
    {
        territory.add(new Point(x, y));
        if(building == Building.TOWER)
        {
            this.hasTower = true;
        }
        else if (building == Building.TEMPLE)
        {
            this.hasTemple = true;
        }
    }

    public int getId() { return id; }
    public Player getOwner() { return owner; }
    public int getSize() { return territory.size(); }
    public boolean hasTemple() { return hasTemple; }
    public boolean hasTower() { return hasTower; }
    public Iterator<Point> iterator() { return territory.iterator(); }
}
