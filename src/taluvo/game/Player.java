package taluvo.game;

import taluvo.game.hex.Building;

import java.util.EnumMap;

public class Player
{
    private String name;
    private int score;

    private EnumMap<Building, Integer> buildings;

    public static final Player NULL = new Player("None");

    public Player(String name)
    {
        this.name = name;
        this.score = 0;
        this.buildings = new EnumMap<>(Building.class);
        buildings.put(Building.VILLAGE, 20);
        buildings.put(Building.TOWER, 2);
        buildings.put(Building.TEMPLE, 3);
    }

    public void placed(Building building)
    {
        buildings.put(building, buildings.get(building) - 1);
        score += building.getPoints();
    }

    public int getScore() { return score; }
    public int getBuildingCount(Building building) { return buildings.get(building); }

    @Override
    public String toString() { return name; }
}
