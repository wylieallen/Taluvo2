package taluvo.game.hex;

public enum Building
{
    NONE(0, false, false), VILLAGE(1, false, true), TEMPLE(75, true, true), TOWER(200, true, true);

    private final int points;
    private final boolean isPermanent, occupiesSpace;

    private Building(int points, boolean isPermanent, boolean occupiesSpace)
    {
        this.points = points;
        this.isPermanent = isPermanent;
        this.occupiesSpace = occupiesSpace;
    }

    public int getPoints() { return points; }
    public boolean isPermanent() { return isPermanent; }
    public boolean occupiesSpace() { return occupiesSpace; }
}
